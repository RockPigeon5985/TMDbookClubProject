import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const RegisterForm = () => {
    const navigate = useNavigate()
    const [registerFormValues, setRegisterFormValues] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: ""
    })
    const [registerFormErrors, setRegisterFormError] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: ""
    })

    const handleLogInNav = () => {
        navigate('/login')
    }

    const validate = (values) => {
        const errors = {}
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i

        if (!values.firstName) {
            errors.firstName = "First name is required"
        } else {
            errors.firstName = ""
        }

        if (!values.lastName) {
            errors.lastName = "Last name is required"
        } else {
            errors.lastName = ""
        }

        if (!values.email) {
            errors.email = "Email is required!"
        } else if (!regex.test(values.email)) {
            errors.email = "This is not a valid email format!"
        } else {
            errors.email = ""
        }

        if (!values.password) {
            errors.password = "Password is required"
        } else if (values.password === values.confirmPassword) {
            errors.password = "Password and confirmation password wrong"
        } else {
            errors.password = ""
        }

        if (!values.password) {
            errors.password = "Confirmation password is required"
        } else {
            errors.password = ""
        }
        return errors
    }


    const handleRegister = () => {
        setRegisterFormError(validate(registerFormValues))

        if (JSON.stringify(registerFormErrors) === JSON.stringify({
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            confirmPassword: ""
        })) {
            const reqBody = {
                first_name: registerFormValues.firstName,
                last_name: registerFormValues.lastName,
                email: registerFormValues.email,
                password: registerFormValues.password
            }

            if (registerFormValues.password !== registerFormValues.confirmPassword) {

            } else {
                fetch(`http://localhost:8080/user/create`, {
                    headers: {
                        'Content-Type': 'application/json',
                        Accept: 'application/json'
                    },
                    method: 'POST',
                    body: JSON.stringify(reqBody)
                })
                    .then(response => {
                        if (response.status === 200) {
                            navigate('/login')
                        } else {
                            setRegisterFormError({...registerFormErrors, email: "Existing email"})
                        }
                    })
            }
        }
    }

    return (
        <div className="px-4 py-4 flex">
            <div className=" flex mx-auto shadow border-b">
                <div className="px-6 py-6">
                    <div className="font-thin text-2xl tracking-wider">
                        <h1>Register</h1>
                    </div>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            First Name
                        </label>
                        <input
                            type="text"
                            name="firstName"
                            placeholder="Enter first name"
                            onChange={e => setRegisterFormValues({...registerFormValues, firstName: e.target.value})}
                            value={registerFormValues.firstName}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{registerFormErrors.firstName}</p>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Last Name
                        </label>
                        <input
                            type="text"
                            name="lastName"
                            placeholder="Enter last name"
                            onChange={e => setRegisterFormValues({...registerFormValues, lastName: e.target.value})}
                            value={registerFormValues.lastName}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{registerFormErrors.lastName}</p>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Email
                        </label>
                        <input
                            type="email"
                            name="email"
                            placeholder="Enter email"
                            onChange={e => setRegisterFormValues({...registerFormValues, email: e.target.value})}
                            value={registerFormValues.email}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{registerFormErrors.email}</p>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Password
                        </label>
                        <input
                            type="password"
                            name="password"
                            placeholder="Enter password"
                            onChange={e => setRegisterFormValues({...registerFormValues, password: e.target.value})}
                            value={registerFormValues.password}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{registerFormErrors.password}</p>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Confirm Password
                        </label>
                        <input
                            type="password"
                            name="password"
                            placeholder="Enter password"
                            onChange={e => setRegisterFormValues({
                                ...registerFormValues,
                                confirmPassword: e.target.value
                            })}
                            value={registerFormValues.confirmPassword}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <p className="text-red-700">{registerFormErrors.confirmPassword}</p>
                    <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
                        <button
                            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6"
                            onClick={handleRegister}
                        >
                            Register
                        </button>

                        <button
                            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6"
                            onClick={handleLogInNav}
                        >
                            Login
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default RegisterForm
