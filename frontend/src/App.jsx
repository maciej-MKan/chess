import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Chessboard from "./views/board/board";
import Login from "./views/dashboard/components/Login";


const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Chessboard/>}></Route>
                <Route path="/login" element={<Login/>}></Route>
            </Routes>
        </Router>
    );
}

export default App;
