import React, { useEffect, useState, useCallback } from 'react';
import { Square } from './components/Square';
import './Chessboard.css';
import { getAvailableMoves, getComputerMove, getGameState, initGame } from '../../api/game';
import { isEmpty } from '../utils';
import PawnPromotionModal from './components/PawnPromotion';

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
    const [waitingForPromotion, setWaitingForPromotion] = useState(false);

    useEffect(() => {
        setWaitApi(true);
        initGame()
            .then((boardData) => {
                setBoardState(boardData);
                fetchAvailableMoves(boardData);
            })
            .catch((error) => {
                console.log('error ' + error);
                setError(error.toString());
            })
            .finally(() => setWaitApi(false));
    }, []);

    const fetchGameState = useCallback((board) => {
        setWaitApi(true);
        if (board && !isEmpty(board)) {
            getGameState(board)
                .then((gameStateData) => {
                    if (gameStateData.gameOver.isGameOver) {
                        setGameOver(true);
                        setWinner(gameStateData.gameOver.winner);
                        setAvailableMoves({});
                    } else if (gameStateData.pawnPromotion.pawn) {
                        setPawnPromotionOpen(true);
                        setPiecesToPromote(gameStateData.pawnPromotion.figuresToPromote);
                        setWaitingForPromotion(true);
                    }
                })
                .catch((error) => {
                    console.log('error ' + error);
                    setError(error.toString());
                })
                .finally(() => setWaitApi(false));
        }
    }, []);

    const fetchAvailableMoves = useCallback((board) => {
        if (board && !isEmpty(board) && !gameOver) {
            setWaitApi(true);
            getAvailableMoves(board)
                .then((availableMovesData) => setAvailableMoves(!gameOver ? availableMovesData.availableMoves : {}))
                .catch((error) => {
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

    const findPiece = (row, column) => boardState?.pieces?.find((piece) => piece.position.row === row && piece.position.column === column);

    const removePiece = (piece) => {
        if (piece) {
            const newPieces = boardState.pieces.filter((p) => p !== piece);
            return { ...boardState, pieces: newPieces };
        }
        return boardState;
    };

    const animatePieceMovement = (piece) => {
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
    };

    const onSquareClick = (row, column) => {
        if (!isEmpty(selectedSquare) && row === selectedSquare.row && column === selectedSquare.column) {
            setSelectedSquare({});
        } else if (!isEmpty(selectedPiece) && row === selectedPiece.row && column === selectedPiece.column) {
            setSelectedPiece({});
        } else {
            const piece = findPiece(row, column);
            const id = piece?.id;
            piece && piece.color === playerColor ? setSelectedPiece({ id, row, column }) : setSelectedSquare({ row, column });
        }
    };

    const checkSquareSelected = (row, column) => {
        return selectedSquare.row === row && selectedSquare.column === column;
    };

    const checkPieceSelected = (row, column) => {
        return selectedPiece.row === row && selectedPiece.column === column;
    };

    const checkSquareActive = (row, column) => {
        if (findPiece(row, column)?.color === playerColor) return true;
        if (!isEmpty(selectedPiece)) {
            const id = findPiece(selectedPiece.row, selectedPiece.column)?.id;
            return availableMoves[id]?.some((move) => move.row === row && move.column === column);
        }
        return false;
    };

    const playerMove = () => {
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
        animatePieceMovement(selectedPiece);
        fetchGameState(updatedBoard);
    };

    const computerMove = useCallback(
        (board) => {
            if (!gameOver && !waitingForPromotion && !waitApi) {
                setWaitApi(true);
                getComputerMove(board)
                    .then((boardData) => {
                        setBoardState(boardData);
                        fetchGameState(boardData);
                        fetchAvailableMoves(boardData);
                    })
                    .catch((error) => {
                        console.log('error ' + error);
                        setError(error.toString());
                    })
                    .finally(() => setWaitApi(false));
            }
        },
        [fetchGameState, fetchAvailableMoves, gameOver, waitApi, waitingForPromotion]
    );

    const renderSquare = (row, column, piece) => {
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
    };

    const renderBoard = () => {
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
    };

    const promotePawn = (piece) => {
        const pawn = findPiece(piece.position.row, piece.position.column);
        if (pawn) {
            pawn.type = piece.type;
            setBoardState({ ...boardState });
            setPawnPromotionOpen(false);
            setWaitingForPromotion(false);
            fetchGameState(boardState);
        } else {
            console.log('no pawn found for ');
            console.log(piece);
        }
    };

    return (
        <div>
            <div className="chessboard">{error ? error : boardState ? renderBoard() : 'Loading...'}</div>
            <PawnPromotionModal
                isOpen={pawnPromotionOpen}
                onClose={() => {
                    setWaitApi(false);
                    setPawnPromotionOpen(false);
                }}
                piecesList={piecesToPromote}
                onFigureSelect={promotePawn}
            />
            {waitApi && <div className="loadingLabel">Wait for API response</div>}
            {gameOver && <div className="gameOver">Game Over, the winner is {winner}</div>}
        </div>
    );
};

export default Chessboard;
