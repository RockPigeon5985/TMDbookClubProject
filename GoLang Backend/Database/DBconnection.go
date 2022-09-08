package Database

import (
	"Controllers"
	"fmt"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/postgres"
	"github.com/joho/godotenv"
	"log"
	"os"
)

var DB *gorm.DB
var err error

func DBconnection() {
	// Loading environment variables
	err := godotenv.Load(".env")

	if err != nil {
		log.Fatalf("Error loading .env file")
	}

	dialect := os.Getenv("DIALECT")
	host := os.Getenv("HOST")
	dbPort := os.Getenv("DBPORT")
	user := os.Getenv("USER")
	dbName := os.Getenv("NAME")
	password := os.Getenv("PASSWORD")

	// Databese connection string
	dbURI := fmt.Sprintf("host=%s user=%s dbname=%s sslmode=disable password=%s port=%s", host, user, dbName, password, dbPort)

	// Openening connection to database
	DB, err = gorm.Open(dialect, dbURI)

	if err != nil {
		log.Fatal(err)
	} else {
		fmt.Println("Successfully connected to database!")
	}

	// Make migration to the database if they have not already been created
	DB.AutoMigrate(&Controllers.User{})
	DB.AutoMigrate(&Controllers.Book{})
	DB.AutoMigrate(&Controllers.RentList{})
	DB.AutoMigrate(&Controllers.BookList{})
	DB.AutoMigrate(&Controllers.WaitList{})
	DB.AutoMigrate(&Controllers.WishList{})
}
