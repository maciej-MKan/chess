import {proxyFetch} from "../views/utils/utils";

const backendUri = import.meta.env.VITE_BACKEND_URI
const frontUri = import.meta.env.VITE_FRONTEND_URI

export const fetchUserDetails = async () => {
    try {
        const response = await proxyFetch(`${backendUri}/user`, {
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
        const response = await proxyFetch(`${backendUri}/user/color`, {
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

export const sendUserColor = async (color) => {
    try {
        await proxyFetch(`${backendUri}/user/color`, {
            credentials: "include",
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': `${frontUri}`
            },
            body: JSON.stringify({color: color})
        });
    } catch (error) {
        console.error('Store user color fail: ', error);
    }
};

export const sendUserName = async (name) => {
    try {
        await proxyFetch(`${backendUri}/user/name`, {
            credentials: "include",
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': `${frontUri}`
            },
            body: name
        });
    } catch (error) {
        console.error('Store user color fail: ', error);
    }
};

export const sendLogout = async () => {
    try {
        await proxyFetch(`${backendUri}/security/logout`, {
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