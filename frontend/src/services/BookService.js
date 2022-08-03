import axios from "axios";

const BOOK_REST_API_URL = 'http://localhost:8080/book';

class BookService{
    list(){
        return axios.get(BOOK_REST_API_URL);
    }
}

export default new BookService()