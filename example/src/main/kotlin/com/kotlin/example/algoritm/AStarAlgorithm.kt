package com.kotlin.example.algoritm

import java.util.LinkedList
import java.util.PriorityQueue

class AStarAlgorithm {
}

// Represents a position on the grid
data class Point(val x: Int, val y: Int)

// Represents a node in the A* search
data class NodeA(
    val point: Point,
    var gCost: Int,        // Cost from start to this node
    var hCost: Int,        // Heuristic cost from this node to goal
    var parent: NodeA? = null
) : Comparable<NodeA> {
    // fCost = gCost + hCost
    val fCost: Int get() = gCost + hCost

    // Compare nodes based on fCost (for PriorityQueue)
    override fun compareTo(other: NodeA): Int {
        if (fCost != other.fCost) {
            return fCost.compareTo(other.fCost)
        }
        // Tie-breaker: prefer node with lower hCost
        return hCost.compareTo(other.hCost)
    }
}

// --- Heuristic Function (Manhattan Distance) ---
fun calculateHCost(a: Point, b: Point): Int {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y)
}

// --- Neighbor Finding ---
fun getNeighbors(p: Point, rows: Int, cols: Int): List<Point> {
    val neighbors = mutableListOf<Point>()

    // Possible movements (Up, Down, Left, Right)
    val directions = listOf(
        Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0)
    )

    for ((dx, dy) in directions) {
        val newX = p.x + dx
        val newY = p.y + dy

        // Check boundary conditions
        if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
            neighbors.add(Point(newX, newY))
        }
    }
    return neighbors
}

// --- Path Reconstruction ---
fun reconstructPath(endNode: NodeA): List<Point> {
    val path = LinkedList<Point>()
    var current: NodeA? = endNode
    while (current != null) {
        path.addFirst(current.point)
        current = current.parent
    }
    return path
}

fun findPath(grid: Array<IntArray>, start: Point, end: Point): List<Point>? {
    val rows = grid.size
    val cols = grid[0].size

    // Min-Priority Queue of nodes to be evaluated (Open Set)
    val openSet = PriorityQueue<NodeA>()

    // Tracks the minimum gCost found so far for a given point
    val gCosts = mutableMapOf<Point, Int>()

    // Tracks the node that came before the current node on the cheapest path (for path reconstruction)
    val cameFrom = mutableMapOf<Point, NodeA?>()

    // --- Initialization ---
    val startNode = NodeA(start, 0, calculateHCost(start, end))
    openSet.add(startNode)
    gCosts[start] = 0
    cameFrom[start] = null

    while (openSet.isNotEmpty()) {
        val current = openSet.poll()

        // --- Goal Check ---
        if (current.point == end) {
            return reconstructPath(current)
        }

        // --- Explore Neighbors ---
        val neighbors = getNeighbors(current.point, rows, cols)

        for (neighborPoint in neighbors) {
            // Check if it's an obstacle
            if (grid[neighborPoint.y][neighborPoint.x] == 1) continue

            // Tentative gCost is the gCost to current + cost to move to neighbor (assuming 1 for all moves)
            val tentativeGCost = current.gCost + 1

            // If this path to neighbor is better than any previous one
            if (tentativeGCost < (gCosts[neighborPoint] ?: Int.MAX_VALUE)) {

                val neighborNode = NodeA(
                    point = neighborPoint,
                    gCost = tentativeGCost,
                    hCost = calculateHCost(neighborPoint, end),
                    parent = current
                )

                gCosts[neighborPoint] = tentativeGCost
                cameFrom[neighborPoint] = current

                // If the neighbor is not already in the open set, add it
                if (!openSet.contains(neighborNode)) {
                    openSet.add(neighborNode)
                }
                // If it is in the open set, the PriorityQueue handles the update implicitly
                // because a new Node with a lower fCost will be processed before the older one.
                // For a more complex implementation, you might need to manually update or remove/re-add the node.
            }
        }
    }

    // No path found
    return null
}

fun main() {
    // 5x5 Grid: 0 = clear, 1 = obstacle
    // Y-axis (rows) is the first index, X-axis (columns) is the second
    val grid = arrayOf(
        intArrayOf(0, 0, 0, 0, 0), // (0,0) Start
        intArrayOf(0, 1, 1, 0, 0),
        intArrayOf(0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 0)  // (4,4) End
    )

    val start = Point(0, 0)
    val end = Point(4, 4)

    val path = findPath(grid, start, end)

    if (path != null) {
        println("Path Found:")
        path.forEachIndexed { index, point ->
            print("(${point.x}, ${point.y})")
            if (index < path.size - 1) print(" -> ")
        }
        println("\nPath Length (steps): ${path.size - 1}")
    } else {
        println("No path found between $start and $end.")
    }
}