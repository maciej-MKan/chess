import {createSlice} from "@reduxjs/toolkit";

const gameSlice = createSlice({
    name: 'game',
    initialState: {
        gameState: '',
    },
    reducers: {
        setGameState: (state, action) => {
            state.gameState = action.payload;
            console.log("Set new game state");
        }
    }
});

export const {setGameState} = gameSlice.actions;

export default gameSlice.reducer;