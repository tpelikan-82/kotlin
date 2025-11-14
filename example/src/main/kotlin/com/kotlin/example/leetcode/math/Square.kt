package com.kotlin.example.leetcode.math

class Square {
}

fun mySqrt(x: Int): Int {
    // Edge case: Square root of 0 or 1 is the number itself.
    if (x <= 1) {
        return x
    }

    // Binary Search setup
    var low = 1
    var high = x
    var ans = 1 // Store the potential result

    // The search continues until the low pointer crosses the high pointer
    while (low <= high) {
        // Calculate the midpoint. Using the subtraction method prevents potential
        // integer overflow if low + high were to exceed Int.MAX_VALUE.
        val mid = low + (high - low) / 2

        // CRITICAL STEP: Use Long for the square calculation to prevent
        // 32-bit integer overflow, as (mid * mid) could easily exceed Int.MAX_VALUE
        // when x is large (up to 2,147,483,647).
        val square: Long = mid.toLong() * mid.toLong()

        // Comparison against the input x (also cast to Long for consistency)
        if (square == x.toLong()) {
            // Found the exact square root
            return mid
        } else if (square < x.toLong()) {
            // Mid is too small. We store mid as a potential answer because it's the
            // largest integer whose square is less than x (i.e., the floor).
            ans = mid
            low = mid + 1 // Search the right half for a larger root
        } else { // square > x
            // Mid is too large.
            high = mid - 1 // Search the left half
        }
    }

    // When the loop finishes, 'ans' holds the largest integer whose square is <= x.
    return ans
}

fun main(args: Array<String>) {


}