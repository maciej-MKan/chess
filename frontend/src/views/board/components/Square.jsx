import {Piece} from "./Piece";
import {useEffect} from "react";
import {state$} from "../../../rxjsstore/RxStore";

export const Square = ({ id, color, piece, onClick, selected, active, mark, selectedPiece}) => {
    const squareId = `square-${id}`;
    const boardState = state$.value.boardState;
    const handleClick = () => {
        onClick(boardState);
        console.log(boardState);
        console.log('is active : ', active);
    }
    useEffect(() => {
        const subscription = state$.subscribe();
        return () => subscription.unsubscribe();
    }, []);
    return <div id={squareId}
                className={`square ${color + (selected ? '_blink' : '') + (active && !piece ? '_active' : '') + (mark ? '.marked' : '')}`}
                onClick={handleClick}>
        {piece && <Piece id={piece.id} type={piece.type} color={piece.color} selected={selectedPiece}/>}
    </div>;
};