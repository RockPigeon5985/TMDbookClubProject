package Controllers

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"
	"net/http"
)

type Book struct {
	gorm.Model

	Title  string
	Author string
	Year   int
}

func Suggest(w http.ResponseWriter, r *http.Request) {
	var suggestedBooks []Book

	params := mux.Vars(r)
	var s = "%" + params["s"] + "%"

	// NEED TO FIX
	result := DB.Raw("SELECT id, title, author, year FROM books WHERE title LIKE ? OR author LIKE ?", s, s).Scan(&suggestedBooks)

	err := json.NewEncoder(w).Encode(result)
	if err != nil {
		return
	}
}
