import axios from "axios";

const BOOKLIST_REST_API_URL = 'htpp://localhost:8080/booklist';

class BookListService{
    list(){
        return axios.get(BOOKLIST_REST_API_URL);
    }

    add(){
        return axios.post(BOOKLIST_REST_API_URL + '/add');
    }

    getBooksForRent(){
        return axios.get(BOOKLIST_REST_API_URL + '/rent');
    }
}

export default new BookListService()