
export const initGame = async () => {
    const url = `http://localhost:8080/api/game`;
    try {
        const response = await fetch(url);
        return await response.json();
    } catch (error) {
        console.error('Error fetching attractions:', error);
        throw error;
    }
};