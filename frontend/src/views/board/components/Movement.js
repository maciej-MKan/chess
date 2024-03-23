// import {useEffect, useState} from "react";
// import {isEmpty} from "../../utils";
// import {getComputerMove} from "../../../api/game";
//
//
// const Movement = (bordState) => {
//     const [selectedSquare, setSelectedSquare] = useState({})
//     const [selectedPiece, setSelectedPiece] = useState({})
//
//     useEffect(() => {
//         setSelectedSquare({});
//     }, [selectedPiece]);
//
//     useEffect(() => {
//         if (!isEmpty(selectedSquare) && !isEmpty(selectedPiece)){
//             playerMove();
//         }
//     }, [selectedSquare, selectedPiece]);
//
//     const checkSquareSelected = (row, column) => {
//         return selectedSquare.row === row && selectedSquare.column === column;
//     }
//
//     const checkPieceSelected = (row, column) => {
//         return selectedPiece.row === row && selectedPiece.column === column;
//     }
//     const playerMove = () => {
//         console.log("move " + selectedPiece.row + " " + selectedPiece.column + " to " + selectedSquare.row + " " + selectedSquare.column);
//         const piece = findPiece(selectedPiece.row, selectedPiece.column);
//         piece.position.row = selectedSquare.row;
//         piece.position.column = selectedSquare.column;
//         console.log(bordState);
//         setSelectedPiece({});
//         setSelectedSquare({});
//         computerMove();
//     }
//     const computerMove = () => {
//         getComputerMove(bordState)
//             .then(response => {
//                 console.log("response: " + response);
//                 setBordState(response);
//             })
//             .catch((error) => {
//                 console.log("error " + error);
//                 setError(error.toString())
//             });
//     };
// }
//
// export default Movement