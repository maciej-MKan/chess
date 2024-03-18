import React from "react";

export const Piece = ({ type, color }) => {
    return (
        <div className={`piece ${type.toLowerCase()} ${'piece_' + color.toLowerCase()}`}>
            <span>{type}</span>
        </div>
    );
};