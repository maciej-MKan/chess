import React from "react";
import './GamesHistory.css'
import Modal from "react-modal";

const GamesHistory = (games, isOpen, onClose) => {
    if (games) {
        return (
            <Modal
                isOpen={isOpen}
                onRequestClose={onClose}
                contentLabel='Games History Modal'
            >
                <div className="games-history">
                    <h2>Games History</h2>
                    <ul>
                        {games?.map((game, index) => (
                            <li key={index} onClick={() => console.log(game)} className="historical-game">
                                <div>
                                    {game.gameId}
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </Modal>
        );
    }
}

export default GamesHistory