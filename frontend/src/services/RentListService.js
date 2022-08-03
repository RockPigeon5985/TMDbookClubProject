import axios from "axios";

const RENTLIST_REST_API_URL = 'http://localhost:8080/rentlist';

class RentListService{
    list(){
        return axios.get(RENTLIST_REST_API_URL);
    }

    rent(){
        return axios.post(RENTLIST_REST_API_URL + '/rent');
    }

    verifyRent(){
        return axios.get(RENTLIST_REST_API_URL + '/verifyRent');
    }

    extendRent(){
        return axios.post(RENTLIST_REST_API_URL + '/extend');
    }

    returnBook(){
        return axios.get(RENTLIST_REST_API_URL + '/return');
    }

    search(){
        return axios.get((RENTLIST_REST_API_URL + '/search'));
    }
}

export default new RentListService()