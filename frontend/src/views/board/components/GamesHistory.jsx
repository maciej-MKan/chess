import React, {useState} from "react";
import './GamesHistory.css';
import Modal from "react-modal";
import {useSelector} from "react-redux";
import {toNormalDate} from "../../utils/utils";
import ChessBoard from "./ChessBoard";
import '../Chessboard.css';


const GamesHistory = ({isOpen, onClose}) => {
    const games = useSelector((state) => state.game.historicalGames);
    const gameState = useSelector((state) => state.game.gameState);
    const [expandedGameId, setExpandedGameId] = useState(null);
    const toggleGameDetails = (gameId) => {
        setExpandedGameId(expandedGameId === gameId ? null : gameId);
    };

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
                            <li key={index} className="historical-game">
                                <div onClick={() => toggleGameDetails(game.actualBoardState.gameId)}
                                     style={{cursor: 'pointer'}}>
                                    {game ? toNormalDate(game.gameStartDate) : "No gameId available"}
                                    {game.actualBoardState.gameId === gameState.gameId ? " <-- current gameplay" : " <-- [status]"}
                                </div>

                                {expandedGameId === game.actualBoardState.gameId && (
                                    <div className="game-details">
                                        <p><strong>Game ID:</strong> {game.actualBoardState.gameId}</p>
                                        <p><strong>Player Color:</strong> {game.playerColor}</p>
                                        <p><strong>Moves:</strong> {game.movesHistory.length}</p>
                                        <div className="chessboard">
                                            <ChessBoard
                                                boardState={game.actualBoardState}
                                                isActive={false}
                                                showMove={false}
                                            />
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
