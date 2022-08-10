import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const LoginForm = () => {
    const navigate = useNavigate()
    const handleRegisterNav = () => {
        navigate('/register')
    }

    const [loginFormValues, setLoginFormValues] = useState({email: "", password: ""})
    const [loginFormErrors, setLoginFormError] = useState({email: "", password: "", credentials: ""})

    const validate = (values) => {
        const errors = {}
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i

        if (!values.email) {
            errors.email = "Email is required!"
        } else if (!regex.test(values.email)) {
            errors.email = "This is not a valid email format!"
        }else {
            errors.email = ""
        }

        if (!values.password) {
            errors.password = "Password is required"
        }else {
            errors.password = ""
        }

        return errors
    }

    const handleLogIn = () => {
        setLoginFormError(validate(loginFormValues))

        if (JSON.stringify(loginFormErrors) === JSON.stringify({email: "", password: ""})) {
            const reqBody = {
                userName: loginFormValues.email,
                password: loginFormValues.password
            }

            fetch('http://localhost:8080/auth/login', {
                headers: {
                    'Content-Type': 'application/json',
                    Accept: 'application/json'
                },
                method: 'POST',
                body: JSON.stringify(reqBody)
            })
                .then(response => {
                    if (response.status === 200) {
                        return response.headers
                    } else {
                        return Promise.reject('Invalid credentials')
                    }
                })
                .then(data => {
                    localStorage.setItem('token', data.get('authorization'))
                    dispatchEvent(new Event("ChangedToken"))
                    navigate('/dashboard')
                })
                .catch(e => setLoginFormError({...loginFormErrors, credentials: e}))
        }
    }

    return (
        <div className="px-8 py-8 flex">
            <div className="max-w-2xl mx-auto shadow border-b">
                <div className="px-8 py-8">
                    <div className="font-thin text-2xl tracking-wider">
                        <h1>Login</h1>
                    </div>
                    <p className="text-red-700">{loginFormErrors.credentials}</p>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Email
                        </label>
                        <input
                            type="email"
                            name="email"
                            placeholder="Enter email"
                            onChange={e => setLoginFormValues({...loginFormValues, email: e.target.value})}
                            value={loginFormValues.email}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{loginFormErrors.email}</p>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Password
                        </label>
                        <input
                            type="password"
                            name="password"
                            placeholder="Enter password"
                            onChange={e => setLoginFormValues({...loginFormValues, password: e.target.value})}
                            value={loginFormValues.password}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{loginFormErrors.password}</p>
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
        </div>)
}
export default LoginForm
