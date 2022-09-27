package Controllers

import (
	"GoLang_Backend/Database"
	"GoLang_Backend/Utils"
	"encoding/json"
	"net/http"
	"time"
)

// CreateWait create a wait in wait_lists
func CreateWait(w http.ResponseWriter, r *http.Request) {
	request := struct {
		UserId uint
		BookId uint
	}{}
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		return
	}

	err = Utils.VerifyUserAndBook(request.UserId, request.BookId)
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

	waitList := Database.WaitList{
		UserID:     request.UserId,
		BookID:     request.BookId,
		DateOfWait: time.Now(),
	}

	createdWaitList := Database.DB.Create(&waitList)
	err = createdWaitList.Error
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
		err = json.NewEncoder(w).Encode(&waitList)
		if err != nil {
			return
		}
	}
}
