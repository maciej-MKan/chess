import React, {useEffect, useState} from 'react';
import Modal from "react-modal";
import './PreferencesModal.css'
import {useDispatch, useSelector} from "react-redux";
import {setUserDefaultColor, setUserGameColor, setUsername} from "../../redux/userSlice";
import {sendUserColor, sendUserName} from "../../api/user";
import GameDialog from "../utils/components/GameDialog";
import {useNavigate} from "react-router-dom";
import {startNewGame} from "../utils/utils";
import {setState, state$} from "../../rxjsstore/RxStore";

const PreferencesModal = ({isOpen, onClose}) => {
    const dispatch = useDispatch();
    const username = useSelector((state) => state.user.username);
    const color = useSelector((state) => state.user.userDefaultColor);
    const [newUsername, setNewUsername] = useState('');
    const [newColor, setNewColor] = useState('');
    const [dialogOpen, setDialogOpen] = useState(false);
    const labelContent = username + "'s preferences";
    const navigate = useNavigate();

    useEffect(() => {
        const subscription = state$.subscribe();
        return () => subscription.unsubscribe();
    }, []);

    const handleSave = () => {
        if (username !== newUsername) {
            console.log("username changed : ", newUsername);
            dispatch(setUsername(newUsername));
            sendUserName(newUsername)
                .catch(
                    error => {
                        console.log('error : ' + error)
                    }
                )
        }
        if (color !== newColor) {
            console.log("color changed : ", color);
            setDialogOpen(true);
            dispatch(setUserDefaultColor(newColor));
            sendUserColor(color)
                .catch(error => {
                        console.log('error ' + error);
                    }
                );
        }
        onClose();
    }
    const handleClose = () => {
        setNewUsername(username);
        setNewColor(color);
        onClose();
    }
    useEffect(() => {
        setNewUsername(username);
    }, [username]);
    useEffect(() => {
        setNewColor(color);
    }, [color]);

    if (!color || !username) {
        return (
            <Modal
                isOpen={isOpen}
            >
                <h2>wait</h2>
            </Modal>
        )
    } else {
        return (
            <>
                <Modal
                    isOpen={isOpen}
                    onRequestClose={onClose}
                    contentLabel={labelContent}
                    className="react-modal-content"
                    overlayClassName="react-modal-overlay"
                >
                    <h3>{labelContent}</h3>
                    <div className="input-group">
                        <label className="label" htmlFor="username">Username: </label>
                        <input
                            className="name-input"
                            type="text"
                            id="username"
                            value={newUsername}
                            onChange={(e) => setNewUsername(e.target.value)}
                        />
                    </div>
                    <div className="input-group">
                        <label className="label" htmlFor="color">Default Color:</label>
                        <div className="color-options">
                            <label style={{cursor: "pointer"}}>
                                <input
                                    className="color-option"
                                    type="radio"
                                    value="WHITE"
                                    checked={newColor === 'WHITE'}
                                    onChange={(e) => setNewColor(e.target.value)}
                                />
                                White
                            </label>
                            <label style={{cursor: "pointer"}}>
                                <input
                                    className="color-option"
                                    type="radio"
                                    value="BLACK"
                                    checked={newColor === 'BLACK'}
                                    onChange={(e) => setNewColor(e.target.value)}
                                />
                                Black
                            </label>
                        </div>
                    </div>
                    <div className="button-group">
                        <button className="button-save" onClick={handleSave}>save</button>
                        <button className="button-close" onClick={handleClose}>close</button>
                    </div>
                </Modal>
                <GameDialog
                    isOpen={dialogOpen}
                    onContinue={() => setDialogOpen(false)}
                    onNewGame={() => {
                        startNewGame(navigate, playerColor)
                            .then(boardState => {
                                // dispatch(setGameState(boardState))
                                setState({boardState: boardState});
                            })
                            .catch((err) => {
                                console.error(err)
                            });
                        setDialogOpen(false);
                        dispatch(setUserGameColor(color));
                    }}
                />
            </>
        )
    }
}

export default PreferencesModal;