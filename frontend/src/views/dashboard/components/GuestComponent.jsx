import React from "react";
import "./Login.css"
import {useNavigate} from "react-router-dom";

const GuestComponent = () => {

    const navigate = useNavigate();

    const handleGuestPlay = () => {
        navigate("/");
    }

    return (
        <div className="guest-container">
            <h2>Or</h2>
            <div className="guest-box">
                <button className="button-guest" onClick={() => handleGuestPlay('google')}>Play as guest</button>
            </div>
        </div>
    );
}

export default GuestComponent;