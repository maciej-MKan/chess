import React from 'react';
import Modal from 'react-modal';
import './MovesHistory.css'
import '../Chessboard.css'

const MoveOptionsModal = ({isOpen, onClose, onViewState, onRevertToMove, board}) => {
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
            <div className="chessboard">{board}</div>
        </Modal>
    );
};

export default MoveOptionsModal;
