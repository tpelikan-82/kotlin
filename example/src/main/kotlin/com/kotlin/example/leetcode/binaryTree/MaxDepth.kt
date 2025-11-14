package com.kotlin.example.leetcode.binaryTree

class MaxDepth {
}


/**
 * Complexity	Result
 * Time (Speed)	O(N) (Linear)
 * Space (Memory - Call Stack)	O(H) or O(N) (Worst Case)
 */


fun maxDepth(root: TreeNode?): Int {

    println(root?.value)

    // Base Case 1: If the node is null, we've gone past a leaf, so the depth contribution is 0.
    if (root == null) {
        return 0
    }

    // 1. Recursively find the maximum depth of the left subtree.
    val leftDepth = maxDepth(root.left)

    // 2. Recursively find the maximum depth of the right subtree.
    val rightDepth = maxDepth(root.right)

    // 3. The depth of the current tree is 1 (for the current node) plus
    //    the maximum of the left and right subtrees.
    return 1 + maxOf(leftDepth, rightDepth)
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

    // Example 2: Simple tree with depth 2
    val root2 = TreeNode(1, left = TreeNode(2))

    // Example 3: Empty tree with depth 0
    val root3: TreeNode? = null


    println("--- Maximum Depth Calculation ---")
    println("Tree 1 (Depth 3): ${maxDepth(root)}")
    println("Tree 2 (Depth 2): ${maxDepth(root2)}")
    println("Tree 3 (Depth 0): ${maxDepth(root3)}")
}