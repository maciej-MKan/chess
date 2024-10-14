import {notify} from "./components/Notify";
import {initGame} from "../../api/game";

export function isEmpty(object) {
    return Object.keys(object).length === 0;
}

export const startNewGame = (navigate, playerColor) => {
    return new Promise((resolve, reject) => {
        sessionStorage.removeItem('chessGameState');
        const movesHistory = [];
        if (playerColor) {
            console.log("init game")
            initGame(playerColor)
                .then((boardState) => {
                        console.log("boardState", boardState);
                        const gameState = {boardState, movesHistory, playerColor};
                        sessionStorage.setItem('chessGameState', JSON.stringify(gameState));
                        resolve(boardState);
                        if (navigate != null) {
                            window.location.reload();
                            navigate("/");
                        }
                    }
                )
                .catch(error => {
                    reject(error);
                })
        } else {
            reject("player color undefined");
        }
    })
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