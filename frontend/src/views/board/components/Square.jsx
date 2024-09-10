import {Piece} from "./Piece";
import {useSelector} from "react-redux";

export const Square = ({ id, color, piece, onClick, selected, active, mark, selectedPiece}) => {
    const squareId = `square-${id}`;
    const boardState = useSelector((state) => state.game.gameState);
    const handleClick = () => {
        onClick(boardState);
        console.log(boardState);
        console.log('is active : ', active);
    }
    return <div id={squareId}
                className={`square ${color + (selected ? '_blink' : '') + (active && !piece ? '_active' : '') + (mark ? '.marked' : '')}`}
                onClick={handleClick}>
        {piece && <Piece id={piece.id} type={piece.type} color={piece.color} selected={selectedPiece}/>}
    </div>;
};