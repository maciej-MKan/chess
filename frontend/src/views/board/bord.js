import React from 'react';
import {Square} from "./components/Square";
import './Chessboard.css'

const Chessboard = ({pieces}) => {
    const renderSquare = (row, column) => {
        const isBlack = (row + column) % 2 === 1;
        return <Square key={`${row}-${column}`} color={isBlack ? 'black' : 'white'}/>;
    };

    const renderBoard = () => {
        const board = [];
        for (let row = 0; row < 8; row++) {
            for (let column = 0; column < 8; column++) {
                board.push(renderSquare(row, column));
            }
        }
        pieces.forEach((piece) => {
            // const {position} = piece;
            const {row, column} = piece.position;
            const squareIndex = row * 8 + column;
            board[squareIndex] = React.cloneElement(board[squareIndex], {piece: piece });
        });
        return board;
    };

    return <div className="chessboard">{renderBoard()}</div>;
};

export default Chessboard;
