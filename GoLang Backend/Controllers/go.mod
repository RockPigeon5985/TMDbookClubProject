module Controllers

go 1.19

require (
	github.com/gorilla/mux v1.8.0
	github.com/jinzhu/gorm v1.9.16
	GoLang_Backend/Database v1.0.0
)

replace (
	GoLang_Backend/Database => ./Database
)
require github.com/jinzhu/inflection v1.0.0 // indirect
