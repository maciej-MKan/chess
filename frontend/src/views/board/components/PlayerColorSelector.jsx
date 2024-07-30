import React, {useEffect} from 'react';
import './PlayerColorSelector.css';
import {fetchUserColor} from "../../../api/user";

const PlayerColorSelector = ({onColorSelect}) => {
    useEffect(() => {
        fetchUserColor().then(color => onColorSelect(color))
    }, []);

    return (
        <div className="color-selector">
            <div className="select-box">
                <h2>Select Your Color</h2>
                <div className="color-options">
                    <button className="color-option white" onClick={() => onColorSelect('WHITE')}>WHITE</button>
                    <button className="color-option black" onClick={() => onColorSelect('BLACK')}>BLACK</button>
                </div>
            </div>
        </div>
    );
};

export default PlayerColorSelector;