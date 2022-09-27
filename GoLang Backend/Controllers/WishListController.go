package Controllers

import (
	"GoLang_Backend/Database"
	"GoLang_Backend/Utils"
	"encoding/json"
	"net/http"
)

// CreateWish add a book on wish list (the book needs to exist in DB)
func CreateWish(w http.ResponseWriter, r *http.Request) {
	var request Database.WishList
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyUserAndBook(request.UserID, request.BookID)
	if err != nil {
		w.WriteHeader(http.StatusNoContent)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
		return
	}

	createdWish := Database.DB.Create(&request)
	err = createdWish.Error
	if err != nil {
		w.WriteHeader(http.StatusConflict)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
	} else {
		w.WriteHeader(http.StatusCreated)
		err = json.NewEncoder(w).Encode(&request)
		if err != nil {
			return
		}
	}
}

// ShowWishList show a user wish list
func ShowWishList(w http.ResponseWriter, r *http.Request) {
	request := struct {
		UserId uint
	}{}
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyUser(request.UserId)
	if err != nil {
		w.WriteHeader(http.StatusNoContent)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
		return
	}

	var wishList []Database.WishList

	Database.DB.Table("wish_lists").Where("user_id = ?", request.UserId).Find(&wishList)
	
	err = json.NewEncoder(w).Encode(&wishList)
	if err != nil {
		return
	}
}

// DeleteWish delete a wish list
func DeleteWish(w http.ResponseWriter, r *http.Request) {
	var request Database.WishList
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyUserAndBook(request.UserID, request.BookID)
	if err != nil {
		w.WriteHeader(http.StatusNoContent)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
		return
	}

	err = Utils.VerifyWishList(request)
	if err != nil {
		w.WriteHeader(http.StatusNoContent)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
		return
	}

	Database.DB.Delete(&request)
}
