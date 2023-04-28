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
            <pre>
                {
                    board.map((row, i) => {
                        return (
                            <div key={i}>
                                {
                                    row.map((cell, j) => {
                                        return (
                                            <span key={j}>{cell}</span>
                                        )
                                    })
                                }
                            </div>
                        )
                    })
                }
            </pre>
            <button onClick={roleDice}>Role dice</button>
        </div>
    );
}
