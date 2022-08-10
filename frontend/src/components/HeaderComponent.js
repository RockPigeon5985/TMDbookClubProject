import React, {useState}from 'react'
import {useNavigate} from "react-router-dom";

const HeaderComponent = () => {
    const [token, setToken] = useState(localStorage.getItem('token'))
    const navigate = useNavigate()

    window.addEventListener("ChangedToken", () => {
       setToken(localStorage.getItem('token'))
    })

    const handleLogOut = () => {
        localStorage.clear()
        dispatchEvent(new Event("ChangedToken"))
        navigate('/login')
    }

    return (

        <div className="bg-gray-800">
            <div className="h-16 px-8 flex items-center">
                <p className="text-white font-bold">TMD Book Club</p>
                {
                    token ? <button
                        className="rounded text-white font-semibold bg-orange-400 hover:bg-orange-700 py-2 px-6 right-10 absolute"
                        onClick={handleLogOut}>
                        LogOut
                    </button> : null
                }
            </div>
        </div>

    )
}

export default HeaderComponent
