import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom'
import "./Login.css"
import Oauth2Component from "./Oauth2Component";
import GuestComponent from "./GuestComponent";
import {useSelector} from "react-redux";
import {fetchUserDetails} from "../../../api/user";

const Login = () => {
    const navigate = useNavigate();
    const [hasToken, setHasToken] = useState(null);
    const username = useSelector((state) => state.user.username);

    useEffect(() => {
        if (username !== '') {
            fetchUserDetails()
                .then(user => {
                    if (user.name === username) {
                        setHasToken(true);
                        console.log("Token valid. User login in");
                        navigate("/game");
                    } else {
                        setHasToken(false);
                        console.log("Token not valid.")
                    }
                })
                .catch(error => {
                    console.log('error : ' + error);
                    setHasToken(false);
                })
        } else {
            setHasToken(false);
        }
    }, [navigate]);

    if (hasToken === null) {
        return <div>checking token...</div>;
    }

    return (
        <div className="login-container">
            <Oauth2Component/>
            <GuestComponent/>
        </div>
    )
}

export default Login;
