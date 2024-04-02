export const initGame = async () => {
    const url = `http://localhost:8080/api/game`;
    try {
        const response = await fetch(url);
        return await response.json();
    } catch (error) {
        console.error('Error fetching init game: ', error);
        throw error;
    }
};

export const getComputerMove = async (bordState) => {
    const url = `http://localhost:8080/api/game`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
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
    const url = `http://localhost:8080/api/game/available_moves`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(bordState),
        });
        if (response.ok) {
            return await response.json();
        } else {
            return {};
        }
    } catch (error) {
        console.log('Error fetching computer move: ', error);
        throw error;
    }
};