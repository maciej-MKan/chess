import React from 'react';
import './DropDownMenu.css';

const DropdownMenu = ({name, onLogout, onViewHistory, onViewPreferences}) => {
    return (
        <div className="dropdown">
            <button className="dropbtn">Hello, {name}</button>
            <div className="dropdown-content">
                <a href="#" onClick={onViewHistory}>Games history</a>
                <a href="#" onClick={onViewPreferences}>User preferences</a>
                <a href="#" onClick={onLogout}>Logout</a>
            </div>
        </div>
    );
};

export default DropdownMenu;
