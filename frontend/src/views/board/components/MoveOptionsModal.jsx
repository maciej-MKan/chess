import React from 'react';
import Modal from 'react-modal';
import './MovesHistory.css'
import '../Chessboard.css'

const MoveOptionsModal = ({isOpen, onClose, onRevertToMove, onNext, onPreview, labelContent, board}) => {
    if (!labelContent) labelContent = "Historical Move"
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel={labelContent}
            className="react-modal-content"
            overlayClassName="react-modal-overlay"
        >
            <div className="button-container">
                <div className="moves-history-modal">
                    <h3>{labelContent}</h3>
                    <button className="button-revert" onClick={onRevertToMove}>Revert to state</button>
                    <button className="button-cancel" onClick={onClose}>Back to game</button>
                </div>
                <div className="scroll-box">
                    <button className="button-prev" onClick={onPreview}>Preview move</button>
                    <button className="button-next" onClick={onNext}>Next move</button>
                </div>
            </div>
            <div className="chessboard">{board}</div>
        </Modal>
    );
};

export default MoveOptionsModal;
