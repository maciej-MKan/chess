import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Chessboard from "./views/board/board";
import Login from "./views/login/components/Login";
import Title from "./views/utils/components/Title";
import "./App.css"


const App = () => {
    return (
        <div className="screen-container">
            <Title/>
            <Router>
                <Routes>
                    <Route path="/" element={<Chessboard/>}></Route>
                    <Route path="/login" element={<Login/>}></Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
