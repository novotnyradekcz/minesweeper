# Minesweeper
Minesweeper project created as part of the JetBrains Academy Kotlin Basics course


## Task:
### Description

The game starts with an unexplored minefield that has a user-defined number of mines.

The player can:

- Mark unexplored cells as cells that potentially have a mine, and also remove those marks. Any empty cell can be marked, not just the cells that contain a mine. The mark is removed by marking the previously marked cell.
- Explore a cell if they think it does not contain a mine.

There are three possibilities after exploring a cell:

1. If the cell is empty and has no mines around, all the cells around it, including the marked ones, can be explored, and it should be done automatically. Also, if next to the explored cell there is another empty one with no mines around, all the cells around it should be explored as well, and so on, until no more can be explored automatically.
2. If a cell is empty and has mines around it, only that cell is explored, revealing a number of mines around it.
3. If the explored cell contains a mine, the game ends and the player loses.

There are two possible ways to win:

1. Marking all the cells that have mines correctly.
2. Opening all the safe cells so that only those with unexplored mines are left.

### Objectives

In this final stage, your program should contain the following additional functionality:

1. Print the current state of the minefield starting with all unexplored cells at the beginning, ask the player for their next move with the message “`Set/unset mine marks or claim a cell as free:`”, treat the player's move according to the rules, and print the new minefield state. Ask for the player's next move until the player wins or steps on a mine. The player's input contains a pair of cell coordinates and a command: `mine` to mark or unmark a cell, `free` to explore a cell.
2. If the player explores a mine, print the field in its current state, with mines shown as `X` symbols. After that, output the message “`You stepped on a mine and failed!`”.
3. Generate mines like in the original game: the first cell explored with the `free` command cannot be a mine; it should always be empty. You can achieve this in many ways – it's up to you.

Use the following symbols to represent each cell’s state:

- `.` as unexplored cells

- `/` as explored free cells without mines around it

- Numbers from 1 to 8 as explored free cells with 1 to 8 mines around them, respectively

- `X` as mines

- `*` as unexplored marked cells
