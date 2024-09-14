import React, {useState} from "react";
import './GamesHistory.css';
import Modal from "react-modal";
import {useSelector} from "react-redux";
import {toNormalDate} from "../../utils/utils";
import ChessBoard from "./ChessBoard";
import {useNavigate} from "react-router-dom";


const GamesHistory = ({isOpen, onClose}) => {
    const navigate = useNavigate();
    const games = useSelector((state) => state.game.historicalGames);
    const gameState = useSelector((state) => state.game.gameState);
    const [expandedGameId, setExpandedGameId] = useState(null);
    const toggleGameDetails = (gameId) => {
        setExpandedGameId(expandedGameId === gameId ? null : gameId);
    };
    const handleClose = () => {
        setExpandedGameId(null);
        onClose();
    }

    const handleLoad = (gameData) => {
        const gameState = {
            boardState: gameData.actualBoardState,
            movesHistory: gameData.movesHistory,
            playerColor: gameData.playerColor
        };
        sessionStorage.setItem('chessGameState', JSON.stringify(gameState));
        navigate("/");
        handleClose();
    }

    if (!isOpen) {
        return null;
    }

    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={handleClose}
            contentLabel='Games History Modal'
            className="history-modal"
        >
            <div className="games-history">
                <h2>Games History</h2>
                {games.length > 0 ? (
                    <ul>
                        {games.map((game, index) => (
                            <li key={index}
                                className={`historical-game ${expandedGameId === game.actualBoardState.gameId ? "selected" : ""}`}>
                                <div className="game-title"
                                     onClick={() => toggleGameDetails(game.actualBoardState.gameId)}>
                                    {game ? toNormalDate(game.gameStartDate) : "No gameId available"}
                                    {game.actualBoardState.gameId === gameState.gameId ? " <-- current gameplay" : ""}
                                </div>

                                {expandedGameId === game.actualBoardState.gameId && (
                                    <div className="game-details">
                                        <div className="game-description">
                                            <p><strong>Player Color:</strong> {game.playerColor}</p>
                                            <p><strong>Moves:</strong> {game.movesHistory.length}</p>
                                            <p><strong>Pieces:</strong> {game.actualBoardState.pieces.length}</p>
                                            <button className="load-game-button" onClick={() => handleLoad(game)}>Load
                                                this game
                                            </button>
                                        </div>
                                        <div className="game-view">
                                            <div className="chessboard">
                                                <ChessBoard
                                                    state={game.actualBoardState}
                                                    isActive={false}
                                                    showMove={false}
                                                />
                                            </div>
                                        </div>
                                    </div>
                                )}
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
