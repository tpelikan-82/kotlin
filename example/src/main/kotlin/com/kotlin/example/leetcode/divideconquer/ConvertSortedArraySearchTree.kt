package com.kotlin.example.leetcode.divideconquer

import com.kotlin.example.leetcode.binaryTree.TreeNode

class ConvertSortedArraySearchTree {
}

fun sortedArrayToBST(nums: IntArray): TreeNode? {

    var low = 0
    var high = nums.size - 1

    if (low > high) { return null }

    var mid = (high + low) / 2

    var leftInterval = low..mid -1
    var rightInterval = mid+1..high

    if (nums.size < 3) {
        if (nums[mid] > nums[high]) {
            leftInterval =  mid+1..high
            rightInterval = low..mid -1
        }
    }

    val root : TreeNode =
        TreeNode(nums[mid], sortedArrayToBST(nums.sliceArray(leftInterval)),
            sortedArrayToBST(nums.sliceArray(rightInterval)))


    return root;
}

fun sortedArrayToBSTAI(nums: IntArray): TreeNode? {
    // The main function simply calls the recursive helper with the full array indices.
    return convertToBST(nums, 0, nums.size - 1)
}

/**
 * Recursive helper function that uses indices (low, high) to define the current sub-array.
 * This avoids inefficient array slicing/copying.
 */
private fun convertToBST(nums: IntArray, low: Int, high: Int): TreeNode? {

    if (low > high) {
        return null
    }

    val mid = low + (high - low) / 2

    val root = TreeNode(nums[mid], convertToBST(nums, low, mid - 1),  convertToBST(nums, mid + 1, high))

    return root
}

fun main(args: Array<String>) {

    val inputArray = intArrayOf(-10,-3,0,5,9)
    val result = sortedArrayToBST(inputArray)


    val result2 = sortedArrayToBSTAI(inputArray)

    println(result.toString())
    println( result?.left.toString())
    println(result?.right.toString())

}