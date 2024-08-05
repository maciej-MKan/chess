import {createSlice} from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: 'user',
    initialState: {
        username: '',
    },
    reducers: {
        setUsername: (state, action) => {
            state.username = action.payload;
            console.log("set username : ", state.username);
        }
    },
});

export const {setUsername} = userSlice.actions;

export default userSlice.reducer;