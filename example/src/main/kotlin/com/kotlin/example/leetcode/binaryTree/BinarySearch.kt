package com.kotlin.example.leetcode.binaryTree

class BinarySearch {
}

/**
 * time complexity O(log n)
 */

fun searchInsert(nums: IntArray, target: Int): Int {

    var low = 0
    var high = nums.size - 1

// Standard binary search loop: continue as long as the search space is valid.
    while (low <= high) {
        // Calculate the middle index safely to prevent potential integer overflow
        val mid = low + (high - low) / 2

        when {
            // Case 1: Target found. Return the index.
            nums[mid] == target -> return mid

            // Case 2: Mid value is less than the target.
            // Target must be in the right half (or is the insertion point after mid).
            nums[mid] < target -> low = mid + 1

            // Case 3: Mid value is greater than the target.
            // Target must be in the left half (or is the insertion point at or before mid).
            nums[mid] > target -> high = mid - 1
        }
    }

// When the loop terminates, 'low' points to the correct insertion index.
// The search space has been narrowed down to the point where low > high,
// and 'low' is the first index greater than the target.
    return low

}