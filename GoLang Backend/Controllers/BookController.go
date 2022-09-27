package Controllers

import (
	"GoLang_Backend/Database"
	"encoding/json"
	"net/http"
)

// SuggestBook suggest a book by title or author containing the string
func SuggestBook(w http.ResponseWriter, r *http.Request) {
	request := struct {
		String string
	}{}
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	var suggestedBooks []Database.Book

	Database.DB.Table("books").Where("title LIKE '%' || ? || '%'", request.String).Or("author LIKE '%' || ? || '%'", request.String).Find(&suggestedBooks)

	err = json.NewEncoder(w).Encode(&suggestedBooks)
	if err != nil {
		return
	}
}
