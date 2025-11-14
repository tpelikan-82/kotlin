package com.kotlin.example.leetcode.binaryTree

import java.util.ArrayDeque
import java.util.Queue

class AverageValue {
}


//class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null) {
//}

/**
 * Breadth-First Search (BFS)
 * time complexity O(N) N is number of nodes
 * space complexity O(W) W is number of nodes in queue
  */

fun averageOfLevels(root: TreeNode?): DoubleArray {

    if (root == null) return  doubleArrayOf()


    val result : MutableList<Double> = mutableListOf()


    val queue: Queue<TreeNode> = ArrayDeque<TreeNode>()
    queue.offer(root)



    while (!queue.isEmpty()) {

        var levelSum: Long = 0

        val queueDimension = queue.size;

        for(i in 0 until queueDimension) {

            val node = queue.poll();

            levelSum += node.value


//            node.left?.also { queue.offer(it) }

            node.left?.also { queue.offer(it) }
            node.right?.also { queue.offer(it) }


        }

        result.add(levelSum.toDouble()/queueDimension)

    }

    return result.toDoubleArray()

}

fun main() {
    // Example Tree Structure:
    //      3
    //     / \
    //    9  20
    //       / \
    //      15  7
    // Max Depth should be 3 (path: 3 -> 20 -> 15 or 3 -> 20 -> 7)

    val node15 = TreeNode(15)
    val node7 = TreeNode(7)
    val node20 = TreeNode(20, node15, node7)
    val node9 = TreeNode(9)
    val root = TreeNode(3, node9, node20)



    val result: DoubleArray = averageOfLevels(root)

    result.forEach { print("$it ") }



}