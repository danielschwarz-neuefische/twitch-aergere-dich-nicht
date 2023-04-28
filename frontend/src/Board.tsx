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
                    top: (positions[props.status?.playerPosition-1]?.[1] + 1) * 40,
                    color: 'blue',
                }}>
                    ●
                </div>
            </div>
            {props.status?.rolledNumber && <>Du hast eine {props.status?.rolledNumber} gewürfelt.</>}<br/>
            <button onClick={props.roleDice}>Role dice</button>
        </div>
    );
}









