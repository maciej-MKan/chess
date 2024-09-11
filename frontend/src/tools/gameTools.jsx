import {isEmpty} from "../views/utils/utils";

export function findPiece(row, column, boardData) {
    return boardData?.pieces?.find(piece => piece.position.row === row && piece.position.column === column)
}

export function removePiece(piece, board) {
    if (piece) {
        console.log("board to remove piece: ", board);
        console.log("piece to remove: ", piece);
        const newPieces = board.pieces.filter(p => p !== piece);
        return {...board, pieces: newPieces};
    }
    return board;
}

export function checkSquareSelected(row, column, selectedSquare) {
    return selectedSquare.row === row && selectedSquare.column === column;
}

export function checkPieceSelected(row, column, selectedPiece) {
    return selectedPiece.row === row && selectedPiece.column === column;
}

export function checkAvailableMove(id, row, column, availableMoves) {
    return availableMoves[id]?.some(move => move.row === row && move.column === column);
}

export function checkSquareActive(row, column, boardState, playerColor, selectedPiece, availableMoves) {
    if (findPiece(row, column, boardState)?.color === playerColor) return true;
    if (!isEmpty(selectedPiece)) {
        const id = findPiece(selectedPiece.row, selectedPiece.column, boardState)?.id;
        return checkAvailableMove(id, row, column, availableMoves);
    }
    return false;
}