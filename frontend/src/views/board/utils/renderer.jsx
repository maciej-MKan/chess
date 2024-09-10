import React from "react";
import {Square} from "../components/Square";
import {findPiece} from "./tools";


export function renderSquare(row, column, piece, markSquare, onClick, isSelected, isSelectedPiece, isActive) {
    const isBlack = (row + column) % 2 === 1;
    console.log('render square : ', row, '  ', column, ' is active: ', isActive);
    return (
        <Square
            id={`${row}-${column}`}
            key={`${row}-${column}`}
            color={isBlack ? 'black' : 'white'}
            piece={piece}
            onClick={onClick}
            selected={isSelected}
            selectedPiece={isSelectedPiece}
            active={isActive}
            mark={markSquare}
        />
    );
}

export function renderBoard(boardData, isActive, showMove) {
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
                if (isActive) onSquareClick(row, column); //if (!waitApi && isActive) onSquareClick(row, column);
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
}