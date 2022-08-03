import {BrowserRouter, Navigate, Route, Routes} from 'react-router-dom';
import HeaderComponent from "./components/HeaderComponent";
import LoginForm from "./components/LoginForm";
import FooterComponent from "./components/FooterComponent";
import RegisterForm from "./components/RegisterForm";
import DashBoard from "./components/DashBoard";

function App() {
    const token = localStorage.getItem("token")

    console.log(token);
    return (
        <div>
            <HeaderComponent/>
            <BrowserRouter>
                {
                    !token?
                        <Routes>
                            <Route path="*" element ={<Navigate to="/login" />} />
                            <Route path="/login" element={<LoginForm/>}/>
                            <Route path="/register" element={<RegisterForm/>}/>
                        </Routes>
                        :
                        <Routes>
                            <Route path="/dashboard" element={<DashBoard/>}/>
                        </Routes>
                }
            </BrowserRouter>
            <FooterComponent/>
        </div>
    );
}

export default App;
