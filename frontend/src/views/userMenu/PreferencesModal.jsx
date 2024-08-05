import React, {useState} from 'react';
import Modal from "react-modal";
import './PreferencesModal.css'
import {useSelector} from "react-redux";

const PreferencesModal = ({isOpen, onClose}) => {
    const username = useSelector((state) => state.user.username);
    const [newUsername, setNewUsername] = useState(username);
    const [color, setColor] = useState(null);
    const labelContent = username + "'s preferences";

    const handleSave = () => {
        if (username !== newUsername) {
            console.log("username changed : ", newUsername);
        }
    }
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
                            value="white"
                            checked={color === 'white'}
                            onChange={(e) => setColor(e.target.value)}
                        />
                        White
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="black"
                            checked={color === 'black'}
                            onChange={(e) => setColor(e.target.value)}
                        />
                        Black
                    </label>
                </div>
            </div>
            <button className="button-save" onClick={handleSave}>save</button>
            <button className="button-cancel" onClick={onClose}>close</button>
        </Modal>
    )
}

export default PreferencesModal;