package main

import (
	"Controllers"
	"GoLang_Backend/Database"
	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"
	"net/http"
)

var db = Database.DB

func main() {
	// API routes
	router := mux.NewRouter()

	router.HandleFunc("/users", Controllers.GetUsers).Methods("GET")
	router.HandleFunc("/user/{id}", Controllers.GetUser).Methods("GET")
	router.HandleFunc("/user/create", Controllers.CreateUser).Methods("POST")

	err := http.ListenAndServe("localhost:8080", router)
	if err != nil {
		return
	}

	// Close connection to database when router finish
	defer func(db *gorm.DB) {
		err := db.Close()
		if err != nil {

		}
	}(db)
}
