package com.kotlin.example.algoritm


import java.util.*

/**
 * Implements Dijkstra's algorithm to find the shortest path from a starting node
 * to all other nodes in a weighted graph.
 *
 * This implementation uses an adjacency list representation for the graph and
 * a PriorityQueue for efficient selection of the next unvisited node with the
 * smallest current distance.
 *
 * @param graph The adjacency list representation of the graph:
 * Map<Node, List<Pair<Neighbor, Weight>>>
 * @param startNode The source node to calculate shortest paths from.
 * @return A map containing the shortest distance from the startNode to every
 * other node in the graph. Nodes not reachable will not be present.
 */
fun dijkstra(graph: Map<Int, List<Pair<Int, Int>>>, startNode: Int): Map<Int, Int> {
    // 1. Initialize data structures
    // Stores the shortest distance found so far from the start node to all other nodes.
    // We use a Map to represent "infinity" for nodes not yet reached, and the key is the node ID.
    val distances: MutableMap<Int, Int> = mutableMapOf<Int, Int>()

    // Priority Queue stores pairs of (distance, node) and prioritizes the smallest distance.
    // It is the core of Dijkstra's efficiency, ensuring we always explore the shortest path first.
    // The comparator ensures the element with the smallest distance (first element of Pair) is at the top.
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

    // 2. Setup the start node
    distances[startNode] = 0
    priorityQueue.add(Pair(0, startNode)) // (Distance, Node)

    // 3. Main loop: process nodes in order of increasing distance
    while (priorityQueue.isNotEmpty()) {
        val (currentDistance, currentNode) = priorityQueue.poll()

        // Optimization: If we found a shorter path to currentNode already, skip.
        // This handles stale entries in the priority queue.
        if (currentDistance > (distances[currentNode] ?: Int.MAX_VALUE)) {
            continue
        }

        // 4. Explore neighbors (Relaxation step)
        // Get the neighbors of the current node from the graph.
        val neighbors: List<Pair<Int, Int>> = graph[currentNode] ?: continue

        for ((neighborNode, weight) in neighbors) {
            val newDistance = currentDistance + weight

            // Check if this new path is shorter than the currently recorded distance
            // to the neighbor.
            if (newDistance < (distances[neighborNode] ?: Int.MAX_VALUE)) {
                // Found a shorter path! Update distance and add/re-add to the queue.
                distances[neighborNode] = newDistance
                priorityQueue.add(Pair(newDistance, neighborNode))
            }
        }
    }

    return distances
}

fun main() {
    // Define the graph using an adjacency list:
    // Node -> List of (Neighbor Node, Weight)
    // Example Graph:
    // (A) --4--> (B) --8--> (C)
    // |         / \        /
    // 8      11  2       4
    // |     /     \     /
    // (D) --7--> (E) --1--> (F)
    //
    // Mapped to integers: A=0, B=1, C=2, D=3, E=4, F=5
    val sampleGraph = mapOf(
        0 to listOf(Pair(1, 4), Pair(3, 8)), // A -> B (4), A -> D (8)
        1 to listOf(Pair(2, 8), Pair(3, 11), Pair(4, 2)), // B -> C (8), B -> D (11)
        2 to listOf(Pair(5, 4)), // C -> F (4)
        3 to listOf(Pair(4, 7)), // D -> E (7)
        4 to listOf(Pair(2, 2), Pair(5, 1)), // E -> C (2), E -> F (1)
        5 to emptyList() // F is a sink node
    )

    val startNode = 0 // Start from node A

    println("Running Dijkstra's algorithm starting from node $startNode (A)...")
    val shortestDistances = dijkstra(sampleGraph, startNode)

    println("\nShortest Distances from Node $startNode:")
    // The nodes are 0 (A) to 5 (F).
    val nodeNames = mapOf(0 to "A", 1 to "B", 2 to "C", 3 to "D", 4 to "E", 5 to "F")

    // We can iterate through the graph keys to ensure we print all possibilities
    // (though unreachable nodes won't be in the distances map if not started at 0).
    for (nodeId in 0..5) {
        val name = nodeNames[nodeId] ?: nodeId
        val distance = shortestDistances[nodeId]

        if (distance != null) {
            println("Node $name ($nodeId): $distance")
        } else {
            // Note: In this specific graph, all nodes are reachable from 0.
            // If the graph was disconnected, some nodes might show "Not Reachable".
            println("Node $name ($nodeId): Not Reachable")
        }
    }
}
