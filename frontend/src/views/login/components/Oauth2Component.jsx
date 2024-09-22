import React from "react";
import "./Login.css"
import {setUsername} from "../../../redux/userSlice";
import {useDispatch} from "react-redux";

const backendUri = import.meta.env.VITE_BACKEND_URI

const Oauth2Component = () => {
    const dispatch = useDispatch();
    const handleLogin = (provider) => {
        try {
            window.location.href = `${backendUri}/oauth2/authorization/${provider}`;
            dispatch(setUsername('LOGIN'));
            console.log('set user name null');
        } catch (error) {
            console.log('error : ' + error)
        }
    };

    return (
        <div className="oauth2-container">
            <h2>Login</h2>
            <div className="provider-box">
                <button className="button-google" onClick={() => handleLogin('google')}>Login with Google</button>
                <button className="button-gh" onClick={() => handleLogin('github')}>Login with GitHub</button>
            </div>
        </div>
    );
}

export default Oauth2Component;