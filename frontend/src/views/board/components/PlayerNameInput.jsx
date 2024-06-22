import React, { useState } from 'react';
import './PlayerNameInput.css';

const PlayerNameInput = ({ onNameSubmit: onEnter }) => {
    const [name, setName] = useState('');

    const handleChange = (event) => {
        setName(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (name.trim()) {
            onEnter(name);
        }
    };

    return (
        <div className="player-name-input">
            <h2>Enter Your Name</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={name}
                    onChange={handleChange}
                    placeholder="Your Name"
                    className="name-input"
                />
                <button type="submit" className="submit-button">Submit</button>
            </form>
        </div>
    );
};

export default PlayerNameInput;
