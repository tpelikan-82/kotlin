package com.kotlin.example.algoritm

import java.util.LinkedList
import java.util.Queue

data class Node(
    val value: Int,
    val children: MutableList<Node> = mutableListOf()
)

fun breadthFirstSearch(root: Node) {

    val queue: Queue<Node> = LinkedList()
    queue.offer(root) // offer is better than add

    println("BFS search node by level:")

    // until queue is empty
    while (queue.isNotEmpty()) {

        val currentNode = queue.poll()

        print("${currentNode.value} ")

        for (child in currentNode.children) {
            queue.offer(child)
        }
    }
    println()
}

fun depthFirstSearchRecursive(node: Node) {


    print("${node.value} ")

    for (child in node.children) {
        depthFirstSearchRecursive(child)
    }
}


fun main() {

    // Creation tree structure
    //      1
    //    / | \
    //   2  3  4
    //  / \    |
    // 5   6   7

    val node7 = Node(7)
    val node5 = Node(5)
    val node6 = Node(6)
    val node2 = Node(2, mutableListOf(node5, node6))
    val node3 = Node(3)
    val node4 = Node(4, mutableListOf(node7))
    val root = Node(1, mutableListOf(node2, node3, node4))

    breadthFirstSearch(root)

    println("Search to deep")
    depthFirstSearchRecursive(root)

}
