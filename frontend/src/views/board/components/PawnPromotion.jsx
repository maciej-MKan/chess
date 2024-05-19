import React, {useState} from "react";
import {Piece} from "./Piece";

const PawnPromotionModal = ({isOpen, onClose, piecesList, onFigureSelect}) => {
    const [modalOpen, setModalOpen] = useState(isOpen);

    const closeModal = () => {
        setModalOpen(false);
        onClose();
    };

    if (!modalOpen) {
        return null;
    }

    return (
        <>
            <h2>Promote Pawn</h2>
            <div className="promote-options">
                {piecesList.map((figure) => (
                    <div onClick={onFigureSelect(figure)}>
                        <Piece
                            key={figure.id}
                            id={figure.id}
                            type={figure.type}
                            color={figure.color}
                            selected={false}
                        />
                    </div>
                ))}
            </div>
            <button className="close-button" onClick={closeModal}>Close</button>
        </>
    );
};

export default PawnPromotionModal;