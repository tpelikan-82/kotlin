package com.kotlin.example.leetcode.array

import kotlin.math.ceil

class MajorityElem {
}

/**
 *
 * Boyer-Moore Voting Algorithm.
 *requiring O(N) time and only O(1) extra space,
 */

fun majorityElemAI(nums: IntArray) : Int {

    var candidate = 0  // Stores the current majority candidate
    var count = 0      // Stores the running count for the candidate

    nums.forEach {
        if (count == 0) {
            // If count is 0, the current element becomes the new candidate
            candidate = it
            count = 1
        } else if (it == candidate) {
            // If the current element matches the candidate, increment count
            count += 1
        } else {
            // If the current element is different, decrement count
            count -= 1
        }
    }

        // Since the problem guarantees that the majority element always exists,
        // the 'candidate' variable at the end must be the majority element.
        return candidate

}


fun majorityElement(nums: IntArray): Int {




    val aaa = (nums.size / 2.0)
    val majorityCount = ceil(aaa).toInt()


    var mapResult: MutableMap<Int, Int> = mutableMapOf()

    for (i in nums.indices) {
        val elemMapValue = mapResult.get(nums[i])
        if (elemMapValue != null) {
            if (elemMapValue + 1 >= majorityCount) {
                return nums[i]
            }
            mapResult[nums[i]] =  elemMapValue + 1
        } else {
            mapResult[nums[i]] = 1
        }

    }
    return -1
}


fun main() {

    println(majorityElement(intArrayOf(1, 1, 2,2,2)))



}
