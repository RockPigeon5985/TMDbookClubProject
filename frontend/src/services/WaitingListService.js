import axios from "axios";

const WAITINGLIST_REST_API_URL = 'htpp://localhost:8080/waitinglist';

class WaitingListService{
    list(){
        return axios.get(WAITINGLIST_REST_API_URL);
    }

    add(){
        return axios.post(WAITINGLIST_REST_API_URL + '/add');
    }
}

export default new WaitingListService()