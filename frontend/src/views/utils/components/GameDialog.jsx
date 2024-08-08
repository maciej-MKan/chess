import React from 'react';
import './GameDialog.css';

const GameDialog = ({isOpen, onContinue, onNewGame, onClose}) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>What would you like to do?</h2>
                <p>Do you want to continue the current game or start a new one?</p>
                <div className="button-group">
                    <button className="continue-button" onClick={onContinue}>Continue Game</button>
                    <button className="newgame-button" onClick={onNewGame}>Start New Game</button>
                </div>
                <button className="close-button" onClick={onClose}>Cancel</button>
            </div>
        </div>
    );
};

export default GameDialog;
