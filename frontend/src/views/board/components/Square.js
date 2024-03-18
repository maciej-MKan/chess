import React from "react";
import {Piece} from "./Piece";

export const Square = ({ color, piece }) => {
    return <div className={`square ${color}`}>
        {piece && <Piece type={piece.type} color={piece.color}/>}
    </div>;
};