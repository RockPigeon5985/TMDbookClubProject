import React, {useState} from 'react';
import UserService from "../services/UserService";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
    let navigate = useNavigate();
    const routeChange = () =>{
        let path = `/register`;
        navigate(path);
    }

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');


    const handleLogIn = () => {
        UserService.getUserByEmail(email).then(response => {
            console.log(response.data);
        }).catch(error => {
            console.log(error);
            setError(error);
        })
    }

    return (
        <div className="px-8 py-8">
            <div className=" flex max-w-2xl mx-auto shadow border-b">
                <div className="px-8 py-8">
                    <div className="font-thin text-2xl tracking-wider">
                        <h1>Login</h1>
                    </div>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Email
                        </label>
                        <input
                            type="email"
                            name="email"
                            placeholder="Enter email"
                            onChange={e => setEmail(e.target.value)}
                            value={email}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Password
                        </label>
                        <input
                            type="password"
                            name="password"
                            placeholder="Enter password"
                            onChange={e => setPassword(e.target.value)}
                            value={password}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>

                    <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
                        <button
                            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6"
                            onClick={handleLogIn}
                        >
                            Login
                        </button>
                        <button
                            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6"
                            onClick={routeChange}>
                            Register
                        </button>
                    </div>
                </div>
            </div>
        </div>);
};

export default LoginForm;

/*
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

*/