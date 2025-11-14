package com.kotlin.example.leetcode.binaryTree

import java.util.LinkedList
import java.util.Queue

class CountAllNodes {
}



private fun treeHeight(root: TreeNode?): Int {
    var h = 0
    var node = root
    while (node != null) {
        h++
        node = node.left
    }
    return h
}

/**
 * Main function to count the nodes.
 * Time Complexity: O(log^2 N)
 * Space Complexity: O(log N) for the recursion stack
 */
fun countNodesAI(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    // 1. Calculate the height of the current node's left and right subtrees
    val leftHeight = treeHeight(root.left)
    val rightHeight = treeHeight(root.right)

    // 2. Check for the Perfect Binary Tree (PBT) condition

    // Case A: The left subtree is a Perfect Binary Tree (PBT).
    // This happens if traversing all the way down the left path from the left child
    // yields the same height as traversing the left path from the right child.
    if (leftHeight == rightHeight) {
        // Since the left subtree is PBT, we calculate its nodes instantly: 2^h - 1
        // Total nodes = (1 << leftHeight) - 1 (Left Subtree)
        //             + 1 (Root)
        //             + countNodes(root.right) (Incomplete/Recursive Part)

        // Simplified: (1 << leftHeight) + countNodes(root.right)
        // (1 shl leftHeight) calculates 2^leftHeight
        return (1 shl leftHeight) + countNodes(root.right)
    }

    // Case B: The right subtree is a Perfect Binary Tree (PBT).
    // If the left height is greater, the 'gap' is in the left side, meaning
    // the right side must be a complete level up, making the right subtree PBT.
    else {
        // Since the right subtree is PBT, we calculate its nodes instantly: 2^h - 1
        // Total nodes = countNodes(root.left) (Incomplete/Recursive Part)
        //             + 1 (Root)
        //             + (1 << rightHeight) - 1 (Right Subtree)

        // Simplified: (1 << rightHeight) + countNodes(root.left)
        // (1 shl rightHeight) calculates 2^rightHeight
        return (1 shl rightHeight) + countNodes(root.left)
    }
}




fun countNodes(root: TreeNode?): Int {

    if (root == null) return 0

    var count = 0

    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root)
    while (!queue.isEmpty()) {

        count ++
        val node = queue.poll();
        println(node.value)
        node.left?.let { queue.offer(it) }
        node.right?.let { queue.offer(it) }

    }

    return count

}

fun main(args: Array<String>) {

    val node1 = TreeNode(1)
    val node3 = TreeNode(3)
    val node2 = TreeNode(2, node1, node3)
    val node6 = TreeNode(6)
    val root1 = TreeNode(4, node2, node6)

    println(countNodes(root1))

    println(countNodesAI(root1))

}