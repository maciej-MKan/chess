import React from 'react';
import Modal from "react-modal";
import './PreferencesModal.css'

const PreferencesModal = ({isOpen, onClose, userName}) => {
    const labelContent = userName + "'s preferences";
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel={labelContent}
            className="react-modal-content"
            overlayClassName="react-modal-overlay"
        >
            <h3>{labelContent}</h3>
            <button className="button-cancel" onClick={onClose}>close</button>
        </Modal>
    )
}

export default PreferencesModal;