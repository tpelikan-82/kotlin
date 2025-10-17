package com.kotlin.example.leetcode

class MergeSortedArray {
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    val input1 = nums1.take(m)
    val input2 = nums2.take(n)

    var i = 0
    var j = 0

    for (k in 0 until m + n) {
        if (i >= m) {
            nums1[k] = input2[j]
            j++
        } else if (j >= n) {
            nums1[k] = input1[i]
            i++
        } else if (input1[i] > input2[j]) {
            nums1[k] = input2[j]
            j++
        } else {
            nums1[k] = input1[i]
            i++
        }
    }



    println(nums1.contentToString( ))
}

fun mergeAISolution(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {

    var i = m - 1   // Pointer for the last element of the actual numbers in nums1
    var j = n - 1   // Pointer for the last element of nums2
    var k = m + n - 1 // Pointer for the last available position in nums1

    // 1. Merge from the back to the front
    // We stop when we've processed all elements in nums2 (j < 0)
    while (j >= 0) {
        // Compare the elements at i and j.
        // Important: Check if i is still valid (i >= 0) AND if nums1[i] is greater than nums2[j]
        if (i >= 0 && nums1[i] > nums2[j]) {
            // nums1[i] is larger, so it goes into the final position k
            nums1[k] = nums1[i]
            i-- // Move nums1 pointer left
        } else {
            // nums2[j] is larger (or i < 0, meaning nums1 is exhausted), so it goes into position k
            nums1[k] = nums2[j]
            j-- // Move nums2 pointer left
        }
        k-- // Move insertion pointer left
    }

}

fun main() {
  val nums1 = intArrayOf(1, 2, 3, 0,0,0)
  val nums2: IntArray = intArrayOf(2, 5, 6)

    merge(nums1, 3, nums2, 3)

    val nums3 = intArrayOf(0)
    val nums4: IntArray = intArrayOf(1)

    merge(nums3, 0, nums4, 1)

}