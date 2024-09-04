export function isEmpty(object){
    return Object.keys(object).length === 0;
}

export function startNewGame(navigate) {
    sessionStorage.removeItem('chessGameState');
    if (navigate != null) navigate("/");
}