import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './UserStatus.css';
import {fetchUserDetails, sendLogout} from "../../../api/user";
import DropDownMenu from "../../userMenu/DropDownMenu";
import PreferencesModal from "../../userMenu/PreferencesModal";
import {useDispatch, useSelector} from "react-redux";
import {logIn, logOut} from "../../../redux/authSlice";
import {setUsername} from "../../../redux/userSlice";

const UserStatus = () => {
    const navigate = useNavigate();
    const [preferencesVisible, setPreferencesVisible] = useState(false);
    const dispatch = useDispatch();
    const isLoginIn = useSelector((state) => state.auth.isLoginIn);
    const username = useSelector((state) => state.user.username);

    useEffect(() => {
        fetchUserDetails().then(user => {
            dispatch(setUsername(user.name));
            dispatch(logIn());
        })
    }, []);

    useEffect(() => {
        console.log("isLoginIn : ", isLoginIn);
    }, [isLoginIn]);

    const handleLogin = () => {
        navigate('/');
    };

    const handleLogout = () => {
        dispatch(setUsername(""));
        dispatch(logOut());
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
                onClose={() => setPreferencesVisible(false)}
            />
        </>
    );
};

export default UserStatus;