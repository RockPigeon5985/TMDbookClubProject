package Utils

import (
	"GoLang_Backend/Database"
	"errors"
	"fmt"
	"strconv"
	"time"
)

// GetRentListByRentID return first RentList that matches rentId
func GetRentListByRentID(rentId uint) Database.RentList {
	var result Database.RentList

	Database.DB.Table("rent_lists").Where("rent_id = ?", rentId).First(&result)

	return result
}

type RentedBooks struct {
	RentList     Database.RentList
	DateOfReturn time.Time
}

// GetAllBorrowedBooks get all rented books and when are no longer rented
func GetAllBorrowedBooks(bookList []Database.BookList) []RentedBooks {
	rentList := GetAllRentListByRentId(bookList)
	var rentedBooks []RentedBooks
	for _, element := range rentList {
		num, err2 := strconv.Atoi(string(element.Period[0]))
		if err2 != nil {
			fmt.Println(err2)
		}
		rentedBooks = append(rentedBooks, RentedBooks{RentList: element, DateOfReturn: element.DateOfRent.AddDate(0, 0, num*7)})
	}

	return rentedBooks
}

// GetAllRentListByRentId get all rented books by rent id inside BookList
func GetAllRentListByRentId(bookList []Database.BookList) []Database.RentList {
	var rentList []Database.RentList
	for _, element := range bookList {
		rentList = append(rentList, GetRentListByRentID(element.RentID))
	}

	return rentList
}

// VerifyAvailableForRent verify if a book can be rented
func VerifyAvailableForRent(userId, bookId uint) (Database.BookList, error) {
	var bookList Database.BookList

	// Verify if user (that wants to rent) exists
	err := VerifyUser(userId)
	if err != nil {
		return bookList, err
	}

	// Verify if book exists
	err = VerifyBook(bookId)
	if err != nil {
		return bookList, err
	}

	// Verify if the book is available for rent
	bookList, err = VerifyBookAvailableForRent(bookId)
	if err != nil {
		return bookList, err
	}

	return bookList, nil
}

// VerifyRent verify if a rent id exists
func VerifyRent(rentId uint) error {
	var rent Database.RentList

	Database.DB.Table("rent_lists").Where("rent_id = ?", rentId).First(&rent)

	if rent == (Database.RentList{}) {
		return errors.New("rent id doesn't exist")
	}
	return nil
}

// UpdateRentPeriod update period column from rent_lists table
func UpdateRentPeriod(rentId uint, period string) {
	rent := GetRentListByRentID(rentId)

	currentPeriod, _ := strconv.Atoi(string(rent.Period[0]))
	extendedPeriod, _ := strconv.Atoi(string(period[0]))

	Database.DB.Table("rent_lists").Where("rent_id = ?", rentId).Update("period", strconv.Itoa(currentPeriod+extendedPeriod)+" weeks")
}

// GetRentedBooks return all books rented by a user
func GetRentedBooks(userId uint) []RentedBooks {
	var rents []Database.RentList

	Database.DB.Table("rent_lists").Where("user_id = ?", userId).Find(&rents)

	var rentedBooks []RentedBooks
	for _, element := range rents {
		period, _ := strconv.Atoi(string(element.Period[0]))
		rentedBooks = append(rentedBooks, RentedBooks{RentList: element, DateOfReturn: element.DateOfRent.AddDate(0, 0, period*7)})
	}

	return rentedBooks
}
