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
import {setAvailableMoves, setGameState, setSelectedPiece, setSelectedSquare} from "../../redux/gameSlice";
import {findPiece, removePiece} from "../../tools/gameTools";
import ChessBoard from "./components/ChessBoard";

const GameScreen = () => {
    const dispatch = useDispatch();
    const [error, setError] = useState('');
    const [gameOver, setGameOver] = useState(false);
    const [waitApi, setWaitApi] = useState(false);
    const [winner, setWinner] = useState(null);
    const [pawnPromotionOpen, setPawnPromotionOpen] = useState(false);
    const [piecesToPromote, setPiecesToPromote] = useState([]);
    const [movesHistory, setMovesHistory] = useState([]);
    const [moveOptionsOpen, setMoveOptionsOpen] = useState(false);
    const [selectedMoveIndex, setSelectedMoveIndex] = useState(0);
    const loginIn = useSelector((state) => state.auth.isLoginIn);
    const playerColor = useSelector((state) => state.user.userGameColor);
    const boardState = useSelector((state) => state.game.gameState);
    const globalWaitApi = useSelector((state) => state.game.waitApi);
    const selectedSquare = useSelector((state) => state.game.selectedSquare);
    const selectedPiece = useSelector((state) => state.game.selectedPiece);

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
                dispatch(setSelectedSquare({}));
                dispatch(setSelectedPiece({}));
                setMovesHistory([]);
                setSelectedMoveIndex(0);
                initGame(playerColor)
                    .then(boardData => {
                        dispatch(setGameState(boardData));
                        setWaitApi(false);
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

    useEffect(() => {
        setWaitApi(globalWaitApi);
    }, [globalWaitApi]);

    const move = (board) => {
        console.log(playerColor);
        if (playerColor === "BLACK") {
            dispatch({type: 'REFRESH_COMPONENT'});
            computerMove({
                pieces: board.pieces,
                gameId: board.gameId
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
                .then(availableMovesData => dispatch(setAvailableMoves(!gameOver ? availableMovesData.availableMoves : {})))
                .catch(error => {
                    console.log('error ' + error);
                    setError(error.toString());
                })
                .finally(() => setWaitApi(false));
        }
    }, [gameOver]);

    useEffect(() => {
        dispatch(setSelectedSquare({}));
    }, [selectedPiece]);

    useEffect(() => {
        if (!isEmpty(selectedSquare) && !isEmpty(selectedPiece)) {
            playerMove();
        } // eslint-disable-next-line
    }, [selectedSquare, selectedPiece]);

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
        dispatch(setSelectedPiece({}));
        dispatch(setSelectedSquare({}));
        const moveDescription = `${piece.color} moved ${piece.type} from ${String.fromCharCode(65 + selectedPiece.column)}${8 - selectedPiece.row} to ${String.fromCharCode(65 + selectedSquare.column)}${8 - selectedSquare.row}`;
        setMovesHistory(prevHistory => [...prevHistory, {
            desc: moveDescription,
            state: JSON.parse(JSON.stringify(updatedBoard)),
            move: updatedBoard.move,
            whoseMove: "player"
        }]);
        fetchGameState(updatedBoard, computerMove);
    }, [selectedPiece, selectedSquare, findPiece, removePiece, fetchGameState]);

    const computerMove = (board) => {
        let moveAvailable = !gameOver && !waitApi;
        console.log("Computer move is available: " + moveAvailable)
        console.log("waitApi: ", waitApi);
        console.log("gameOver: ", gameOver);
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
    };

    const promotePawn = useCallback((piece) => {
        let pawn = findPiece(piece.position.row, piece.position.column, boardState);
        let updatedBoard = removePiece(pawn, boardState);
        if (pawn) {
            pawn = {
                ...pawn,
                type: piece.type,
            }
            updatedBoard.pieces.push(pawn);
            dispatch(setGameState(updatedBoard));
            setPawnPromotionOpen(false);
            computerMove(updatedBoard);
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
                <PlayerColorSelector/>
            </>
        );
    } else if (!boardState) {
        return (
            <div className="game-board">Loading...</div>
        );
    } else {
        return (
            <>
                <div className="chessboard-container">
                    {!error &&
                        <div className="chessboard"><ChessBoard state={boardState} isActive={true} showMove={false}/>
                        </div>}
                    {error && <div className="chessboard-error">{error}</div>}
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
                    board={<ChessBoard
                        state={movesHistory[selectedMoveIndex]?.state}
                        isActive={false}
                        showMove={movesHistory[selectedMoveIndex]?.move}/>}
                />
            </>
        );
    }
};

export default GameScreen;