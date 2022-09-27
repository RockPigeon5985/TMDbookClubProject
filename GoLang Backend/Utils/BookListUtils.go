package Utils

import (
	"GoLang_Backend/Database"
	"errors"
)

// GetBookListByUserIDWhereRentIsNotNull get the list of books for a user id
func GetBookListByUserIDWhereRentIsNotNull(userid uint) []Database.BookList {
	var result []Database.BookList

	Database.DB.Table("book_lists").Where("user_id = ? AND NOT rent_id = 0", userid).Find(&result)

	return result
}

// GetBookListByBookIdAndRentId0 return first BookList that contains the book and is not rented
func GetBookListByBookIdAndRentId0(bookId uint) Database.BookList {
	var bookList Database.BookList

	Database.DB.Table("book_lists").Where("book_id = ? AND rent_id = 0", bookId).First(&bookList)

	return bookList
}

// VerifyBookAvailableForRent verify if the book is available for rent
func VerifyBookAvailableForRent(bookId uint) (Database.BookList, error) {
	var bookList Database.BookList
	bookList = GetBookListByBookIdAndRentId0(bookId)
	if bookList == (Database.BookList{}) {
		return bookList, errors.New("book is not available for rent")
	}
	return bookList, nil
}

// UpdateBookListRentId update rent_id column for a BookList
func UpdateBookListRentId(bookList Database.BookList, rentId uint) {
	Database.DB.Table("book_lists").Where("user_id = ?1 AND book_id = ?2", bookList.UserID, bookList.BookID).Update("rent_id", rentId)
}

// GetBookListByBookId return all BookLists that contains matched book id
func GetBookListByBookId(bookId uint) []Database.BookList {
	var bookList []Database.BookList

	Database.DB.Table("book_lists").Where("book_id = ?", bookId).Find(&bookList)

	return bookList
}
