package minesweeper

import kotlin.random.Random

fun generateMines(minefield: Array<Array<String>>) {
    println("How many mines do you want on the field?")
    val mines = readln().toInt()
    for (i in 1..mines) {
        var index1: Int
        var index2: Int
        do {
            index1 = Random.nextInt(0, 9)
            index2 = Random.nextInt(0, 9)
        } while (minefield[index1][index2] != "/")
        minefield[index1][index2] = "X"
    }
}

fun initialiseMines(minefield: Array<Array<String>>) {
    var mineCount: Int
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (minefield[i][j] == "X") continue
            mineCount = 0
            if (i != 0 && j != 0 && minefield[i - 1][j - 1] == "X") mineCount++
            if (i != 0 && minefield[i - 1][j] == "X") mineCount++
            if (i != 0 && j != 8 && minefield[i - 1][j + 1] == "X") mineCount++
            if (j != 0 && minefield[i][j - 1] == "X") mineCount++
            if (minefield[i][j] == "X") mineCount++
            if (j != 8 && minefield[i][j + 1] == "X") mineCount++
            if (i != 8 && j != 0 && minefield[i + 1][j - 1] == "X") mineCount++
            if (i != 8 && minefield[i + 1][j] == "X") mineCount++
            if (i != 8 && j != 8 && minefield[i + 1][j + 1] == "X") mineCount++
            if (mineCount != 0) minefield[i][j] = mineCount.toString()
        }
    }
}

fun printField(minefield: Array<Array<String>>) {
    println(" |123456789|")
    println("-|---------|")
    for (i in 0 until 9) {
        print("${i + 1}|")
        for (j in 0 until 9) print(minefield[i][j])
        print("|\n")
    }
    println("-|---------|")
}

fun scanField(playfield: Array<Array<String>>, minefield: Array<Array<String>>): Boolean {
    var mineCount = 0
    var markCount = 0
    var scoreCount = 0
    var deathCount = 0  // check if mines are revealed = game over
    var dotCount = 0    // check if only unmarked and marked mines are left
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (minefield[i][j] == "X") mineCount++
            if (playfield[i][j] == "*") markCount++
            if (minefield[i][j] == "X" && playfield[i][j] != "*") scoreCount++
            if (playfield[i][j] == "X") deathCount++
            if (playfield[i][j] == ".") dotCount++
        }
    }
    if ((mineCount == (markCount + dotCount)) || deathCount != 0) return false
    return true
}

fun showMines(playfield: Array<Array<String>>, minefield: Array<Array<String>>) {
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (minefield[i][j] == "X") playfield[i][j] = "X"
        }
    }
}

fun revealMines(i: Int, j: Int, playfield: Array<Array<String>>, minefield: Array<Array<String>>) {
    if ((playfield[i][j] == "." || playfield[i][j] == "*") && minefield[i][j] == "/") {
        playfield[i][j] = "/"
        if (i != 0 && j != 0) revealMines(i - 1, j - 1, playfield, minefield)
        if (i != 0) revealMines(i - 1, j, playfield, minefield)
        if (i != 0 && j != 8) revealMines(i - 1, j + 1, playfield, minefield)
        if (j != 0) revealMines(i, j - 1, playfield, minefield)
        if (j != 8) revealMines(i, j + 1, playfield, minefield)
        if (i != 8 && j != 0) revealMines(i + 1, j - 1, playfield, minefield)
        if (i != 8) revealMines(i + 1, j, playfield, minefield)
        if (i != 8 && j != 8) revealMines(i + 1, j + 1, playfield, minefield)
    }
    playfield[i][j] = minefield[i][j]
}

fun turn(playfield: Array<Array<String>>, minefield: Array<Array<String>>): Array<Array<String>> {
    println("Set/unset mine marks or claim a cell as free:")
    val playerInput = readln()
    val i = playerInput[2].toString().toInt() - 1
    val j = playerInput[0].toString().toInt() - 1
    val action = playerInput.substring(4)
    if (action == "free") {
        if (minefield[i][j] == "X") {
            showMines(playfield, minefield)
        } else if (minefield[i][j] in "/12345678") {
            revealMines(i, j, playfield, minefield)
        }
    } else if (action == "mine") {
        if (playfield[i][j] == ".") playfield[i][j] = "*"
        else if (playfield[i][j] == "*") playfield[i][j] = "."
    } else {
        println("Invalid input, try again:")
        return playfield
    }
    printField(playfield)
    return playfield
}

fun main() {
    val minefield = arrayOf(arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"),
        arrayOf("/", "/", "/", "/", "/", "/", "/", "/", "/"))
    var playfield = arrayOf(arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        arrayOf(".", ".", ".", ".", ".", ".", ".", ".", "."))
    // create minefield
    generateMines(minefield)
    initialiseMines(minefield)
    // show playing field
    printField(playfield)
    // Start playing
    while (scanField(playfield, minefield)) {
        playfield = turn(playfield, minefield)
    }
    println("Congratulations! You found all the mines!")

}
