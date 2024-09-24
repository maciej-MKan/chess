import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom'
import "./Login.css"
import Oauth2Component from "./Oauth2Component";
import GuestComponent from "./GuestComponent";
import {fetchUserDetails} from "../../../api/user";
import {setUsername} from "../../../redux/userSlice";
import {logIn} from "../../../redux/authSlice";
import {useDispatch} from "react-redux";

const Login = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [hasToken, setHasToken] = useState(null);
    const username = sessionStorage.getItem("username");

    useEffect(() => {
        if (username !== '' && username !== null) {
            fetchUserDetails()
                .then(user => {
                    if (user.name === username) {
                        setHasToken(true);
                        console.log("Token valid. User login in");
                        dispatch(setUsername(user.name));
                        dispatch(logIn());
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
    }, []);

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
