import React, {useEffect, useState} from 'react';
import {Square} from "./components/Square";
import './Chessboard.css'
import {getAvailableMoves, getComputerMove, getGameState, initGame} from "../../api/game";
import {isEmpty} from "../utils";

const Chessboard = () => {

    const playerColor = 'BLACK';
    const [boardState, setBoardState] = useState();
    const [availableMoves, setAvailableMoves] = useState({});
    const [error, setError] = useState(" ");
    const [waitApi, setWaitApi] = useState(false);
    const [gameOver, setGameOver] = useState(false);
    const [winner, setWinner] = useState(null)
    const [selectedSquare, setSelectedSquare] = useState({});
    const [selectedPiece, setSelectedPiece] = useState({});

    useEffect(() => {
        setWaitApi(true)
        initGame()
            .then((boardData) => {
                setBoardState(boardData);
                fetchAvailableMoves(boardData);
            })
            .catch((error) => {
                console.log("error " + error);
                setError(error.toString())
            })
            .finally(() => setWaitApi(false));
    }, []);

    useEffect(() => {
        if (boardState && !isEmpty(boardState)) {
            getGameState(boardState)
                .then((gameStateData) => {
                    if (gameStateData.isGameOver) {
                        setGameOver(true);
                        setWinner(gameStateData.winner);
                        setAvailableMoves({})
                    }
                        console.log('game over: ' + gameStateData.isGameOver);
                    }
                )
                .catch((error) => {
                    console.log("error " + error);
                    setError(error.toString())
                })
        }
    }, [boardState]);

    const fetchAvailableMoves = (board) => {
        console.log('try get available moves' + board)
        if (board && !isEmpty(board) && !gameOver) {
            getAvailableMoves(board)
                .then((availableMovesData) =>
                    setAvailableMoves(!gameOver ? (availableMovesData.availableMoves) : {})
                )
                .catch((error) => {
                    console.log("error " + error);
                    setError(error.toString())
                })
        }
    };

    useEffect(() => {
        setSelectedSquare({});
    }, [selectedPiece]);

    useEffect(() => {
        if (!isEmpty(selectedSquare) && !isEmpty(selectedPiece)) {
            playerMove();
        } // eslint-disable-next-line
    }, [selectedSquare, selectedPiece]);


    const findPiece = (row, column) => boardState.pieces.find(piece => piece.position.row === row && piece.position.column === column);
    const removePiece = (piece) => {
        if (piece) {
            console.log(piece);
            boardState.pieces.remove(piece);
        }
    }
    const onSquareClick = (row, column) => {
        if (!isEmpty(selectedSquare) && row === selectedSquare.row && column === selectedSquare.column) {
            setSelectedSquare({})
        } else if (!isEmpty(selectedPiece) && row === selectedPiece.row && column === selectedPiece.column) {
            setSelectedPiece({})
        } else {
            const piece = findPiece(row, column);
            (piece && piece.color === playerColor) ? setSelectedPiece({row, column}) : setSelectedSquare({row, column});
        }
    }
    const checkSquareSelected = (row, column) => {
        return selectedSquare.row === row && selectedSquare.column === column;
    }

    const checkPieceSelected = (row, column) => {
        return selectedPiece.row === row && selectedPiece.column === column;
    }

    const checkSquareActive = (row, column) => {
        if (findPiece(row, column)?.color === playerColor) return true;
        if (!isEmpty(selectedPiece)) {
            const id = findPiece(selectedPiece.row, selectedPiece.column)?.id;
            return (availableMoves[id]?.some(move => move.row === row && move.column === column));
        }
        return false;
    };
    const playerMove = () => {
        console.log("move " + selectedPiece.row + " " + selectedPiece.column + " to " + selectedSquare.row + " " + selectedSquare.column);
        const piece = findPiece(selectedPiece.row, selectedPiece.column);
        removePiece(findPiece(selectedSquare.row, selectedSquare.column))
        piece.position.row = selectedSquare.row;
        piece.position.column = selectedSquare.column;
        piece.moved = true;
        console.log(piece.moved)
        console.log(boardState);
        setSelectedPiece({});
        setSelectedSquare({});
        computerMove();
    }
    const computerMove = () => {
        setWaitApi(true);
        getComputerMove(boardState)
            .then(boardData => {
                setBoardState(boardData);
                fetchAvailableMoves(boardData);
            })
            .catch((error) => {
                console.log("error " + error);
                setError(error.toString())
            })
            .finally(() => setWaitApi(false));
    };
    const renderSquare = (row, column, piece) => {
        const isBlack = (row + column) % 2 === 1;
        const isSelected = checkSquareSelected(row, column);
        const isSelectedPiece = checkPieceSelected(row, column);
        const isActive = checkSquareActive(row, column);
        return <Square
            key={`${row}-${column}`}
            color={isBlack ? 'black' : 'white'}
            piece={piece}
            onClick={() => {
                if (!waitApi && isActive) onSquareClick(row, column);
            }}
            selected={isSelected}
            selectedPiece={isSelectedPiece}
            active={isActive}
        />;
    };

    const renderBoard = () => {
        const board = [];
        for (let row = 0; row < 8; row++) {
            for (let column = 0; column < 8; column++) {
                const piece = findPiece(row, column);
                board.push(renderSquare(row, column, piece ? piece : null));
            }
        }
        return board;
    };

    return (
        <div>
            <div className="chessboard">{(error !== " ") ? error : boardState ? renderBoard() : error}</div>
            {waitApi && <div className='loadingLabel'>Wait for API response</div>}
            {gameOver && <div className='gameOver'>Game Over, the winner is {winner}</div>}
        </div>
    );
};

export default Chessboard;
