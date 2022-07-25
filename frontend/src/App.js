import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import HeaderComponent from "./components/HeaderComponent";
import LoginForm from "./components/LoginForm";
import FooterComponent from "./components/FooterComponent";

function App() {

  return (
      <div>
        <Router>
          <HeaderComponent />
            <div className = "container">
              <Routes>
                <Route path = "/" element = {<LoginForm />}></Route>
              </Routes>
            </div>
            <FooterComponent/>
        </Router>
      </div>

  );
}

export default App;
