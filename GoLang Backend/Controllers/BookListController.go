package Controllers

import "github.com/jinzhu/gorm"

type BookList struct {
	gorm.Model

	UserID     int
	BookID     int
	RentListID int
}
