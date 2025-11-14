package com.kotlin.example.leetcode.graph

class CountIslands {
}

/**
 * Determines the number of islands in a 2D binary grid.
 *
 * An island is surrounded by water ('0') and is formed by connecting adjacent lands ('1')
 * horizontally or vertically.
 *
 * @param grid The m x n grid represented as Array<CharArray>.
 * @return The total number of islands.
 */
fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty() || grid[0].isEmpty()) {
        return 0
    }

    val rows = grid.size
    val cols = grid[0].size
    var islandCount = 0

    // 1. Iterate through every cell in the grid
    for (r in 0 until rows) {
        for (c in 0 until cols) {
            // If we find a piece of land ('1')...
            if (grid[r][c] == '1') {
                // ... we found a new, uncounted island.
                islandCount++

                // 2. Sink the entire island using DFS so it's not counted again.
                // We pass the grid directly, modifying it in place to mark visited land ('0').
                sinkIsland(grid, r, c)
            }
        }
    }

    return islandCount
}

/**
 * Recursive DFS helper function to "sink" (mark as visited) an entire island.
 * Modifies the input grid by changing '1's to '0's.
 */
fun sinkIsland(grid: Array<CharArray>, r: Int, c: Int) {
    val rows = grid.size
    val cols = grid[0].size

    // Base Case 1: Check boundaries
    if (r < 0 || r >= rows || c < 0 || c >= cols) {
        return
    }

    // Base Case 2: If it's water ('0'), stop the search
    if (grid[r][c] == '0') {
        return
    }

    // Action: Mark the current cell as visited/sunk by changing it to '0'.
    grid[r][c] = '0'

    // Recursively check all four neighbors (Up, Down, Left, Right)
    sinkIsland(grid, r + 1, c) // Down
    sinkIsland(grid, r - 1, c) // Up
    sinkIsland(grid, r, c + 1) // Right
    sinkIsland(grid, r, c - 1) // Left
}

fun main() {
    // Example 1: Three separate islands
    val grid1: Array<CharArray> = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )
    // Expected Output: 1 (All '1's are connected)
    println("Grid 1 Islands: ${numIslands(grid1)}")

    // Example 2: Three distinct islands
    val grid2: Array<CharArray> = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '1', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )
    // Expected Output: 3
    println("Grid 2 Islands: ${numIslands(grid2)}")
}