package Controllers

import (
	"GoLang_Backend/Database"
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"
	"net/http"
)

type User struct {
	gorm.Model

	FirstName string
	LastName  string
	Email     string `gorm:"typevarchar(100);unique_index"`
	Password  string
	Enabled   bool
	Role      string
}

func GetUsers(w http.ResponseWriter, r *http.Request) {
	var users []User

	Database.DB.Find(&users)

	json.NewEncoder(w).Encode(&users)
}

func GetUser(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	var user User

	Database.db.First(&user, params["id"])

	json.NewEncoder(w).Encode(user)
}

func CreateUser(w http.ResponseWriter, r *http.Request) {
	var user User

	json.NewDecoder(r.Body).Decode(&user)

	createdUser := Database.db.Create(&user)
	Database.err = createdUser.Error

	if Database.err != nil {
		json.NewEncoder(w).Encode(Database.err)
	} else {
		json.NewEncoder(w).Encode(&user)
	}
}
