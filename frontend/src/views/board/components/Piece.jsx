import React from "react";

export const Piece = ({ type, color, selected}) => {
    return (
        <div
            className={`piece ${type.toLowerCase()} ${'piece_' + color.toLowerCase() + (selected ? '_blink' : '')}`}
            // onClick={onClick}
        >
            <span>{type}</span>
        </div>
    );
};