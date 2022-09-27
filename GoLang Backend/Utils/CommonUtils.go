package Utils

import "errors"

// VerifyUserAndBook verify if book and user exists
func VerifyUserAndBook(userId, bookId uint) error {
	err := VerifyUser(userId)
	if err != nil {
		return errors.New("user doesn't exist")
	}

	err = VerifyBook(bookId)
	if err != nil {
		return errors.New("book doesn't exist")
	}

	return nil
}
