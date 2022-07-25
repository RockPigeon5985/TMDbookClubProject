import React, {useState} from 'react';
import UserService from "../services/UserService";

const LoginForm = () => {
    const [user, setUser] = useState({firstName: '', lastName: '', email: '', password: ''});
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const getByEmail = (email) => {
        UserService.getUserByEmail(email).then(response => {
            setUser(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
            setError(error);
        })
    }

    return (
        <div>
            <br/><br/>
            <div className="container">
                <form>
                    <div className="form-inner">
                        <h2>Login</h2>
                        {(error !== "") ? (<div className="error">{error}</div>) : ""}
                        <div className="form-group">
                            <label className="form-label">Email : </label>
                            <input
                                type="email"
                                name="email"
                                placeholder="Enter email"
                                className="form-control"
                                defaultValue={email}
                                onChange={e => setUser({...user, email: e.target.value})}>
                            </input>
                        </div>

                        <div className="form-group">
                            <label className="form-label">Password : </label>
                            <input
                                type="text"
                                name="password"
                                placeholder="Enter password"
                                className="form-control"
                                defaultValue={password}
                                onChange={e => setUser({...user, password: e.target.value})}>
                            </input>
                        </div>
                        <button className="btn btn-success" onClick={e => getByEmail()}> LogIn</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default LoginForm;