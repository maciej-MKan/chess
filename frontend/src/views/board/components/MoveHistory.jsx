import React from 'react';

const MoveHistory = ({ moves, onMoveClick }) => {
    return (
        <div className="moves-history">
            <h2>Moves History</h2>
            <ul>
                {moves.map((move, index) => (
                    <li key={index} onClick={() => onMoveClick(index)}>
                        {move}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MoveHistory;
