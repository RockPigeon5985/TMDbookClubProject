package Database

import (
	"time"
)

type User struct {
	ID        uint   `gorm:"primary_key;column:user_id"`
	FirstName string `gorm:"not_null"`
	LastName  string `gorm:"not_null"`
	Email     string `gorm:"unique_index"`
	Password  string `gorm:"not_null"`
	Enabled   bool   `gorm:"not_null"`
	Role      string `gorm:"not_null"`
}

type Book struct {
	ID     uint   `gorm:"primary_key;column:book_id"`
	Title  string `gorm:"not_null"`
	Author string `gorm:"not_null"`
	Year   uint   `gorm:"not_null"`
}

type RentList struct {
	ID         uint      `gorm:"unique_index;column:rent_id;auto_increment:true"`
	UserID     uint      `gorm:"primary_key;auto_increment:false;column:user_id"`
	BookID     uint      `gorm:"primary_key;auto_increment:false;column:book_id"`
	Period     string    `gorm:"not_null"`
	DateOfRent time.Time `gorm:"not_null"`
}

type BookList struct {
	UserID uint `gorm:"primary_key;auto_increment:false;column:user_id"`
	BookID uint `gorm:"primary_key;auto_increment:false;column:book_id"`
	RentID uint
}

type WaitList struct {
	UserID     uint      `gorm:"primary_key;auto_increment:false;column:user_id"`
	BookID     uint      `gorm:"primary_key;auto_increment:false;column:book_id"`
	DateOfWait time.Time `gorm:"not_null"`
}

type WishList struct {
	UserID uint `gorm:"primary_key;auto_increment:false;column:user_id"`
	BookID uint `gorm:"primary_key;auto_increment:false;column:book_id"`
}
