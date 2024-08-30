import {createSlice} from "@reduxjs/toolkit";

const gameSlice = createSlice({
    name: 'game',
    initialState: {
        gameState: '',
        historicalGames: [],
    },
    reducers: {
        setGameState: (state, action) => {
            state.gameState = action.payload;
            console.log("Set new game state: ", action.payload);
        },
        setHistoricalGames: (state, action) => {
            state.historicalGames = action.payload;
        }
    }
});

export const {setGameState, setHistoricalGames} = gameSlice.actions;

export default gameSlice.reducer;