import React from "react";

export const Piece = ({ id, type, color, selected}) => {
    const imgUrl = `/chess_set/${color.toLowerCase()}_${type.toLowerCase()}.webp`;
    return (
        <div
            id={id}
            className={`piece ${type.toLowerCase()} ${'piece_' + color.toLowerCase() + (selected ? '_blink' : '')}`}
        >
            <img className={'piece_image'} src={imgUrl} alt={type}/>
        </div>
    );
};