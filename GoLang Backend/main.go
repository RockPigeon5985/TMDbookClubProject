package main

import (
	"GoLang_Backend/Controllers"
	"GoLang_Backend/Database"
	"GoLang_Backend/Security"
	"fmt"
	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"
	"net/http"
)

func main() {
	// Start connection to database
	Database.DBconnection()

	// Router for endpoints
	router := mux.NewRouter()

	// User endpoints
	router.Handle("/user/create", Security.ValidateJWT(Controllers.CreateUser)).Methods("POST")

	// Book endpoints
	router.Handle("/book/suggest", Security.ValidateJWT(Controllers.SuggestBook)).Methods("GET")

	// BookList endpoints
	router.Handle("/booklist/add", Security.ValidateJWT(Controllers.CreateBookList)).Methods("POST")
	router.Handle("/booklist/available", Security.ValidateJWT(Controllers.AvailableForRent)).Methods("GET")
	router.Handle("/booklist/borrowed", Security.ValidateJWT(Controllers.ShowBorrowedBooks)).Methods("GET")
	router.Handle("/booklist/search", Security.ValidateJWT(Controllers.SearchBookByTitleOrAuthor)).Methods("GET")

	// RentList endpoints
	router.Handle("/rentlist/rent", Security.ValidateJWT(Controllers.CreateRent)).Methods("POST")
	router.Handle("/rentlist/extend", Security.ValidateJWT(Controllers.ExtendRent)).Methods("POST")
	router.Handle("/rentlist/rentedbooks", Security.ValidateJWT(Controllers.RentedBooks)).Methods("GET")

	// WaitList endpoints
	router.Handle("/waitlist/create", Security.ValidateJWT(Controllers.CreateWait)).Methods("POST")

	// WishList endpoints
	router.Handle("/wishlist/create", Security.ValidateJWT(Controllers.CreateWish)).Methods("POST")
	router.Handle("/wishlist/show", Security.ValidateJWT(Controllers.ShowWishList)).Methods("GET")
	router.Handle("/wishlist/delete", Security.ValidateJWT(Controllers.DeleteWish)).Methods("DELETE")

	//JWT
	router.HandleFunc("/jwt", Security.GetJwt)

	// Start router
	err := http.ListenAndServe("localhost:8080", router)
	if err != nil {
		fmt.Println(err)
		return
	}

	// Close connection to database when exit main
	defer func(DB *gorm.DB) {
		DBerr := DB.Close()
		if DBerr != nil {
			fmt.Println("Database close connection error: " + DBerr.Error())
		}
	}(Database.DB)
}
