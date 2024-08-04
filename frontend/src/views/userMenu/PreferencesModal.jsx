import React from 'react';
import Modal from "react-modal";
import './PreferencesModal.css'

const PreferencesModal = ({isOpen, onClose, userName}) => {
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel={userName + "'s preferences"}
            className="react-modal-content"
            overlayClassName="react-modal-overlay"
        >
            <button className="button-cancel" onClick={onClose}>close</button>
        </Modal>
    )
}

export default PreferencesModal;