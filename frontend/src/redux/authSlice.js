import {createSlice} from '@reduxjs/toolkit';

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        isLoginIn: false,
    },
    reducers: {
        logIn: (state) => {
            console.log("Logging in");
            state.isLoginIn = true;
        },
        logOut: (state) => {
            console.log("Logging out");
            state.isLoginIn = false;
        },
    },
});

export const {logIn, logOut} = authSlice.actions;

export default authSlice.reducer;