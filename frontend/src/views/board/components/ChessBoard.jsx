import React, {useCallback} from "react";
import {
    checkAvailableMove,
    checkPieceSelected,
    checkSquareActive,
    checkSquareSelected,
    findPiece,
} from "../../../tools/gameTools";
import {Square} from "./Square";
import {useDispatch, useSelector} from "react-redux";
import {isEmpty} from "../../utils/utils";
import {setSelectedPiece, setSelectedSquare} from "../../../redux/gameSlice";

const ChessBoard = ({state, isActive, showMove}) => {
    const playerColor = useSelector((state) => state.user.userGameColor);
    const boardState = useSelector((state) => state.game.gameState);
    const waitApi = useSelector((state) => state.game.waitApi);
    const selectedSquare = useSelector((state) => state.game.selectedSquare);
    const selectedPiece = useSelector((state) => state.game.selectedPiece);
    const availableMoves = useSelector((state) => state.game.availableMoves);
    const dispatch = useDispatch();

    const onSquareClick = useCallback((row, column) => {
        console.log('on square click')
        const piece = findPiece(row, column, boardState);
        if (!isEmpty(selectedSquare) && row === selectedSquare.row && column === selectedSquare.column) {
            dispatch(setSelectedSquare({}));
            console.log('unselect square');
        } else if (!isEmpty(selectedPiece) && row === selectedPiece.row && column === selectedPiece.column) {
            dispatch(setSelectedPiece({}));
            console.log('unselect piece');
        } else if (
            checkAvailableMove(selectedPiece.id, row, column, availableMoves) &&
            piece?.type === "ROOK" &&
            findPiece(selectedPiece.row, selectedPiece.column, boardState)?.type === "KING"
        ) {
            console.log("castling");
            dispatch(setSelectedSquare({row, column}));
        } else {
            console.log('square select')
            const id = piece?.id;
            piece && (piece.color === playerColor) ?
                dispatch(setSelectedPiece({id, row, column})) :
                dispatch(setSelectedSquare({row, column}));
        }
    }, [selectedSquare, selectedPiece, playerColor, findPiece]);

    const renderSquare = useCallback((row, column, piece, checkActive, markSquare) => {
        const isBlack = (row + column) % 2 === 1;
        const isSelected = checkSquareSelected(row, column, selectedSquare);
        const isSelectedPiece = checkPieceSelected(row, column, selectedPiece);
        const isActive = checkActive ?
            checkSquareActive(row, column, boardState, playerColor, selectedPiece, availableMoves) : false;
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

    return (
        renderBoard(state, isActive, showMove)
    )
};

export default ChessBoard;