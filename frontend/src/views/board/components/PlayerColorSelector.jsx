import React, {useEffect} from 'react';
import './PlayerColorSelector.css';
import {fetchUserColor} from "../../../api/user";
import {useDispatch} from "react-redux";
import {setUserColor} from "../../../redux/userSlice";

const PlayerColorSelector = () => {
    const dispatch = useDispatch();
    useEffect(() => {
        fetchUserColor().then(color => {
            if (color != null) dispatch(setUserColor(color))
        })
    }, []);

    return (
        <div className="color-selector">
            <div className="select-box">
                <h2>Select Your Color</h2>
                <div className="color-options">
                    <button className="color-option white" onClick={() => dispatch(setUserColor('WHITE'))}>WHITE
                    </button>
                    <button className="color-option black" onClick={() => dispatch(setUserColor('BLACK'))}>BLACK
                    </button>
                </div>
            </div>
        </div>
    );
};

export default PlayerColorSelector;