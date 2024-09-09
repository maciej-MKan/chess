export function isEmpty(object){
    return Object.keys(object).length === 0;
}

export function startNewGame(navigate) {
    sessionStorage.removeItem('chessGameState');
    if (navigate != null) navigate("/");
}

export function toNormalDate(isoDate) {
    const date = new Date(isoDate);
    return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    }) + ' ' + date.toLocaleTimeString('en-US', {
        hour: '2-digit',
        minute: '2-digit',
    });
}