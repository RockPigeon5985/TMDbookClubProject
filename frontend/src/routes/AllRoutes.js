import React from 'react'
import {Route, Navigate, Routes} from 'react-router-dom'
import LoginForm from '../components/LoginForm'
import RegisterForm from '../components/RegisterForm'
import DashBoard from '../components/DashBoard'
import PrivateRoutes from './PrivateRoutes'

const AllRoutes = () => {
  return (
        <div>
                <Routes>
                    <Route element={<PrivateRoutes/>}>
                        <Route path="/dashboard" element={<DashBoard/>}/>
                    </Route>

                    <Route path="/" element={<Navigate to="/login"/>}/>
                    <Route path="/login" element={<LoginForm/>}/>
                    <Route path="/register" element={<RegisterForm/>}/>
                </Routes>
        </div>
  )
}

export default AllRoutes
