import {Piece} from "./Piece";

export const Square = ({ color, piece, onClick, selected, selectedPiece}) => {
    return <div className={`square ${color + (selected? '_blink' : '')}`} onClick={onClick}>
        {piece && <Piece type={piece.type} color={piece.color} selected={selectedPiece}/>}
    </div>;
};