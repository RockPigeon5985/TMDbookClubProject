package Controllers

import (
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

	DB.Find(&users)

	json.NewEncoder(w).Encode(&users)
}

func GetUserById(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	var user User

	DB.First(&user, params["id"])

	json.NewEncoder(w).Encode(user)
}

func CreateUser(w http.ResponseWriter, r *http.Request) {
	var user User

	json.NewDecoder(r.Body).Decode(&user)

	createdUser := DB.Create(&user)
	err := createdUser.Error

	if err != nil {
		json.NewEncoder(w).Encode(err)
	} else {
		json.NewEncoder(w).Encode(&user)
	}
}
