import React from 'react';
import './App.css';
import useWebSocket from "react-use-websocket";
import {GameStatus} from "./GameStatus";
import Board from "./Board";

export default function App() {

    const [status, setStatus] = React.useState<GameStatus>();
    const webSocket = useWebSocket("ws://localhost:8080/ws/game", {
        onOpen: () => console.log("opened"),
        onMessage: (msg) => {
            setStatus(JSON.parse(msg.data));
        },
    })

    function roleDice() {
        webSocket.sendMessage(".");
    }

    if (!status) {
        return <>Loading...</>
    }
    return <Board status={status} roleDice={roleDice}></Board>

}
