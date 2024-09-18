import React from 'react';
import Modal from 'react-modal';
import {Piece} from './Piece';
import './PawnPromotion.css';

const PawnPromotionModal = ({ isOpen, onClose, piecesList, onFigureSelect }) => {
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel="Pawn Promotion"
            className="react-modal-content"
            overlayClassName="react-modal-overlay"
        >
            <h2>Promote Pawn</h2>
            <div className="promote-options">
                {piecesList.map((piece, index) => (
                    <div key={index} onClick={() => onFigureSelect(piece)}>
                        <Piece id={piece.id} type={piece.type} color={piece.color} selected={false} />
                    </div>
                ))}
            </div>
        </Modal>
    );
};

export default PawnPromotionModal;
