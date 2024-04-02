import React from 'react';
import Chessboard from "./views/board/bord";
import { jsonData } from "./api/example";


const App = () => {
  return (
      <div>
        <h1>Chessboard</h1>
        <Chessboard pieces={jsonData.pieces} />
      </div>
  );
}

export default App;
