import React from 'react';
import './DropDownMenu.css';
import {startNewGame} from "../utils/utils";
import {useNavigate} from "react-router-dom";

const DropdownMenu = ({name, onLogout, onViewHistory, onViewPreferences}) => {
    const navigate = useNavigate();
    return (
        <div className="dropdown">
            <button className="dropbtn">Hello, {name}</button>
            <div className="dropdown-content">
                <a href="#" onClick={onViewHistory}>Games history</a>
                <a href="#" onClick={onViewPreferences}>User preferences</a>
                <a href="#" onClick={onLogout}>Logout</a>
                <a href="#" onClick={() => startNewGame(navigate)}>Start new game</a>
            </div>
        </div>
    );
};

export default DropdownMenu;
