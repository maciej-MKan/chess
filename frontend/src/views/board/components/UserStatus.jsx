import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './UserStatus.css';
import {sendLogout} from "../../../api/user";
import DropDownMenu from "../../userMenu/DropDownMenu";
import PreferencesModal from "../../userMenu/PreferencesModal";
import {useDispatch, useSelector} from "react-redux";
import {logOut} from "../../../redux/authSlice";
import {setUserGameColor, setUsername} from "../../../redux/userSlice";
import {getGamesHistory} from "../../../api/game";
import GamesHistory from "./GamesHistory";
import {setHistoricalGames} from "../../../redux/gameSlice";
import {startNewGame} from "../../utils/utils";
import {setState, state$} from "../../../rxjsstore/RxStore";

const UserStatus = () => {
    const navigate = useNavigate();
    const [preferencesVisible, setPreferencesVisible] = useState(false);
    const [historyModalVisible, setHistoryModalVisible] = useState(false);
    const dispatch = useDispatch();
    const isLoginIn = useSelector((state) => state.auth.isLoginIn);
    const username = useSelector((state) => state.user.username);
    const playerColor = useSelector((state) => state.user.userDefaultColor);

    useEffect(() => {
        const subscription = state$.subscribe();
        return () => subscription.unsubscribe();
    }, []);

    useEffect(() => {
        console.log("isLoginIn : ", isLoginIn);
    }, [isLoginIn]);

    const handleLogin = () => {
        navigate('/');
    };

    const handleLogout = () => {
        dispatch(setUsername(""));
        dispatch(logOut());
        dispatch(setUserGameColor(''));
        sessionStorage.removeItem("username");
        document.cookie = "JSESSIONID=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/;";
        sendLogout().then(() => {
        });
        startNewGame(navigate, playerColor)
            .then(boardState => {
                // dispatch(setGameState(boardState))
                setState({boardState: boardState})
            })
            .catch((err) => {
                console.error(err)
            });
    }
    const handleGamesHistory = () => {
        getGamesHistory()
            .then(result => {
                dispatch(setHistoricalGames(result));
                setHistoryModalVisible(true);
            })
            .catch(error => console.log(error))
    }
    const handleUserPreferences = () => {
        setPreferencesVisible(true);
    }

    const handleNewGame = () => {
        startNewGame(null, playerColor)
            .then(boardState => {
                // dispatch(setGameState(boardState))
                setState({boardState: boardState})
            })
            .catch((err) => {
                console.error(err)
            });
        window.location.reload();
    }

    return (
        <>
            <div className="user-status-container">
                {username ? (
                    <div className="username">
                        <DropDownMenu
                            name={username}
                            onLogout={handleLogout}
                            onViewHistory={handleGamesHistory}
                            onViewPreferences={handleUserPreferences}
                        />
                    </div>
                ) : (
                    <div className="button-container">
                        <button className="login-button" onClick={handleLogin}>Login</button>
                        <button className="new-game-button" onClick={handleNewGame}>Restart Game</button>
                    </div>
                )}
            </div>
            <PreferencesModal
                isOpen={preferencesVisible}
                onClose={() => setPreferencesVisible(false)}
            />
            <GamesHistory
                isOpen={historyModalVisible}
                onClose={() => setHistoryModalVisible(false)}
            />
        </>
    );
};

export default UserStatus;