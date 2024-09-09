export function findPiece(row, column, boardData) {
    return boardData?.pieces?.find(piece => piece.position.row === row && piece.position.column === column)
}