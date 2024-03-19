import React, {useEffect, useState} from 'react';
import {Square} from "./components/Square";
import './Chessboard.css'
import {initGame} from "../../api/game";

const Chessboard = () => {

    const [bordState, setBordState] = useState();
    const [error, setError] = useState(" ");
    const [selectedSquare, setSelectedSquare] = useState({})
    const [selectedPiece, setSelectedPiece] = useState({})

    useEffect(() => {
        initGame()
            .then((boardData) => {
                setBordState(boardData);
            })
            .catch((error) => {
                console.log("error " + error);
                setError(error.toString())
            });
    }, []);

    useEffect(() => {
        setSelectedSquare({});
    }, [selectedPiece]);

    useEffect(() => {
        if (Object.keys(selectedSquare).length !== 0 && Object.keys(selectedPiece).length !== 0){
            movePiece();
        }
    }, [selectedSquare, selectedPiece]);

    const findPiece = (row, column) => bordState.pieces.find(piece => piece.position.row === row && piece.position.column === column);
    const onSquareClick = (row, column) => {
        findPiece(row, column) ? setSelectedPiece({row, column}) : setSelectedSquare({row, column});
    }
    const checkSquareSelected = (row, column) => {
        return selectedSquare.row === row && selectedSquare.column === column;
    }

    const checkPieceSelected = (row, column) => {
        return selectedPiece.row === row && selectedPiece.column === column;
    }

    const movePiece = () => {
        console.log("move " + selectedPiece.row + " " + selectedPiece.column + " to " + selectedSquare.row + " " + selectedSquare.column);
        setSelectedPiece({});
        setSelectedSquare({});
    }
    const renderSquare = (row, column, piece) => {
        const isBlack = (row + column) % 2 === 1;
        const isSelected = checkSquareSelected(row, column);
        const isSelectedPiece = checkPieceSelected(row, column);
        return <Square
            key={`${row}-${column}`}
            color={isBlack ? 'black' : 'white'}
            piece={piece}
            onClick={() => onSquareClick(row, column)}
            selected={isSelected}
            selectedPiece={isSelectedPiece}
        />;
    };

    const renderBoard = () => {
        const board = [];
        for (let row = 0; row < 8; row++) {
            for (let column = 0; column < 8; column++) {
                const piece = findPiece(row, column);
                board.push(renderSquare(row, column, piece? piece: null));
            }
        }
        return board;
    };

    return <div className="chessboard">{bordState? renderBoard() : error}</div>;
};

export default Chessboard;
