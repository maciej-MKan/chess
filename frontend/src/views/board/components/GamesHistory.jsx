import React from "react";
import './GamesHistory.css';
import Modal from "react-modal";
import {useSelector} from "react-redux";
import {toNormalDate} from "../../utils/utils";

const GamesHistory = ({isOpen, onClose}) => {
    const games = useSelector((state) => state.game.historicalGames);
    const gameState = useSelector((state) => state.game.gameState);

    if (!isOpen) {
        return null;
    }

    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel='Games History Modal'
        >
            <div className="games-history">
                <h2>Games History</h2>
                {games.length > 0 ? (
                    <ul>
                        {games.map((game, index) => (
                            <li key={index} onClick={() => console.log(game)} className="historical-game">
                                <div>
                                    {game ? toNormalDate(game.gameStartDate) : "No gameId available"}
                                    {game.actualBoardState.gameId === gameState.gameId ? " <-- current gameplay" : " <-- [status]"}
                                </div>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No games history available.</p>
                )}
            </div>
        </Modal>
    );
};

export default GamesHistory;
