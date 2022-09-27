package Utils

import (
	"GoLang_Backend/Database"
	"errors"
)

// GetUserById return a user that matches userid
func GetUserById(userid uint) Database.User {
	var user Database.User

	Database.DB.Table("users").Where("user_id = ?", userid).First(&user)

	return user
}

// VerifyUser verify if user exists
func VerifyUser(userId uint) error {
	var user = GetUserById(userId)
	if user == (Database.User{}) {
		return errors.New("user doesn't exist")
	}
	return nil
}
