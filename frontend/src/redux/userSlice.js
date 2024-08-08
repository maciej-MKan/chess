import {createSlice} from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: 'user',
    initialState: {
        username: '',
        userDefaultColor: '',
        userGameColor: '',
    },
    reducers: {
        setUsername: (state, action) => {
            state.username = action.payload;
            console.log("set username : ", state.username);
        },
        setUserDefaultColor: (state, action) => {
            state.userDefaultColor = action.payload;
            console.log("set userDefaultColor : ", state.userDefaultColor);
        },
        setUserGameColor: (state, action) => {
            state.userGameColor = action.payload;
            console.log("set userGameColor : ", state.userGameColor);
        }
    },
});

export const {setUsername, setUserDefaultColor, setUserGameColor} = userSlice.actions;

export default userSlice.reducer;