import React from "react";
import "./Login.css"

const backendUri = import.meta.env.VITE_BACKEND_URI
const frontUri = import.meta.env.VITE_FRONTEND_URI

const Oauth2Component = () => {
    const handleLogin = (provider) => {
        window.location.href = `${backendUri}/oauth2/authorization/${provider}`;
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