package Database

import (
	"fmt"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/postgres"
	"github.com/joho/godotenv"
	"log"
	"os"
)

var DB *gorm.DB

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

	// Database connection string
	dbURI := fmt.Sprintf("host=%s user=%s dbname=%s sslmode=disable password=%s port=%s", host, user, dbName, password, dbPort)

	// Opening connection to database
	DB, err = gorm.Open(dialect, dbURI)
	if err != nil {
		log.Fatal(err)
	} else {
		fmt.Println("Successfully connected to database!")
	}

	// Make migration to the database (run only one time when migration is needed)
	/*	DB.AutoMigrate(&User{})
		DB.AutoMigrate(&Book{})
		DB.AutoMigrate(&RentList{})
		DB.AutoMigrate(&BookList{})
		DB.AutoMigrate(&WaitList{})
		DB.AutoMigrate(&WishList{})*/
}
