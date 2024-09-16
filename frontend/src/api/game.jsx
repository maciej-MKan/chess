import {proxyFetch} from "../views/utils/utils";

const backendUri = import.meta.env.VITE_BACKEND_URI
const frontUri = import.meta.env.VITE_FRONTEND_URI

export const initGame = async (playerColor) => {
    const url = `${backendUri}/api/game?playerColor=${playerColor}`;
    try {
        const response = await proxyFetch(url, {
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            }
        });
        if (response.ok) {
            return await response.json();
        } else {
            return Promise.reject('bad response');
        }
    } catch (error) {
        console.error('Error fetching init game: ', error);
        throw error;
    }
};

export const getComputerMove = async (bordState, color) => {
    const url = `${backendUri}/api/game`;
    try {
        const response = await proxyFetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            },
            body: JSON.stringify({...bordState, playerColor: color}),
        });
        if (response.ok) {
            return await response.json();
        } else {
            return bordState;
        }
    } catch (error) {
        console.log('Error fetching computer move: ', error);
        throw error;
    }
};

export const getAvailableMoves = async (bordState, color) => {
    const url = `${backendUri}/api/game/available_moves`;
    try {
        const response = await proxyFetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            },
            body: JSON.stringify({...bordState, playerColor: color}),
        });
        if (response.ok) {
            return await response.json();
        } else {
            return {};
        }
    } catch (error) {
        console.log('Error fetching available moves: ', error);
        throw error;
    }
};

export const getGameState = async (boardState) => {
    const url = `${backendUri}/api/game/state`;
    try {
        const response = await proxyFetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            },
            body: JSON.stringify(boardState),
        });
        if (response.ok) {
            return await response.json();
        } else {
            return {
                gameOver: {
                    isGameOver: false,
                    winner: null
                },
                pawnPromotion: {
                    pawn: null,
                    figuresToPromote: [

                    ]
                }
            }
        }
    } catch (error) {
        console.log('Error fetching game state: ', error) ;
        throw error;
    }
};

export const sendGameHistory = async (boardState, movesHistory, playerColor) => {
    const url = `${backendUri}/api/game/history`;
    try {
        await proxyFetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': `${frontUri}`
            },
            body: JSON.stringify({
                actualBoardState: boardState,
                movesHistory: movesHistory,
                playerColor: playerColor
            }),
        });
    } catch (error) {
        console.log('Error saving game state: ', error);
        throw error;
    }
}

export const getGamesHistory = async () => {
    const url = `${backendUri}/api/game/history`;
    try {
        const response = await proxyFetch(url, {
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': `${frontUri}`
            }
        });
        if (response.ok) {
            return await response.json();
        } else {
            return Promise.reject('bad response');
        }
    } catch (error) {
        console.error('Error fetching init game: ', error);
        throw error;
    }
}