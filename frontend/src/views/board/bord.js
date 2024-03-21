import React, {useEffect, useState} from 'react';
import {Square} from "./components/Square";
import './Chessboard.css'
import {getComputerMove, initGame} from "../../api/game";
import {isEmpty} from "../utils";

const Chessboard = () => {

    const playerColor = 'BLACK';
    const [bordState, setBordState] = useState();
    const [error, setError] = useState(" ");
    const [waitApi, setWaitApi] = useState(false);
    const [selectedSquare, setSelectedSquare] = useState({});
    const [selectedPiece, setSelectedPiece] = useState({});

    useEffect(() => {
        setWaitApi(true)
        initGame()
            .then((boardData) => {
                setBordState(boardData);
            })
            .catch((error) => {
                console.log("error " + error);
                setError(error.toString())
            })
            .finally(() => setWaitApi(false));
    }, []);

    useEffect(() => {
        setSelectedSquare({});
    }, [selectedPiece]);

    useEffect(() => {
        if (!isEmpty(selectedSquare) && !isEmpty(selectedPiece)){
            playerMove();
        }
    }, [selectedSquare, selectedPiece]);


    const findPiece = (row, column) => bordState.pieces.find(piece => piece.position.row === row && piece.position.column === column);
    const onSquareClick = (row, column) => {
        if (!isEmpty(selectedSquare) && row === selectedSquare.row && column === selectedSquare.column){
            setSelectedSquare({})
        } else if (!isEmpty(selectedPiece) && row === selectedPiece.row && column === selectedPiece.column){
            setSelectedPiece({})
        } else {
            const piece = findPiece(row, column);
            (piece && piece.color === playerColor) ? setSelectedPiece({row, column}) : setSelectedSquare({row, column});
        }
    }
    const checkSquareSelected = (row, column) => {
        return selectedSquare.row === row && selectedSquare.column === column;
    }

    const checkPieceSelected = (row, column) => {
        return selectedPiece.row === row && selectedPiece.column === column;
    }
    const playerMove = () => {
        console.log("move " + selectedPiece.row + " " + selectedPiece.column + " to " + selectedSquare.row + " " + selectedSquare.column);
        const piece = findPiece(selectedPiece.row, selectedPiece.column);
        piece.position.row = selectedSquare.row;
        piece.position.column = selectedSquare.column;
        console.log(bordState);
        setSelectedPiece({});
        setSelectedSquare({});
        computerMove();
    }
    const computerMove = () => {
        setWaitApi(true);
        getComputerMove(bordState)
            .then(response => {
                setBordState(response);
            })
            .catch((error) => {
                console.log("error " + error);
                setError(error.toString())
            })
            .finally( () => setWaitApi(false));
    };
    const renderSquare = (row, column, piece) => {
        const isBlack = (row + column) % 2 === 1;
        const isSelected = checkSquareSelected(row, column);
        const isSelectedPiece = checkPieceSelected(row, column);
        return <Square
            key={`${row}-${column}`}
            color={isBlack ? 'black' : 'white'}
            piece={piece}
            onClick={() => {
                if (!waitApi) onSquareClick(row, column);
            }}
            selected={isSelected}
            selectedPiece={isSelectedPiece}
        />;
    };

    const renderBoard = () => {
        const board = [];
        for (let row = 0; row < 8; row++) {
            for (let column = 0; column < 8; column++) {
                const piece = findPiece(row, column);
                board.push(renderSquare(row, column, piece? piece: null));
            }
        }
        return board;
    };

    return (
        <div>
            <div className="chessboard">{(error !== " ") ? error : bordState? renderBoard() : error}</div>
            {waitApi && <div className='loadingLabel'>Wait for API response</div>}
        </div>
    );
};

export default Chessboard;
