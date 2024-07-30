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

export const fetchUserColor = async () => {
    try {
        const response = await fetch(`${backendUri}/user/color`, {
            credentials: "include",
            method: 'GET',
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

export const sendLogout = async () => {
    try {
        await fetch(`${backendUri}/security/logout`, {
            credentials: "include",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': `${frontUri}`
            }
        });
        return null;
    } catch (error) {
        console.error('Logout fail: ', error);
        return null;
    }
}