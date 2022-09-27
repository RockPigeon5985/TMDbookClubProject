package Controllers

import (
	"GoLang_Backend/Database"
	"GoLang_Backend/Utils"
	"encoding/json"
	"net/http"
	"strconv"
	"time"
)

// CreateRent Create a rent
func CreateRent(w http.ResponseWriter, r *http.Request) {
	var request Database.RentList
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	var bookList Database.BookList
	bookList, err = Utils.VerifyAvailableForRent(request.UserID, request.BookID)
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

	request.DateOfRent = time.Now()
	createdRent := Database.DB.Create(&request)
	err = createdRent.Error
	if err != nil {
		w.WriteHeader(http.StatusConflict)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: err.Error()})
		if err != nil {
			return
		}
	} else {
		err = json.NewEncoder(w).Encode(&request)
		if err != nil {
			return
		}
	}

	Utils.UpdateBookListRentId(bookList, request.ID)
}

// ExtendRent extend the period of a rent
func ExtendRent(w http.ResponseWriter, r *http.Request) {
	request := struct {
		RentId uint
		Period string
	}{}
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyRent(request.RentId)
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

	requestPeriod, _ := strconv.Atoi(string(request.Period[0]))
	if requestPeriod > 2 {
		w.WriteHeader(http.StatusConflict)
		err = json.NewEncoder(w).Encode(struct {
			Error string
		}{Error: "extended period exceeded, only 1-2 weeks"})
		if err != nil {
			return
		}
		return
	}

	Utils.UpdateRentPeriod(request.RentId, request.Period)

	err = json.NewEncoder(w).Encode(Utils.GetRentListByRentID(request.RentId))
	if err != nil {
		return
	}
}

// RentedBooks returns what books a user rented and when to return the books
func RentedBooks(w http.ResponseWriter, r *http.Request) {
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

	result := Utils.GetRentedBooks(request.UserId)
	err = json.NewEncoder(w).Encode(&result)
	if err != nil {
		return
	}
}
