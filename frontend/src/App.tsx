import React from 'react';
import './App.css';
import useWebSocket from "react-use-websocket";

export default function App() {

    useWebSocket("ws://localhost:8080/ws/game")

    return <>Hallo Insel</>;
}
