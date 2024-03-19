import React, {useState} from "react";

export const Piece = ({ type, color, selected}) => {
    // const [selected, setSelected] = useState(false);

    // const onClick = () => {
    //     setSelected(!selected);
    // }
    return (
        <div
            className={`piece ${type.toLowerCase()} ${'piece_' + color.toLowerCase() + (selected ? '_blink' : '')}`}
            // onClick={onClick}
        >
            <span>{type}</span>
        </div>
    );
};