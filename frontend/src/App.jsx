import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import GameScreen from "./views/board/GameScreen";
import Login from "./views/login/components/Login";
import Title from "./views/utils/components/Title";
import "./App.css"
import {ToastContainer} from "react-toastify";
import {LoginSuccess} from "./services/login.service";


const App = () => {
    return (
        <div className="screen-container">
            <Title/>
            <Router>
                <Routes>
                    <Route path="/game" element={<GameScreen/>}></Route>
                    <Route path="/" element={<Login/>}></Route>
                    <Route path="/login_success" element={<LoginSuccess/>}></Route>
                </Routes>
            </Router>
            <ToastContainer/>
        </div>
    );
}

export default App;
