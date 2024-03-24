import {Piece} from "./Piece";

export const Square = ({ color, piece, onClick, selected, active, selectedPiece}) => {
    return <div className={`square ${color + (selected? '_blink' : '')+ (active && !piece? '_active' : '')}`} onClick={onClick}>
        {piece && <Piece type={piece.type} color={piece.color} selected={selectedPiece}/>}
    </div>;
};