import {createSlice} from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: 'user',
    initialState: {
        username: '',
        userColor: '',
    },
    reducers: {
        setUsername: (state, action) => {
            state.username = action.payload;
            console.log("set username : ", state.username);
        },
        setUserColor: (state, action) => {
            state.userColor = action.payload;
            console.log("set userColor : ", state.userColor);
        }
    },
});

export const {setUsername, setUserColor} = userSlice.actions;

export default userSlice.reducer;