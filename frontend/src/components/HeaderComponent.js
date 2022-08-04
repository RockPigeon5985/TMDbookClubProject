import React from 'react'

const HeaderComponent = () => {
/*
    const location = useLocation()
    const navigate = useNavigate()

    useEffect(() => {
        if(!localStorage.getItem('token') && location.pathname !== '/login' &&
            location.pathname !== '/register' && location.pathname !== '/'){
            navigate('/login')
        }
    },[location] )
*/

  return (
        <div className="bg-gray-800">
            <div className="h-16 px-8 flex items-center">
                <p className="text-white font-bold">TMD Book Club</p>
            </div>
        </div>
  )
}

export default HeaderComponent
