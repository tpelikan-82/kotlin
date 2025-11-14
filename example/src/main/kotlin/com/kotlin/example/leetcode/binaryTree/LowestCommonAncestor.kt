package com.kotlin.example.leetcode.binaryTree

class LowestCommonAncestor {
}

fun lowestCommonAncestor(root: TreeNode?, p: Int, q: Int): TreeNode? {
    if (root == null) return null

    var current = root

    // Loop until we find the LCA
    while (current != null) {
        // Case 1: Both nodes (p and q) are greater than the current node's value.
        // The LCA must be in the RIGHT subtree.
        if (p > current.value && q > current.value) {
            current = current.right
        }
        // Case 2: Both nodes (p and q) are smaller than the current node's value.
        // The LCA must be in the LEFT subtree.
        else if (p < current.value && q < current.value) {
            current = current.left
        }
        // Case 3: Split Point or Found One of the Nodes.
        // If the current node's value is between p and q (inclusive),
        // then the current node is the LCA.
        else {
            return current
        }
    }

    // Should not be reached if p and q exist in the tree structure starting from the root
    return null
}


/**
 * Finds the Lowest Common Ancestor (LCA) of two nodes (p and q) in a generic,
 * unsorted binary tree using a recursive Depth First Search (DFS) approach.
 *
 * The LCA is the node where the paths from the root to p and q first diverge.
 * * @param root The root of the generic binary tree.
 * @param p The value of the first target node.
 * @param q The value of the second target node.
 * @return The TreeNode that is the LCA, or null if the root is null or nodes are not found.
 */
fun lowestCommonAncestorGeneric(root: TreeNode?, p: Int, q: Int): TreeNode? {
    // 1. Base Case 1: If the root is null, return null.
    if (root == null) {
        return null
    }

    // 2. Base Case 2: If the current node is p or q,
    // it must be the LCA for anything below it, or one of the nodes itself.
    if (root.value == p || root.value == q) {
        return root
    }

    // 3. Recursive Step: Search the left and right subtrees.
    val leftLCA = lowestCommonAncestorGeneric(root.left, p, q)
    val rightLCA = lowestCommonAncestorGeneric(root.right, p, q)

    // 4. Evaluate Results

    // Case A: Both results are non-null.
    // This means we found one target (p or q) in the left subtree
    // and the other in the right subtree. The current node MUST be the LCA.
    if (leftLCA != null && rightLCA != null) {
        return root
    }

    // Case B: Only the left result is non-null.
    // This means both p and q were found in the left side, or one was found
    // and the other wasn't. In either case, the result from the left side is the LCA.
    if (leftLCA != null) {
        return leftLCA
    }

    // Case C: Only the right result is non-null (or both are null, which returns null).
    // The LCA must be on the right side.
    return rightLCA
}

fun main() {
    // Example Tree: (Same as BSTPredecessor.kt for consistency)
    //      50
    //     /  \
    //    30  70
    //   / \  / \
    //  20 40 60 80


    val node20 = TreeNode(20)
    val node40 = TreeNode(40)
    val node60 = TreeNode(60)
    val node80 = TreeNode(80)

    val node30 = TreeNode(30,node20, node40 )
    val node70 = TreeNode(70, node60, node80 )

    val root = TreeNode(50, node30, node70)

    println("--- Lowest Common Ancestor (LCA) Tests ---")

    // Test 1: Split at Root (LCA of 20 and 80 is 50)
    var p1 = 20
    var q1 = 80
    var lca1 = lowestCommonAncestor(root, p1, q1)
    println("LCA of $p1 and $q1 is: ${lca1?.value}")

    // Test 2: LCA is one of the nodes (LCA of 20 and 40 is 30)
    var p2 = 20
    var q2 = 40
    var lca2 = lowestCommonAncestor(root, p2, q2)
    println("LCA of $p2 and $q2 is: ${lca2?.value}")

    // Test 3: One node is an ancestor of the other (LCA of 30 and 20 is 30)
    var p4 = 30
    var q4 = 20
    var lca4 = lowestCommonAncestor(root, p4, q4)
    println("LCA of $p4 and $q4 is: ${lca4?.value}")
}