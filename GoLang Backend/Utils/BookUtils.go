package Utils

import (
	"GoLang_Backend/Database"
	"errors"
)

// GetBookById return a book that matches bookId
func GetBookById(bookId uint) Database.Book {
	var book Database.Book

	Database.DB.Table("books").Where("book_id = ?", bookId).First(&book)

	return book
}

// VerifyBook verify if a book exists
func VerifyBook(bookId uint) error {
	book := GetBookById(bookId)
	if book == (Database.Book{}) {
		return errors.New("book not found")
	}
	return nil
}

// AddBook add a book in database
func AddBook(book Database.Book) (uint, error) {
	newBook := GetBookIdByTitleAndAuthor(book)
	if newBook.ID == 0 {
		createdBook := Database.DB.Create(&book)
		err := createdBook.Error
		return GetBookIdByTitleAndAuthor(book).ID, err
	} else {
		return newBook.ID, nil
	}
}

// GetBookIdByTitleAndAuthor return the id of book
func GetBookIdByTitleAndAuthor(book Database.Book) Database.Book {
	var searchedBook Database.Book

	Database.DB.Table("books").Where("title = $1 AND author = $2", book.Title, book.Author).First(&searchedBook)

	return searchedBook
}

// GetBooksIDByTitleOrAuthor get a book by title or author
func GetBooksIDByTitleOrAuthor(title, author string) []Database.Book {
	var books []Database.Book

	Database.DB.Table("books").Where("title = ?", title).Or("author = ?", author).Find(&books)

	return books
}
