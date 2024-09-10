import {createSlice} from "@reduxjs/toolkit";

const gameSlice = createSlice({
    name: 'game',
    initialState: {
        gameState: '',
        historicalGames: [],
        availableMoves: {},
    },
    reducers: {
        setGameState: (state, action) => {
            state.gameState = action.payload;
            console.log("Set new game state: ", action.payload);
        },
        setHistoricalGames: (state, action) => {
            state.historicalGames = action.payload;
        },
        setAvailableMoves: (state, action) => {
            state.availableMoves = action.payload;
            console.log('availableMoves setup: ', state.availableMoves);
        }
    }
});

export const {setGameState, setHistoricalGames, setAvailableMoves} = gameSlice.actions;

export default gameSlice.reducer;