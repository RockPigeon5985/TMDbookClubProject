package Controllers

import (
	"encoding/json"
	"github.com/jinzhu/gorm"
	"gorm.io/datatypes"
	"net/http"
	"time"
)

type RentList struct {
	gorm.Model

	UserID     int
	User       User `gorm:"references:ID"`
	BookID     int
	Book       Book `gorm:"references:ID"`
	Period     string
	DateOfRent datatypes.Date
}

// Rent Create a rent
func Rent(w http.ResponseWriter, r *http.Request) {
	var rent RentList

	json.NewDecoder(r.Body).Decode(&rent)

	rent.DateOfRent = datatypes.Date(time.Now())
	createdRent := DB.Create(&rent)
	err := createdRent.Error

	if err != nil {
		json.NewEncoder(w).Encode(err)
	} else {
		json.NewEncoder(w).Encode(&rent)
	}
}
