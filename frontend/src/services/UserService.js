import axios from "axios";

const USERS_REST_API_URL = 'http://localhost:8080/user';

class UserService{
    list(){
        return axios.get(USERS_REST_API_URL);
    }

    create(){
        return axios.post(USERS_REST_API_URL + '/create');
    }

    getUserByEmail(email){
        return axios.get(USERS_REST_API_URL + '/email', email)
    }
}

export default new UserService()