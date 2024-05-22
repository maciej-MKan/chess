import React from 'react';
import Modal from 'react-modal';
import { Piece } from './Piece';

Modal.setAppElement('#root');

const PawnPromotionModal = ({ isOpen, onClose, piecesList, onFigureSelect }) => {
    return (
        <Modal isOpen={isOpen} onRequestClose={onClose} contentLabel="Pawn Promotion">
            <h2>Promote Pawn</h2>
            <div className="promote-options">
                {piecesList.map((piece, index) => (
                    <div key={index} onClick={() => onFigureSelect(piece)}>
                        <Piece id={piece.id} type={piece.type} color={piece.color} selected={false} />
                    </div>
                ))}
            </div>
            <button onClick={onClose}>Cancel</button>
        </Modal>
    );
};

export default PawnPromotionModal;
