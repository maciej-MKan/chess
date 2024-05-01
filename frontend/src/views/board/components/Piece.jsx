import React from "react";

export const Piece = ({ id, type, color, selected}) => {
    return (
        <div
            id={id}
            className={`piece ${type.toLowerCase()} ${'piece_' + color.toLowerCase() + (selected ? '_blink' : '')}`}
            // onClick={onClick}
        >
            <span>{type}</span>
        </div>
    );
};