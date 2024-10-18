import {createSlice} from "@reduxjs/toolkit";

const gameSlice = createSlice({
    name: 'game',
    initialState: {
        gameState: '',
        historicalGames: [],
        // availableMoves: {},
        waitApi: false,
        selectedSquare: {},
        selectedPiece: {},
    },
    reducers: {
        setHistoricalGames: (state, action) => {
            state.historicalGames = action.payload;
        },
        // setAvailableMoves: (state, action) => {
        //     state.availableMoves = action.payload;
        // },
        setWaitApi: (state, action) => {
            state.waitApi = action.payload;
        },
        setSelectedSquare: (state, action) => {
            state.selectedSquare = action.payload;
        },
        setSelectedPiece: (state, action) => {
            state.selectedPiece = action.payload;
        }
    }
});

export const {
    setHistoricalGames,
    // setAvailableMoves,
    setWaitApi,
    setSelectedPiece,
    setSelectedSquare,
} = gameSlice.actions;

export default gameSlice.reducer;