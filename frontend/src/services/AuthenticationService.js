import axios from 'axios'

const AUTH_REST_API_URL = 'http://localhost:8080/auth'

class AuthenticationService {
  login () {
    return axios.get(AUTH_REST_API_URL + '/login')
  }
}

export default new AuthenticationService()
