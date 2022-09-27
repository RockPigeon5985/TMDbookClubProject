package Controllers

import (
	"GoLang_Backend/Database"
	"GoLang_Backend/Security"
	"encoding/json"
	"net/http"
)

// CreateUser create a user account
func CreateUser(w http.ResponseWriter, r *http.Request) {
	var user Database.User

	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		return
	}

	user.Enabled = true
	user.Role = "USER"
	user.Password, err = Security.HashPassword(user.Password)
	if err != nil {
		err = json.NewEncoder(w).Encode(err.Error())
		if err != nil {
			return
		}
		return
	}

	createdUser := Database.DB.Create(&user)
	err = createdUser.Error
	if err != nil {
		w.WriteHeader(http.StatusConflict)
		err = json.NewEncoder(w).Encode(err.Error())
		if err != nil {
			return
		}
	} else {
		w.WriteHeader(http.StatusCreated)
		err = json.NewEncoder(w).Encode(&user)
		if err != nil {
			return
		}
	}
}
