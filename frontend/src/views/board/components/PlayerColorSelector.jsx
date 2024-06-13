import React from 'react';
import './PlayerColorSelector.css';

const PlayerColorSelector = ({ onColorSelect }) => {
    return (
        <div className="color-selector">
            <h2>Select Your Color</h2>
            <div className="color-options">
                <button className="color-option white" onClick={() => onColorSelect('WHITE')}>WHITE</button>
                <button className="color-option black" onClick={() => onColorSelect('BLACK')}>BLACK</button>
            </div>
        </div>
    );
};

export default PlayerColorSelector;