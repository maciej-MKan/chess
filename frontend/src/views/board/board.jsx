import React, {useCallback, useEffect, useState} from 'react';
import './Chessboard.css';
import {getAvailableMoves, getComputerMove, getGameState, initGame, sendGameHistory} from '../../api/game';
import {isEmpty} from '../utils/utils';
import PawnPromotionModal from './components/PawnPromotion';
import MoveOptionsModal from "./components/MoveOptionsModal";
import MoveHistory from "./components/MoveHistory";
import PlayerColorSelector from "./components/PlayerColorSelector";
import UserStatus from "./components/UserStatus";
import {sendUserColor} from "../../api/user";
import {useDispatch, useSelector} from "react-redux";
import {setUserGameColor} from "../../redux/userSlice";
import {setAvailableMoves, setGameState} from "../../redux/gameSlice";
import {renderSquare} from "./utils/renderer";
import {findPiece} from "./utils/tools";

const Chessboard = () => {
    const dispatch = useDispatch();
    // const [availableMoves, setAvailableMoves] = useState({});
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
    const loginIn = useSelector((state) => state.auth.isLoginIn);
    const playerColor = useSelector((state) => state.user.userGameColor);
    const boardState = useSelector((state) => state.game.gameState);
    const availableMoves = useSelector((state) => state.game.availableMoves);

    useEffect(() => {
        setWaitApi(true);
        const savedGameState = sessionStorage.getItem('chessGameState');
        if (savedGameState) {
            const {boardState, movesHistory, playerColor} = JSON.parse(savedGameState);
            dispatch(setGameState(boardState));
            setMovesHistory(movesHistory);
            dispatch(setUserGameColor(playerColor));
            move(boardState);
        } else {
            if ((playerColor !== '') && (playerColor !== undefined)) {
                setWaitApi(true);
                console.log("init new game with player color : " + playerColor);
                dispatch(setAvailableMoves({}));
                setSelectedSquare({});
                setSelectedPiece({});
                setMovesHistory([]);
                setSelectedMoveIndex(0);
                initGame(playerColor)
                    .then(boardData => {
                        dispatch(setGameState(boardData));
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
            if (loginIn) {
                sendGameHistory(boardState, movesHistory, playerColor)
                    .catch(error => {
                        console.log('error ' + error);
                    })
            }
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
                        dispatch(setAvailableMoves({}));
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
                .then(availableMovesData => dispatch(
                    setAvailableMoves(!gameOver ? availableMovesData.availableMoves : {}))
                )
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

    // const findPiece = ((row, column, boardData) =>
    //         boardData?.pieces?.find(piece => piece.position.row === row && piece.position.column === column)
    // );

    useEffect(() => {
        if (loginIn && playerColor) {
            console.log("storePlayerColor : ", playerColor);
            sendUserColor(playerColor)
                .catch(error => {
                        console.log('error ' + error);
                        setError(error.toString()
                        )
                    }
                );
        }
    }, [loginIn, playerColor]);

    const removePiece = (piece, board) => {
        if (piece) {
            console.log("board to remove piece: ", board);
            console.log("piece to remove: ", piece);
            const newPieces = board.pieces.filter(p => p !== piece);
            return {...board, pieces: newPieces};
        }
        return board;
    };

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
    }, [selectedSquare, selectedPiece, playerColor]);

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
        let piece = findPiece(selectedPiece.row, selectedPiece.column, boardState);
        let updatedBoard = removePiece(findPiece(selectedSquare.row, selectedSquare.column, boardState), boardState);
        piece = {
            ...piece,
            position: {
                row: selectedSquare.row,
                column: selectedSquare.column
            },
            moved: true
        }
        updatedBoard = removePiece(findPiece(selectedPiece.row, selectedPiece.column, updatedBoard), updatedBoard);
        updatedBoard.pieces.push(piece);
        updatedBoard = {
            ...updatedBoard,
            move: {
                srcColumn: selectedPiece.column,
                srcRow: selectedPiece.row,
                destColumn: selectedSquare.column,
                destRow: selectedSquare.row,
            }
        }
        dispatch(setGameState(updatedBoard));
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
                    dispatch(setGameState({pieces: boardData.pieces, move: boardData.move, gameId: boardData.gameId}));
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

    // const renderSquare = useCallback((row, column, piece, checkActive, markSquare) => {
    //     const isBlack = (row + column) % 2 === 1;
    //     const isSelected = checkSquareSelected(row, column);
    //     const isSelectedPiece = checkPieceSelected(row, column);
    //     const isActive = checkActive ? checkSquareActive(row, column) : false;
    //     return (
    //         <Square
    //             id={`${row}-${column}`}
    //             key={`${row}-${column}`}
    //             color={isBlack ? 'black' : 'white'}
    //             piece={piece}
    //             onClick={() => {
    //                 if (!waitApi && isActive) onSquareClick(row, column);
    //             }}
    //             selected={isSelected}
    //             selectedPiece={isSelectedPiece}
    //             active={isActive}
    //             mark={markSquare}
    //         />
    //     );
    // }, [waitApi, checkSquareSelected, checkPieceSelected, checkSquareActive, onSquareClick]);

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
                const onClick = () => {
                    if (!waitApi && isActive) onSquareClick(row, column);
                };
                const isSelected = checkSquareSelected(row, column);
                const isSelectedPiece = checkPieceSelected(row, column);
                const isSquareActive = isActive ? checkSquareActive(row, column) : false;
                if (showMove) {
                    if (
                        (row === showMove.srcRow && column === showMove.srcColumn) ||
                        (row === showMove.destRow && column === showMove.destColumn)
                    ) {
                        markSquare = true;
                    }
                }
                board.push(renderSquare(row, column, piece ? piece : null, markSquare, onClick, isSelected, isSelectedPiece, isSquareActive));
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
        dispatch(setGameState(boarsStateToRevert));
        let length = movesHistory.length;
        console.log(selectedMove.desc)
        movesHistory.splice(selectedMoveIndex + 1, length - selectedMoveIndex);
        if (selectedMove.whoseMove === "player") {
            computerMove(boarsStateToRevert)
        } else {
            fetchAvailableMoves(boarsStateToRevert, playerColor);
        }

    }, [selectedMoveIndex, movesHistory]);


    const handleSwitchToNextMove = () => {
        if (selectedMoveIndex < movesHistory.length - 1) {
            setSelectedMoveIndex(selectedMoveIndex + 1);
        }
    }

    const handleSwitchToPrevMove = () => {
        if (selectedMoveIndex > 0) {
            setSelectedMoveIndex(selectedMoveIndex - 1);
        }
    }

    if (!playerColor) {
        return (
            <>
                {/*<PlayerNameInput onNameSubmit={setPlayerName}/>*/}
                <PlayerColorSelector/>
            </>
        );
    } else {
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
    }
};

export default Chessboard;