import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {fetchUserDetails} from "../api/user";
import {useDispatch, useSelector} from "react-redux";
import {setUsername} from "../redux/userSlice";
import {logIn} from "../redux/authSlice";
import {getGamesHistory} from "../api/game";
import {setHistoricalGames} from "../redux/gameSlice";

export const LoginSuccess = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const loginIn = useSelector((state) => state.auth.isLoginIn);

    const onLoginCorrect = (user) => {
        dispatch(setUsername(user.name));
        sessionStorage.setItem("username", user.name);
        dispatch(logIn());
        navigate("/game");
    }

    useEffect(() => {
        fetchUserDetails()
            .then(data => {
                if (data) data.name ? onLoginCorrect(data) : navigate("/login");
            })
    }, []);

    useEffect(() => {
        getGamesHistory()
            .then(result => {
                dispatch(setHistoricalGames(result));
                if (result) loadGame(result.pop());
            })
            .catch(error => console.log(error))
    }, [loginIn === true])

    const loadGame = (gameData) => {
        const gameState = {
            boardState: gameData.actualBoardState,
            movesHistory: gameData.movesHistory,
            playerColor: gameData.playerColor
        };
        console.log("load prev game");
        sessionStorage.setItem('chessGameState', JSON.stringify(gameState));
        navigate("/game");
    }
}