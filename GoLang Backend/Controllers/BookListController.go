package Controllers

import (
	"GoLang_Backend/Database"
	"GoLang_Backend/Utils"
	"encoding/json"
	"net/http"
	"time"
)

// CreateBookList add book for a user in book list
func CreateBookList(w http.ResponseWriter, r *http.Request) {
	request := struct {
		UserID uint
		BookID uint
		Title  string
		Author string
		Year   uint
	}{}

	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyUser(request.UserID)
	if err != nil {
		w.WriteHeader(http.StatusNoContent)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
		return
	}
	var bookList Database.BookList

	if request.BookID != 0 {
		bookList = Database.BookList{
			UserID: request.UserID,
			BookID: request.BookID,
			RentID: 0,
		}
	} else {
		book := Database.Book{
			Title:  request.Title,
			Author: request.Author,
			Year:   request.Year,
		}

		var bookId uint
		bookId, err = Utils.AddBook(book)
		if err != nil {
			w.WriteHeader(http.StatusConflict)
			err = json.NewEncoder(w).Encode(struct {
				Error string
			}{Error: err.Error()})
			if err != nil {
				return
			}
			return
		}

		bookList = Database.BookList{
			UserID: request.UserID,
			BookID: bookId,
			RentID: 0,
		}
	}

	createdBookList := Database.DB.Create(&bookList)
	err = createdBookList.Error
	if err != nil {
		w.WriteHeader(http.StatusConflict)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
	} else {
		err = json.NewEncoder(w).Encode(&bookList)
		if err != nil {
			return
		}
	}
}

// AvailableForRent get all books id that can be rented
func AvailableForRent(w http.ResponseWriter, r *http.Request) {
	// this bookId struct is used to extract book_id column from table book_lists
	var books []struct {
		BookID uint
	}

	Database.DB.Table("book_lists").Select("book_id").Where("rent_id = 0").Find(&books)

	err := json.NewEncoder(w).Encode(&books)
	if err != nil {
		return
	}
}

// ShowBorrowedBooks shows who borrowed a user's books and when they should return them
func ShowBorrowedBooks(w http.ResponseWriter, r *http.Request) {
	request := struct {
		UserID uint
	}{}
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyUser(request.UserID)
	if err != nil {
		w.WriteHeader(http.StatusNoContent)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
		return
	}

	var bookList []Database.BookList
	bookList = Utils.GetBookListByUserIDWhereRentIsNotNull(request.UserID)

	result := Utils.GetAllBorrowedBooks(bookList)
	err = json.NewEncoder(w).Encode(&result)
	if err != nil {
		return
	}
}

// SearchBookByTitleOrAuthor get all books by matching title or author and see if it is available or when it will be available
func SearchBookByTitleOrAuthor(w http.ResponseWriter, r *http.Request) {
	request := struct {
		Title  string
		Author string
	}{}
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	books := Utils.GetBooksIDByTitleOrAuthor(request.Title, request.Author)

	var bookLists []Database.BookList
	for _, element := range books {
		bookLists = append(bookLists, Utils.GetBookListByBookId(element.ID)...)
	}

	type Result struct {
		Available    bool
		DateOfReturn time.Time
	}
	var result []Result
	for _, element := range bookLists {
		if element.RentID == 0 {
			result = append(result, Result{Available: true})
		}
	}

	if len(result) != len(bookLists) {
		rentedBooks := Utils.GetAllBorrowedBooks(bookLists)
		for _, element := range rentedBooks {
			result = append(result, Result{Available: false, DateOfReturn: element.DateOfReturn})
		}
	}
	err = json.NewEncoder(w).Encode(&result)
	if err != nil {
		return
	}
}
