const backendUri = import.meta.env.VITE_BACKEND_URI
const frontUri = import.meta.env.VITE_FRONTEND_URI

export const initGame = async () => {
    const url = `${backendUri}/api/game`;
    try {
        const response = await fetch(url,{
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            }
        });
        return await response.json();
    } catch (error) {
        console.error('Error fetching init game: ', error);
        throw error;
    }
};

export const getComputerMove = async (bordState) => {
    const url = `${backendUri}/api/game`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            },
            body: JSON.stringify(bordState),
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

export const getAvailableMoves = async (bordState) => {
    const url = `${backendUri}/api/game/available_moves`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin' : `${frontUri}`
            },
            body: JSON.stringify(bordState),
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
    const url = `${backendUri}/api/game/game_over`;
    try {
        const response = await fetch(url, {
            method: 'POST',
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
                isGameOver: false,
                winner: null
            }
        }
    } catch (error) {
        console.log('Error fetching game state: ', error) ;
        throw error;
    }
};