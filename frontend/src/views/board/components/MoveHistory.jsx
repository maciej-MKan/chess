import React from 'react';
import './MovesHistory.css'


const MoveHistory = ({moves, onMoveClick}) => {
    return (
        <div className="moves-history">
            <h2>Moves History</h2>
            <ul>
                {moves.map((move, index) => (
                    <li key={index} onClick={() => onMoveClick(index)} className="historical-move">
                        <div>
                            {move.desc}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MoveHistory;
