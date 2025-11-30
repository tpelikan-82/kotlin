package com.kotlin.example.leetcode.binaryTree

class TreeNode(val value: Int,  var left: TreeNode? = null,
               var right: TreeNode? = null)  {
    override fun toString(): String {
        return value.toString()
    }
}