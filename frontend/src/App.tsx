import React from 'react';
import './App.css';
import useWebSocket from "react-use-websocket";
import {GameStatus} from "./GameStatus";
import Board from "./Board";
import {PlayerStatus} from "./PlayerStatus";

export default function App() {

    const [status, setStatus] = React.useState<PlayerStatus>();
    const webSocket = useWebSocket("ws://localhost:8080/ws/game", {
        onOpen: () => console.log("opened"),
        onMessage: (msg) => {
            setStatus(JSON.parse(msg.data));
        },
    })

    function rollDice() {
        webSocket.sendMessage("rollDice");
    }

    function reset() {
        webSocket.sendMessage("reset");
    }

    if (!status) {
        return <>Loading...</>
    }
    return <Board status={status} reset={reset} rollDice={rollDice}></Board>

}
