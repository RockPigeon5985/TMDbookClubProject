package Utils

import (
	"GoLang_Backend/Database"
	"errors"
)

func VerifyWishList(wishList Database.WishList) error {
	var result Database.WishList

	Database.DB.Table("wish_lists").Where("user_id = ?1 AND book_id = ?2", wishList.UserID, wishList.BookID).First(&result)

	if result == (Database.WishList{}) {
		return errors.New("wish list doesn't exist")
	}

	return nil
}
