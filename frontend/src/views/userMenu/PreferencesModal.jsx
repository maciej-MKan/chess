import React, {useEffect, useState} from 'react';
import Modal from "react-modal";
import './PreferencesModal.css'
import {useDispatch, useSelector} from "react-redux";
import {setUserColor, setUsername} from "../../redux/userSlice";

const PreferencesModal = ({isOpen, onClose}) => {
    const dispatch = useDispatch();
    const username = useSelector((state) => state.user.username);
    const color = useSelector((state) => state.user.userColor);
    const [newUsername, setNewUsername] = useState('');
    const [newColor, setNewColor] = useState('');
    const labelContent = username + "'s preferences";

    const handleSave = () => {
        if (username !== newUsername) {
            console.log("username changed : ", newUsername);
            dispatch(setUsername(username));
        }
        if (color !== newColor) {
            console.log("color changed : ", color);
            dispatch(setUserColor(newColor));
        }
        onClose();
    }
    const handleClose = () => {
        setNewUsername(username);
        setNewColor(color);
        onClose();
    }
    useEffect(() => {
        setNewUsername(username);
    }, [username]);
    useEffect(() => {
        setNewColor(color);
    }, [color]);

    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel={labelContent}
            className="react-modal-content"
            overlayClassName="react-modal-overlay"
        >
            <h3>{labelContent}</h3>
            <div className="input-group">
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    id="username"
                    value={newUsername}
                    onChange={(e) => setNewUsername(e.target.value)}
                />
            </div>
            <div className="input-group">
                <label htmlFor="color">Default Color:</label>
                <div className="color-options">
                    <label>
                        <input
                            type="radio"
                            value="WHITE"
                            checked={newColor === 'WHITE'}
                            onChange={(e) => setNewColor(e.target.value)}
                        />
                        White
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="BLACK"
                            checked={newColor === 'BLACK'}
                            onChange={(e) => setNewColor(e.target.value)}
                        />
                        Black
                    </label>
                </div>
            </div>
            <button className="button-save" onClick={handleSave}>save</button>
            <button className="button-cancel" onClick={handleClose}>close</button>
        </Modal>
    )
}

export default PreferencesModal;