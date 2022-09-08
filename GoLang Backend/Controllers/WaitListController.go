package Controllers

import "github.com/jinzhu/gorm"

type WaitList struct {
	gorm.Model

	UserID     int
	BookID     int
	DateOfWait string
}
