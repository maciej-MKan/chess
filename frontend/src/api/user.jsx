const backendUri = import.meta.env.VITE_BACKEND_URI
const frontUri = import.meta.env.VITE_FRONTEND_URI

export const fetchUserDetails = async () => {
    try {
        const response = await fetch(`${backendUri}/user`, {
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': `${frontUri}`
            }
        });

        if (!response.ok) {
            throw new Error('Unauthorized');
        }
        return await response.json();
    } catch (error) {
        console.error('Error fetching user details:', error);
        return null;
    }
};