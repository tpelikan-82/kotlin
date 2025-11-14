package com.kotlin.example.leetcode.binaryTree

import java.util.LinkedList
import java.util.Queue

class BinarySearchMinDiff {
}

fun getMinimumDifference(root: TreeNode?): Int {
    // State variables shared across recursive calls
    var minDiff = Int.MAX_VALUE
    var prevValue: Int? = null

    /**
     * Performs a recursive In-Order Traversal (Left -> Root -> Right).
     */
    fun inOrderTraversal(node: TreeNode?) {
        if (node == null) {
            return
        }

        // 1. Traverse Left (Left subtree)
        inOrderTraversal(node.left)

        // 2. Process Root (Current Node)
        if (prevValue != null) {
            // Since this is an in-order traversal, prevValue is the node immediately
            // smaller than the current node. Their difference must be the minimum
            // difference found so far or the next candidate for the minimum.
            val currentDiff = node.value - prevValue!!
            minDiff = minOf(minDiff, currentDiff)
            if (minDiff == 1) { return }
        }

        // Update prevValue to the current node's value for the next comparison
        prevValue = node.value

        // 3. Traverse Right (Right subtree)
        inOrderTraversal(node.right)
    }

    inOrderTraversal(root)
    return minDiff
}

fun binarySearchSimpleDepth(root: TreeNode?) {



    if (root == null) return

    println(root.value)

    binarySearchSimpleDepth(root.left)
    binarySearchSimpleDepth(root.right)

}

fun binarySearchBreadth(root: TreeNode?) {

    println("Binary search breadth")

    if (root == null) return

    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root)
    while (!queue.isEmpty()) {

        val node = queue.poll();
        println(node.value)
        node.left?.let {  queue.offer(it)  }
        node.right?.let {  queue.offer(it) }

    }

}

fun main() {
    // Example 1: Simple tree
    //     4
    //    / \
    //   2   6
    //  / \
    // 1   3
    // Sorted order: 1, 2, 3, 4, 6
    // Differences: 2-1=1, 3-2=1, 4-3=1, 6-4=2
    // Expected Min Difference: 1

    val node1 = TreeNode(1)
    val node3 = TreeNode(3)
    val node2 = TreeNode(2, node1, node3)
    val node6 = TreeNode(6)
    val root1 = TreeNode(4, node2, node6)

    println("Binary search simple depth")
    binarySearchSimpleDepth(root1);
    binarySearchBreadth(root1);

    println("Example 1 (Min Diff 1): ${getMinimumDifference(root1)}")

    // Example 2: Larger difference
    //     1
    //      \
    //       3
    //      /
    //     2
    // Sorted order: 1, 2, 3
    // Differences: 2-1=1, 3-2=1
    // Expected Min Difference: 1

    val root2 = TreeNode(1, right = TreeNode(3, left = TreeNode(2)))
    println("Example 2 (Min Diff 1): ${getMinimumDifference(root2)}")

    // Example 3: Larger difference
    //     10
    //      \
    //       15
    //      /
    //     13
    // Sorted order: 10, 13, 15
    // Differences: 13-10=3, 15-13=2
    // Expected Min Difference: 2

    val root3 = TreeNode(10, right = TreeNode(15, left = TreeNode(13)))
    println("Example 3 (Min Diff 2): ${getMinimumDifference(root3)}")
}