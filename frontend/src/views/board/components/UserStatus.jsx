import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './UserStatus.css';
import {fetchUserDetails} from "../../../api/user";

const UserStatus = () => {
    const [username, setUsername] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetchUserDetails().then(user => setUsername(user.name))
    }, []);

    const handleLogin = () => {
        navigate('/login');
    };

    return (
        <div className="user-status-container">
            {username ? (
                <div className="username">Hello {username}</div>
            ) : (
                <button className="login-button" onClick={handleLogin}>Login</button>
            )}
        </div>
    );
};

export default UserStatus;