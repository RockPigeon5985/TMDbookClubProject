import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom'

const RegisterForm = () => {
  const navigate = useNavigate()
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')

  const handleLogInNav = () => {
      navigate('/login')
  }

  const handleRegister = () => {
      const reqBody = {
          first_name : firstName,
          last_name : lastName,
          email : email,
          password : password
      }

      if(password !== confirmPassword){
          alert("Wrong password")
      }else {
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
                      alert('Existing email')
                  }
              })
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
                        onChange={e => setFirstName(e.target.value)}
                        value={firstName}
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
                            onChange={e => setLastName(e.target.value)}
                            value={lastName}
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

                    <div className="items-center justify-center h-14 w-full my-4">
                        <label className="block text-gray-600 text-sm font-normal">
                            Confirm Password
                        </label>
                        <input
                            type="password"
                            name="password"
                            placeholder="Enter password"
                            onChange={e => setConfirmPassword(e.target.value)}
                            value={confirmPassword}
                            className="h-10 w-72 border mt-2 px-2 py-2"></input>
                    </div>

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
