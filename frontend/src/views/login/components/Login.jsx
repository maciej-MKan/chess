import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom'
import "./Login.css"
import Oauth2Component from "./Oauth2Component";
import GuestComponent from "./GuestComponent";

const Login = () => {
    const navigate = useNavigate();
    const [hasToken, setHasToken] = useState(null);

    useEffect(() => {
        const token = document.cookie.split('; ').find(row => row.startsWith('token='));
        sessionStorage.clear();

        if (token) {
            setHasToken(true);
            navigate("/game");
            console.log("found token")
        } else {
            setHasToken(false);
            console.log("token not found render login page")
        }
    }, [navigate]);

    if (hasToken === null) {
        return <div>checking token...</div>;
    }

    if (hasToken) {
        return null; // or a loading indicator
    }

    return (
        <div className="login-container">
            <Oauth2Component/>
            <GuestComponent/>
        </div>
    )
}

export default Login;
