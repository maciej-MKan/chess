import {Piece} from "./Piece";

export const Square = ({ id, color, piece, onClick, selected, active, mark, selectedPiece}) => {
    const squareId = `square-${id}`;
    return <div id={squareId} className={`square ${color + (selected? '_blink' : '') + (active && !piece? '_active' : '') + (mark? '.marked' : '') }`} onClick={onClick}>
        {piece && <Piece id={piece.id} type={piece.type} color={piece.color} selected={selectedPiece}/>}
    </div>;
};