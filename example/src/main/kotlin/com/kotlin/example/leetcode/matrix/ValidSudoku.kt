package com.kotlin.example.leetcode.matrix

class ValidSudoku {
}

/**
 * Complexity	Result
 * Time (Speed)	O(N * N)
 */

fun isValidSudoku(board: Array<CharArray>): Boolean {

    val rows : Array<MutableSet<Char>> = Array(9) { mutableSetOf<Char>() }
    val columns : Array<MutableSet<Char>> = Array(9) { mutableSetOf<Char>() }
    val boxes : Array<MutableSet<Char>> = Array(9) { mutableSetOf<Char>() }


    for (i in board.indices) {
        for (j in board[i].indices) {

            val cellValue = board[i][j]

            if (cellValue == '.') {
                continue
            }

            if (cellValue in rows[i]) {
                // Duplicate found in the current row
                return false
            }
            rows[i].add(cellValue)

            if (cellValue in columns[j]) {
                // Duplicate found in the current column
                return false
            }
            columns[j].add(cellValue)

            // i =  0 .. 2 &&  j = 0 .. 2 => 0
            // i = 0 .. 2 && j = 6 .. 8 => 2
            // i = 3 .. 5 && j = 0 .. 2 => 3
            //...
            // i = 6 .. 8 && j = 6 .. 8 => 9
            val boxIndex = (i / 3) * 3 + (j / 3)



            if (cellValue in boxes[boxIndex]) {
                // Duplicate found in the current 3x3 box
                return false
            }
            boxes[boxIndex].add(cellValue)





//            println)
//            println(board[j][i])
        }
    }


    return true

}

fun main(){


    val sudokuBoard: Array<CharArray> = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )

    println(isValidSudoku(sudokuBoard))

    println(sudokuBoard[0][0])


}