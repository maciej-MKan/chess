import {configureStore} from '@reduxjs/toolkit';
import authReducer from './authSlice';
import userReducer from './userSlice';
import gameReducer from './gameSlice';

const store = configureStore({
    reducer: {
        auth: authReducer,
        user: userReducer,
        game: gameReducer
    },
});

export default store;