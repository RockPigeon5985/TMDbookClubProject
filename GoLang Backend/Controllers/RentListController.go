package Controllers

import "github.com/jinzhu/gorm"

type RentList struct {
	gorm.Model

	PersonID   int
	BookId     int
	Period     string
	DateOfRent string
}
