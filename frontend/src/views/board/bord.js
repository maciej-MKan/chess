import React, {useEffect, useState} from 'react';
import {Square} from "./components/Square";
import './Chessboard.css'
import {initGame} from "../../api/game";

const Chessboard = () => {

    const [bordState, setBordState] = useState();
    const [error, setError] = useState(" ");

    useEffect(() => {
        console.log("init");
        initGame()
            .then((boardData) => {
                setBordState(boardData);
            })
            .catch((error) => {
                console.log("error " + error);
                setError(error.toString())
            });
    }, []);
    const renderSquare = (row, column, piece) => {
        const isBlack = (row + column) % 2 === 1;
        return <Square key={`${row}-${column}`} color={isBlack ? 'black' : 'white'} piece={piece}/>;
    };

    const renderBoard = () => {
        const board = [];
        for (let row = 0; row < 8; row++) {
            for (let column = 0; column < 8; column++) {
                const piece = bordState.pieces.find(piece => piece.position.row === row && piece.position.column === column);
                board.push(renderSquare(row, column, piece? piece: null));
            }
        }
        return board;
    };

    return <div className="chessboard">{bordState? renderBoard() : error}</div>;
};

export default Chessboard;
