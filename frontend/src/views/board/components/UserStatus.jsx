import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './UserStatus.css';
import {fetchUserDetails, sendLogout} from "../../../api/user";
import DropDownMenu from "../../userMenu/DropDownMenu";

const UserStatus = () => {
    const [username, setUsername] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetchUserDetails().then(user => setUsername(user.name))
    }, []);

    const handleLogin = () => {
        navigate('/');
    };

    const handleLogout = () => {
        setUsername("");
        document.cookie = "JSESSIONID=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/;";
        sendLogout().then(() => {
        });
        navigate("/");
    }
    const handleGamesHistory = () => {
    }
    const handleUserPreferences = () => {
    }

    return (
        <div className="user-status-container">
            {username ? (
                <div className="username">
                    <DropDownMenu
                        name={username}
                        onLogout={handleLogout}
                        onViewHistory={handleGamesHistory}
                        onViewPreferences={handleUserPreferences}
                    />
                </div>
            ) : (
                <button className="login-button" onClick={handleLogin}>Login</button>
            )}
        </div>
    );
};

export default UserStatus;