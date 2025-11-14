package com.kotlin.example.leetcode.math

class FindSingleNumber {
}

/**
 *
 * Feature	Your Hash Set Solution	XOR Bitwise Solution (Optimal)
 * Runtime	O(n) (Linear)	O(n) (Linear)
 * Space	O(n) (Linear)	O(1) (Constant)
 *
 */
fun singleNumberAI(nums: IntArray): Int {
    // Initialize the result accumulator to 0.
    // XORing any number with 0 returns the number itself (Identity Property).
    var uniqueNumber = 0

    // Iterate through every number in the array.
    for (num in nums) {
        // Apply the XOR operation.
        // If 'num' has been seen before, it will cancel out its previous XOR.
        // If 'num' is the single unique number, it will remain at the end.
        uniqueNumber = uniqueNumber xor num
    }

    // The final result is the single number.
    return uniqueNumber
}


fun singleNumber(nums: IntArray): Int {

    val tempResult = mutableSetOf<Int>()

    for (i in 0..nums.lastIndex) {
        if (tempResult.contains(nums[i])) {
            tempResult.remove(nums[i])
        } else {
            tempResult.add(nums[i])
        }
    }

    return tempResult.first()

}

fun main(args: Array<String>) {

    val input: IntArray = intArrayOf(4,1,2,1,2)

    println(singleNumber(input))
    println(singleNumberAI(input))

}