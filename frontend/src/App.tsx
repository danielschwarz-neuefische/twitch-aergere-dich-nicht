import React from 'react';
import './App.css';
import useWebSocket from "react-use-websocket";

export default function App() {

    const [board, setBoard] = React.useState<string>();

    const webSocket = useWebSocket("ws://localhost:8080/ws/game", {
        onOpen: () => console.log("opened"),
        onMessage: (msg) => {
            setBoard(msg.data);
        },
    })

    function roleDice() {
        webSocket.sendMessage(".");
    }

    return (
        <div>
            {board}
            <button onClick={roleDice}>Role dice</button>
        </div>
    );
}
