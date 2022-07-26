import {BrowserRouter, Route, Routes} from 'react-router-dom';
import HeaderComponent from "./components/HeaderComponent";
import LoginForm from "./components/LoginForm";
import FooterComponent from "./components/FooterComponent";
import RegisterForm from "./components/RegisterForm";

function App() {

  return (
      <div>
        <BrowserRouter>
          <HeaderComponent />
            <div className = "container">
              <Routes>
                  <Route path = "/" element = {<LoginForm />}></Route>
                  <Route path = "/register" element = {<RegisterForm />}></Route>
              </Routes>
            </div>
            <FooterComponent/>
        </BrowserRouter>
      </div>

  );
}

export default App;
