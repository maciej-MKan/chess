import React from "react";
const backendUri = import.meta.env.VITE_BACKEND_URI
const frontUri = import.meta.env.VITE_FRONTEND_URI

const Login = () => {
    const handleLogin = (provider) => {
        window.location.href = `${backendUri}/oauth2/authorization/${provider}`;
    };

    return (
        <div>
            <h1>Login</h1>
            <button onClick={() => handleLogin('google')}>Login with Google</button>
            <button onClick={() => handleLogin('github')}>Login with GitHub</button>
        </div>
    );
}

export default Login;