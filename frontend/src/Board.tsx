import React from "react";
import {GameStatus} from "./GameStatus";

type Props = {
    status: GameStatus;
    roleDice: () => void
}
export default function Board(props: Props) {

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
            [7, 1],
            [7, 2],
            [7, 3],
            [7, 4],
            [7, 5],
            [8, 5],
            [9, 5],
            [10, 5],
            [11, 5],
            [11, 6],
            [11, 7],
            [10, 7],
            [9, 7],
            [8, 7],
            [7, 7],
            [7, 8],
            [7, 9],
            [7, 10],
            [7, 11],
            [6, 11],
            [5, 11],
            [5, 10],
            [5, 9],
            [5, 8],
            [5, 7],
            [4, 7],
            [3, 7],
            [2, 7],
            [1, 7],
            [1, 6],
            [1, 5],
            [2, 5],
            [3, 5],
            [4, 5],
            [5, 5],
            [5, 4],
            [5, 3],
            [5, 2],
            [5, 1],
            [6, 1],
            [6, 2],
            [6, 3],
            [6, 4],
            [6, 5],
        ]
    );

    return (
        <div>
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
                    left: (positions[props.status?.playerPosition-1]?.[0]) * 40,
                    top: (positions[props.status?.playerPosition-1]?.[1]) * 40,
                    color: 'blue',
                }}>
                    ●
                </div>
            </div>
            {props.status?.rolledNumber && <>Du hast eine {props.status?.rolledNumber} gewürfelt.</>}<br/>
            <button onClick={props.roleDice}>Role dice</button>
            props.status?.playerPosition: {props.status?.playerPosition}
            positions[props.status?.playerPosition-1]?.[0]: {positions[props.status?.playerPosition-1]?.[0]}
            positions[props.status?.playerPosition-1]?.[1]: {positions[props.status?.playerPosition-1]?.[1]}
        </div>
    );
}









