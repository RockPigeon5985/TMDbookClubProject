import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom'

const RegisterForm = () => {
  const navigate = useNavigate()
  const routeChange = () => {
    const path = '/'
    navigate(path)
  }

  return (
        <div className="px-8 py-8">
            <div className=" flex max-w-2xl mx-auto shadow border-b">
                <div className="px-8 py-8">
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
                        className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Last Name
                        </label>
                        <input
                            type="text"
                            name="lastName"
                            placeholder="Enter last name"
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>
                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Email
                        </label>
                        <input
                            type="email"
                            name="email"
                            placeholder="Enter email"
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
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>

                    <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
                        <button
                            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6"
                            onClick={routeChange}
                        >
                            Register
                        </button>
                    </div>
                </div>
            </div>
        </div>
  )
}

export default RegisterForm
