import React from 'react';
import './DropDownMenu.css';
import {startNewGame} from "../utils/utils";
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {setAvailableMoves, setGameState} from "../../redux/gameSlice";

const DropdownMenu = ({name, onLogout, onViewHistory, onViewPreferences}) => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const playerColor = useSelector((state) => state.user.userDefaultColor);
    const handleNewGame = () => {
        dispatch(setGameState(null));
        dispatch(setAvailableMoves(null));
        startNewGame(navigate, playerColor)
            .then(boardState => {
                dispatch(setGameState(boardState))
            })
            .catch((err) => {
                console.error(err)
            });
    }
    return (
        <div className="dropdown">
            <button className="dropbtn">Hello, {name}</button>
            <div className="dropdown-content">
                <a href="#" onClick={onViewHistory}>Games history</a>
                <a href="#" onClick={onViewPreferences}>User preferences</a>
                <a href="#" onClick={onLogout}>Logout</a>
                <a href="#" onClick={() => handleNewGame()}>Start new game</a>
            </div>
        </div>
    );
};

export default DropdownMenu;
