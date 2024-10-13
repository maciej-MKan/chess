import {notify} from "./components/Notify";
import {initGame} from "../../api/game";

export function isEmpty(object) {
    return Object.keys(object).length === 0;
}

export const startNewGame = (navigate, playerColor) => {
    sessionStorage.removeItem('chessGameState');
    const movesHistory = [];
    if (playerColor) {
        console.log("init game")
        initGame(playerColor)
            .then((boardState) => {
                    console.log("boardState", boardState);
                    if (navigate != null) navigate("/");
                    sessionStorage.setItem('chessGameState', JSON.stringify({boardState, movesHistory, playerColor}));
                }
            )
            .catch(error => {
            })
    } else {
        console.error("player color undefined");
    }
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

export const proxyFetch = async (url, options = {}) => {
    const response = await fetch(url, options);
    if (response.status === 401) {
        notify('Session expired. Please login again');
        setTimeout(() => {
            window.location.href = '/';
        }, 3000);
    } else {
        return response;
    }
}