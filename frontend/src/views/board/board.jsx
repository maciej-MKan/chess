import React, {useCallback, useEffect, useState} from 'react';
import {Square} from './components/Square';
import './Chessboard.css';
import {getAvailableMoves, getComputerMove, getGameState, initGame} from '../../api/game';
import {isEmpty} from '../utils';
import PawnPromotionModal from './components/PawnPromotion';
import MoveOptionsModal from "./components/MoveOptionsModal";
import MoveHistory from "./components/MoveHistory";

const Chessboard = () => {
    const playerColor = 'BLACK';
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
    const [selectedMoveIndex, setSelectedMoveIndex] = useState(null);

    useEffect(() => {
        setWaitApi(true);
        initGame()
            .then(boardData => {
                setBoardState(boardData);
                fetchAvailableMoves(boardData);
            })
            .catch(error => {
                console.log('error ' + error);
                setError(error.toString());
            })
            .finally(() => setWaitApi(false));
    }, []);

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

    const fetchAvailableMoves = useCallback((board) => {
        if (board && !isEmpty(board) && !gameOver) {
            setWaitApi(true);
            getAvailableMoves(board)
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

    const findPiece = useCallback((row, column) =>
            boardState?.pieces?.find(piece => piece.position.row === row && piece.position.column === column),
        [boardState]
    );

    const removePiece = useCallback((piece) => {
        if (piece) {
            const newPieces = boardState.pieces.filter(p => p !== piece);
            return { ...boardState, pieces: newPieces };
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
        const piece = findPiece(row, column);
        if (!isEmpty(selectedSquare) && row === selectedSquare.row && column === selectedSquare.column) {
            setSelectedSquare({});
        } else if (!isEmpty(selectedPiece) && row === selectedPiece.row && column === selectedPiece.column) {
            setSelectedPiece({});
        } else if (
            checkAvailableMove(selectedPiece.id, row, column) &&
            piece?.type === "ROOK" &&
            findPiece(selectedPiece.row, selectedPiece.column)?.type === "KING"
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
        if (findPiece(row, column)?.color === playerColor) return true;
        if (!isEmpty(selectedPiece)) {
            const id = findPiece(selectedPiece.row, selectedPiece.column)?.id;
            return checkAvailableMove(id, row, column);
        }
        return false;
    };

    const playerMove = useCallback(() => {
        const piece = findPiece(selectedPiece.row, selectedPiece.column);
        const updatedBoard = removePiece(findPiece(selectedSquare.row, selectedSquare.column));
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
        setMovesHistory(prevHistory => [...prevHistory, moveDescription]);
        animatePieceMovement(selectedPiece);
        fetchGameState(updatedBoard, computerMove);
    }, [selectedPiece, selectedSquare, findPiece, removePiece, animatePieceMovement, fetchGameState]);

    const computerMove = useCallback((board) => {
        if (!gameOver && !waitApi) {
            setWaitApi(true);
            getComputerMove(board)
                .then(boardData => {
                    setBoardState(boardData);
                    const piece = findPiece(boardData.move.srcRow, boardData.move.srcColumn);
                    const moveDescription = `${piece.color} moved ${piece.type} from ${String.fromCharCode(65 + boardData.move.srcColumn)}${8 - boardData.move.srcRow} to ${String.fromCharCode(65 + boardData.move.destColumn)}${8 - boardData.move.destRow}`;
                    setMovesHistory(prevHistory => [...prevHistory, moveDescription]);
                    fetchGameState(boardData, null);
                    fetchAvailableMoves(boardData);
                })
                .catch(error => {
                    console.log('error ' + error);
                    setError(error.toString());
                })
                .finally(() => setWaitApi(false));
        }
    }, [gameOver, waitApi, fetchGameState, fetchAvailableMoves]);

    const renderSquare = useCallback((row, column, piece) => {
        const isBlack = (row + column) % 2 === 1;
        const isSelected = checkSquareSelected(row, column);
        const isSelectedPiece = checkPieceSelected(row, column);
        const isActive = checkSquareActive(row, column);
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
            />
        );
    }, [waitApi, checkSquareSelected, checkPieceSelected, checkSquareActive, onSquareClick]);

    const renderBoard = useCallback(() => {
        const board = [];

        board.push(<div key="empty" className="square empty" />);
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
                const piece = findPiece(row, column);
                board.push(renderSquare(row, column, piece ? piece : null));
            }
        }

        return board;
    }, [findPiece, renderSquare]);

    const promotePawn = useCallback((piece) => {
        const pawn = findPiece(piece.position.row, piece.position.column);
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
        setMoveOptionsOpen(true);
    }, []);

    const handleViewState = useCallback(() => {
        setMoveOptionsOpen(false);
        const selectedMove = movesHistory[selectedMoveIndex];
        // Display the board state for the selected move
        // Implement the logic to view the state for the selected move
    }, [selectedMoveIndex, movesHistory]);

    const handleRevertToMove = useCallback(() => {
        setMoveOptionsOpen(false);
        const selectedMove = movesHistory[selectedMoveIndex];
        // Revert the board to the selected move
        // Implement the logic to revert to the selected move
    }, [selectedMoveIndex, movesHistory]);

    return (
        <>
            <div className="chessboard-container">
                <div className="chessboard">{error ? error : boardState ? renderBoard() : 'Loading...'}</div>
                <MoveHistory moves={movesHistory} onMoveClick={handleMoveClick} />
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
                onClose={() => setMoveOptionsOpen(false)}
                onViewState={handleViewState}
                onRevertToMove={handleRevertToMove}
            />
            {waitApi && <div className="loadingLabel">Wait for API response</div>}
            {gameOver && <div className="gameOver">Game Over, the winner is {winner}</div>}
        </>
    );
};

export default Chessboard;