import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './UserStatus.css';
import {fetchUserDetails, sendLogout} from "../../../api/user";
import DropDownMenu from "../../userMenu/DropDownMenu";
import PreferencesModal from "../../userMenu/PreferencesModal";

const UserStatus = ({isLoginIn}) => {
    const [username, setUsername] = useState(null);
    const navigate = useNavigate();
    const [preferencesVisible, setPreferencesVisible] = useState(false);

    useEffect(() => {
        isLoginIn = false;
        fetchUserDetails().then(user => {
            setUsername(user.name);
            isLoginIn = true;
        })
    }, []);

    const handleLogin = () => {
        navigate('/');
    };

    const handleLogout = () => {
        setUsername("");
        isLoginIn = false;
        document.cookie = "JSESSIONID=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/;";
        sendLogout().then(() => {
        });
        navigate("/");
    }
    const handleGamesHistory = () => {
    }
    const handleUserPreferences = () => {
        setPreferencesVisible(true);
    }

    return (
        <>
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
            <PreferencesModal
                isOpen={preferencesVisible}
                onClose={setPreferencesVisible(false)}
                userName={username}
            />
        </>
    );
};

export default UserStatus;