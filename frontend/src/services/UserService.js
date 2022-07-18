import axios from "axios";

const USERS_REST_API_URL = 'htpp://localhost:8080/user';

class UserService{
    list(){
        return axios.get(USERS_REST_API_URL);
    }

    create(){
        return axios.post(USERS_REST_API_URL + '/create');
    }
}

export default new UserService()