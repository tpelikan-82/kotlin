package com.kotlin.example.leetcode.binaryTree

import java.util.LinkedList
import java.util.Queue

class InvertBinaryTree {
}

fun invertTree(root: TreeNode?): TreeNode? {

    val queue: Queue<TreeNode> = LinkedList<TreeNode>()

    queue.offer(root)

    while (queue.isNotEmpty()) {
        queue.poll()?.let {
            val tmp = it.left
            it.left = it.right
            it.right = tmp
            queue.add(it.left)
            queue.add(it.right)
        }
    }

    return root

}