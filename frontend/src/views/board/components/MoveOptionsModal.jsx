import React from 'react';
import Modal from 'react-modal';
import './MovesHistory.css'

const MoveOptionsModal = ({isOpen, onClose, onViewState, onRevertToMove}) => {
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel="Move Options"
            className="react-modal-content"
            overlayClassName="react-modal-overlay"
        >
            <div className="moves-history-modal">
                <h2>Move Options</h2>
                <button onClick={onViewState}>View State</button>
                <button onClick={onRevertToMove}>Revert to Move</button>
                <button onClick={onClose}>Cancel</button>
            </div>
        </Modal>
    );
};

export default MoveOptionsModal;
