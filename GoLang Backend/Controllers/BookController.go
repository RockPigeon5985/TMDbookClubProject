package Controllers

import "github.com/jinzhu/gorm"

type Book struct {
	gorm.Model

	Title  string
	Author string
	Year   int
}
