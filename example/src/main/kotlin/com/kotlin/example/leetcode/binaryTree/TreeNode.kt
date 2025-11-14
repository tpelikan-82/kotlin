package com.kotlin.example.leetcode.binaryTree

class TreeNode(val value: Int,  val left: TreeNode? = null,
               val right: TreeNode? = null)  {
    override fun toString(): String {
        return value.toString()
    }
}