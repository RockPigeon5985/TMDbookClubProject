import React, {useState} from 'react';
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
    let navigate = useNavigate();

    const handleRegisterNav = () => {
        navigate(`/register`)
    }

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogIn = () => {
        const reqBody = {
            userName: email,
            password: password
        }

        fetch("http://localhost:8080/auth/login", {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            method: "POST",
            body: JSON.stringify(reqBody),
        })
            .then(response => {
                if(response.status === 200)
                    return response.headers;
                else
                    return Promise.reject("Invalid credentials")})
            .then(data => {
                localStorage.setItem('token', data.get("authorization"));
                navigate('/dashboard');
            })
            .catch(message => alert(message));
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
                            onClick={handleRegisterNav}>
                            Register
                        </button>
                    </div>
                </div>
            </div>
        </div>);
};

export default LoginForm;