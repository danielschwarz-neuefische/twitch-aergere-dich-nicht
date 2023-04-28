import React from 'react';
import './App.css';
import useWebSocket from "react-use-websocket";

export default function App() {

    const [status, setStatus] = React.useState<string>();
    const [board, setBoard] = React.useState<string[][]>(
        [
            [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
            [' ', 'D', 'D', ' ', ' ', 'O', 'O', 'O', ' ', ' ', 'A', 'A', ' '],
            [' ', 'D', 'D', ' ', ' ', 'O', 'A', 'O', ' ', ' ', 'A', 'A', ' '],
            [' ', ' ', ' ', ' ', ' ', 'O', 'A', 'O', ' ', ' ', ' ', ' ', ' '],
            [' ', ' ', ' ', ' ', ' ', 'O', 'A', 'O', ' ', ' ', ' ', ' ', ' '],
            [' ', 'O', 'O', 'O', 'O', 'O', 'A', 'O', 'O', 'O', 'O', 'O', ' '],
            [' ', 'O', 'D', 'D', 'D', 'D', ' ', 'B', 'B', 'B', 'B', 'O', ' '],
            [' ', 'O', 'O', 'O', 'O', 'O', 'C', 'O', 'O', 'O', 'O', 'O', ' '],
            [' ', ' ', ' ', ' ', ' ', 'O', 'C', 'O', ' ', ' ', ' ', ' ', ' '],
            [' ', ' ', ' ', ' ', ' ', 'O', 'C', 'O', ' ', ' ', ' ', ' ', ' '],
            [' ', 'C', 'C', ' ', ' ', 'O', 'C', 'O', ' ', ' ', 'B', 'B', ' '],
            [' ', 'C', 'C', ' ', ' ', 'O', 'O', 'O', ' ', ' ', 'B', 'B', ' '],
            [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
        ]
    );
    const [positions, setPositions] = React.useState<number[][]>(
        [
            [7, 0],
            [7, 1],
            [7, 2],
            [7, 3],
            [7, 4],
            [8, 4],
            [9, 4],
            [10, 4],
            [11, 4],
            [11, 5],
            [11, 6],
            [10, 6],
            [9, 6],
            [8, 6],
            [7, 6],
        ]
    );

    const webSocket = useWebSocket("ws://localhost:8080/ws/game", {
        onOpen: () => console.log("opened"),
        onMessage: (msg) => {
            setStatus(msg.data);
        },
    })

    function roleDice() {
        webSocket.sendMessage(".");
    }

    return (
        <div>
            {status}
            <div id="board-and-player">
                <div id="board">
                    {
                        board.map((row, i) => {
                            return row.map((cell, j) => {
                                return (
                                    <div className="cell" key={j} style={{
                                        gridColumn: j + 1,
                                        gridRow: i + 1,
                                    }}>{cell}</div>
                                )
                            })
                        })
                    }
                </div>
                <div id="player" style={{
                    left: (positions[0][0]) * 40,
                    top: (positions[0][1] + 1) * 40,
                    color: 'blue',
                }}>
                    ●
                </div>
            </div>
            <button onClick={roleDice}>Role dice</button>
        </div>
    );
}
