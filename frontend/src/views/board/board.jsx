import React, {useCallback, useEffect, useState} from 'react';
import {Square} from './components/Square';
import './Chessboard.css';
import {getAvailableMoves, getComputerMove, getGameState, initGame} from '../../api/game';
import {isEmpty} from '../utils/utils';
import PawnPromotionModal from './components/PawnPromotion';
import MoveOptionsModal from "./components/MoveOptionsModal";
import MoveHistory from "./components/MoveHistory";
import PlayerColorSelector from "./components/PlayerColorSelector";
import UserStatus from "./components/UserStatus";
import {sendUserColor} from "../../api/user";

const Chessboard = () => {
    const [playerColor, setPlayerColor] = useState('');
    const [boardState, setBoardState] = useState();
    const [availableMoves, setAvailableMoves] = useState({});
    const [error, setError] = useState('');
    const [waitApi, setWaitApi] = useState(false);
    const [gameOver, setGameOver] = useState(false);
    const [winner, setWinner] = useState(null);
    const [selectedSquare, setSelectedSquare] = useState({});
    const [selectedPiece, setSelectedPiece] = useState({});
    const [pawnPromotionOpen, setPawnPromotionOpen] = useState(false);
    const [piecesToPromote, setPiecesToPromote] = useState([]);
    const [movesHistory, setMovesHistory] = useState([]);
    const [moveOptionsOpen, setMoveOptionsOpen] = useState(false);
    const [selectedMoveIndex, setSelectedMoveIndex] = useState(0);

    useEffect(() => {
        const savedGameState = sessionStorage.getItem('chessGameState');
        if (savedGameState) {
            const {boardState, movesHistory, playerColor} = JSON.parse(savedGameState);
            setBoardState(boardState);
            setMovesHistory(movesHistory);
            setPlayerColor(playerColor);
            move(boardState);
        } else {
            if ((playerColor !== '') && (playerColor !== undefined)) {
                setWaitApi(true);
                console.log(playerColor);
                initGame(playerColor)
                    .then(boardData => {
                        setBoardState(boardData);
                        move(boardData);
                    })
                    .catch(error => {
                        console.log('error ' + error);
                        setError(error.toString());
                    })
                    .finally(() => setWaitApi(false));
            }
        }

    }, [playerColor]);

    const move = (board) => {
        console.log("player color effect")
        if (playerColor === "BLACK") {
            computerMove({
                pieces: board.pieces
            });
        } else {
            console.log(board);
            fetchAvailableMoves(board, playerColor);
        }
    };

    useEffect(() => {
        if (boardState && movesHistory.length) {
            const gameState = {boardState, movesHistory, playerColor};
            sessionStorage.setItem('chessGameState', JSON.stringify(gameState));
        }
    }, [boardState, movesHistory, playerColor]);

    const fetchGameState = useCallback((board, onExit) => {
        setWaitApi(true);
        let needOnExit = true;
        if (board && !isEmpty(board)) {
            getGameState(board)
                .then(gameStateData => {
                    if (gameStateData.gameOver.isGameOver) {
                        setGameOver(true);
                        setWinner(gameStateData.gameOver.winner);
                        setAvailableMoves({});
                        needOnExit = false;
                    } else if (gameStateData.pawnPromotion.pawn) {
                        setPawnPromotionOpen(true);
                        setPiecesToPromote(gameStateData.pawnPromotion.figuresToPromote);
                        needOnExit = false;
                    }
                })
                .catch(error => {
                    console.log('error ' + error);
                    setError(error.toString());
                })
                .finally(() => {
                    setWaitApi(false);
                    if (onExit && needOnExit) onExit(board);
                });
        }
    }, []);

    const fetchAvailableMoves = useCallback((board, color) => {
        if (board && !isEmpty(board) && !gameOver) {
            setWaitApi(true);
            getAvailableMoves(board, color)
                .then(availableMovesData => setAvailableMoves(!gameOver ? availableMovesData.availableMoves : {}))
                .catch(error => {
                    console.log('error ' + error);
                    setError(error.toString());
                })
                .finally(() => setWaitApi(false));
        }
    }, [gameOver]);

    useEffect(() => {
        setSelectedSquare({});
    }, [selectedPiece]);

    useEffect(() => {
        if (!isEmpty(selectedSquare) && !isEmpty(selectedPiece)) {
            playerMove();
        } // eslint-disable-next-line
    }, [selectedSquare, selectedPiece]);

    const findPiece = ((row, column, boardData) =>
            boardData?.pieces?.find(piece => piece.position.row === row && piece.position.column === column)
    );

    const storePlayerColor = (color) => {
        sendUserColor(color)
            .catch(error => {
                console.log('error ' + error);
                setError(error.toString()
                )}
            );
    };

    const removePiece = useCallback((piece) => {
        if (piece) {
            const newPieces = boardState.pieces.filter(p => p !== piece);
            return {...boardState, pieces: newPieces};
        }
        return boardState;
    }, [boardState]);

    const animatePieceMovement = useCallback((piece) => {
        if (piece) {
            const squareId = `square-${piece.row}-${piece.column}`;
            const square = document.querySelector(`#${squareId}`);
            if (square) {
                square.classList.add('animateMove');
                setTimeout(() => {
                    square.classList.remove('animateMove');
                }, 500);
            }
        }
    }, []);

    const onSquareClick = useCallback((row, column) => {
        const piece = findPiece(row, column, boardState);
        if (!isEmpty(selectedSquare) && row === selectedSquare.row && column === selectedSquare.column) {
            setSelectedSquare({});
        } else if (!isEmpty(selectedPiece) && row === selectedPiece.row && column === selectedPiece.column) {
            setSelectedPiece({});
        } else if (
            checkAvailableMove(selectedPiece.id, row, column) &&
            piece?.type === "ROOK" &&
            findPiece(selectedPiece.row, selectedPiece.column, boardState)?.type === "KING"
        ) {
            console.log("castling");
            setSelectedSquare({row, column});
        } else {
            const id = piece?.id;
            piece && (piece.color === playerColor) ?
                setSelectedPiece({id, row, column}) :
                setSelectedSquare({row, column});
        }
    }, [selectedSquare, selectedPiece, playerColor, findPiece]);

    const checkSquareSelected = (row, column) =>
        selectedSquare.row === row && selectedSquare.column === column;

    const checkPieceSelected = (row, column) =>
        selectedPiece.row === row && selectedPiece.column === column;

    const checkAvailableMove = (id, row, column) => availableMoves[id]?.some(move => move.row === row && move.column === column);

    const checkSquareActive = (row, column) => {
        if (findPiece(row, column, boardState)?.color === playerColor) return true;
        if (!isEmpty(selectedPiece)) {
            const id = findPiece(selectedPiece.row, selectedPiece.column, boardState)?.id;
            return checkAvailableMove(id, row, column);
        }
        return false;
    };

    const playerMove = useCallback(() => {
        const piece = findPiece(selectedPiece.row, selectedPiece.column, boardState);
        const updatedBoard = removePiece(findPiece(selectedSquare.row, selectedSquare.column, boardState));
        piece.position.row = selectedSquare.row;
        piece.position.column = selectedSquare.column;
        piece.moved = true;
        updatedBoard.move = {
            srcColumn: selectedPiece.column,
            srcRow: selectedPiece.row,
            destColumn: selectedSquare.column,
            destRow: selectedSquare.row,
        };
        setBoardState(updatedBoard);
        setSelectedPiece({});
        setSelectedSquare({});
        const moveDescription = `${piece.color} moved ${piece.type} from ${String.fromCharCode(65 + selectedPiece.column)}${8 - selectedPiece.row} to ${String.fromCharCode(65 + selectedSquare.column)}${8 - selectedSquare.row}`;
        setMovesHistory(prevHistory => [...prevHistory, {
            desc: moveDescription,
            state: JSON.parse(JSON.stringify(updatedBoard)),
            move: updatedBoard.move,
            whoseMove: "player"
        }]);
        animatePieceMovement(selectedPiece);
        fetchGameState(updatedBoard, computerMove);
    }, [selectedPiece, selectedSquare, findPiece, removePiece, animatePieceMovement, fetchGameState]);

    const computerMove = useCallback((board) => {
        const moveAvailable = !gameOver && !waitApi;
        console.log("Computer move is available: " + moveAvailable)
        if (moveAvailable) {
            setWaitApi(true);
            getComputerMove(board, playerColor || "BLACK")
                .then(boardData => {
                    setBoardState({pieces: boardData.pieces, move: boardData.move});
                    const piece = findPiece(boardData.move.srcRow, boardData.move.srcColumn, board);
                    const moveDescription = `${piece.color} moved ${piece.type} from ${String.fromCharCode(65 + boardData.move.srcColumn)}${8 - boardData.move.srcRow} to ${String.fromCharCode(65 + boardData.move.destColumn)}${8 - boardData.move.destRow}`;
                    setMovesHistory(prevHistory => [...prevHistory, {
                        desc: moveDescription,
                        state: JSON.parse(JSON.stringify(boardData)),
                        move: boardData.move,
                        whoseMove: "computer"
                    }]);
                    fetchGameState(boardData, null);
                    fetchAvailableMoves(boardData, playerColor || "BLACK");
                })
                .catch(error => {
                    console.log('error ' + error);
                    setError(error.toString());
                })
                .finally(() => setWaitApi(false));
        }
    }, [gameOver, waitApi, fetchGameState, fetchAvailableMoves]);

    const renderSquare = useCallback((row, column, piece, checkActive, markSquare) => {
        const isBlack = (row + column) % 2 === 1;
        const isSelected = checkSquareSelected(row, column);
        const isSelectedPiece = checkPieceSelected(row, column);
        const isActive = checkActive ? checkSquareActive(row, column) : false;
        return (
            <Square
                id={`${row}-${column}`}
                key={`${row}-${column}`}
                color={isBlack ? 'black' : 'white'}
                piece={piece}
                onClick={() => {
                    if (!waitApi && isActive) onSquareClick(row, column);
                }}
                selected={isSelected}
                selectedPiece={isSelectedPiece}
                active={isActive}
                mark={markSquare}
            />
        );
    }, [waitApi, checkSquareSelected, checkPieceSelected, checkSquareActive, onSquareClick]);

    const renderBoard = useCallback((boardData, isActive, showMove) => {
        const board = [];

        board.push(<div key="empty" className="square empty"/>);
        for (let col = 0; col < 8; col++) {
            board.push(
                <div key={`column-header-${col}`} className="square column-header">
                    {String.fromCharCode(65 + col)} {/* (A-H) */}
                </div>
            );
        }

        for (let row = 0; row < 8; row++) {
            board.push(
                <div key={`row-header-${row}`} className="square row-header">
                    {8 - row}
                </div>
            );
            for (let column = 0; column < 8; column++) {
                const piece = findPiece(row, column, boardData);
                let markSquare = false;
                if (showMove) {
                    if (
                        (row === showMove.srcRow && column === showMove.srcColumn) ||
                        (row === showMove.destRow && column === showMove.destColumn)
                    ) {
                        markSquare = true;
                    }
                }
                board.push(renderSquare(row, column, piece ? piece : null, isActive, markSquare));
            }
        }

        return board;
    }, [findPiece, renderSquare]);

    const promotePawn = useCallback((piece) => {
        const pawn = findPiece(piece.position.row, piece.position.column, boardState);
        if (pawn) {
            pawn.type = piece.type;
            setPawnPromotionOpen(false);
            computerMove(boardState);
        } else {
            console.log('no pawn found for ');
            console.log(piece);
        }
    }, [findPiece, computerMove, boardState]);

    const handleMoveClick = useCallback((moveIndex) => {
        setSelectedMoveIndex(moveIndex);
        console.log("clicked move index " + moveIndex);
        setMoveOptionsOpen(true);
    }, []);


    const handleRevertToMove = useCallback(() => {
        setMoveOptionsOpen(false);
        setGameOver(false);
        const selectedMove = movesHistory[selectedMoveIndex];
        const boarsStateToRevert = JSON.parse(JSON.stringify(selectedMove.state));
        setBoardState(boarsStateToRevert);
        let length = movesHistory.length;
        console.log(selectedMove.desc)
        movesHistory.splice(selectedMoveIndex + 1, length - selectedMoveIndex);
        if (selectedMove.whoseMove === "player") {
            computerMove(boarsStateToRevert)
        } else {
            fetchAvailableMoves(boarsStateToRevert, playerColor);
        }

    }, [selectedMoveIndex, movesHistory]);

    const setPlayerName = (name) => {
        sessionStorage.setItem('chessPlayerName', name);
    }

    const handleSwitchToNextMove = () => {
        if (selectedMoveIndex < movesHistory.length -1) {
            setSelectedMoveIndex(selectedMoveIndex +1);
        }
    }

    const handleSwitchToPrevMove = () => {
        if (selectedMoveIndex > 0) {
            setSelectedMoveIndex(selectedMoveIndex -1);
        }
    }

    const handlePlayerColorSelect = (color) => {
        setPlayerColor(color);
        storePlayerColor(color);
    }

    if (!playerColor) {
        return (
            <>
                {/*<PlayerNameInput onNameSubmit={setPlayerName}/>*/}
                <PlayerColorSelector onColorSelect={handlePlayerColorSelect}/>
            </>
        );
    }

    return (
        <>
            <div className="chessboard-container">
                <div
                    className="chessboard">{error ? error : boardState ? renderBoard(boardState, true) : 'Loading...'}</div>
                <MoveHistory moves={movesHistory} onMoveClick={handleMoveClick}/>
                <UserStatus/>

                {waitApi && <div className="loadingLabel">Wait for API response</div>}
                {gameOver && <div className="gameOver">Game Over, the winner is {winner}</div>}
            </div>
            <PawnPromotionModal
                isOpen={pawnPromotionOpen}
                onClose={() => {
                    setWaitApi(false);
                    setPawnPromotionOpen(false);
                }}
                piecesList={piecesToPromote}
                onFigureSelect={promotePawn}
            />
            <MoveOptionsModal
                isOpen={moveOptionsOpen}
                labelContent={movesHistory[selectedMoveIndex]?.desc}
                onClose={() => setMoveOptionsOpen(false)}
                onRevertToMove={handleRevertToMove}
                onNext={handleSwitchToNextMove}
                onPreview={handleSwitchToPrevMove}
                board={renderBoard(
                    movesHistory[selectedMoveIndex]?.state,
                    false,
                    movesHistory[selectedMoveIndex]?.move
                )}
            />
        </>
    );
};

export default Chessboard;