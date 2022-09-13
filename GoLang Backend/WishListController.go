package Controllers

import "github.com/jinzhu/gorm"

type WishList struct {
	gorm.Model

	UserID int
	BookID int
}
