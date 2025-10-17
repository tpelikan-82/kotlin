package com.kotlin.example.leetcode

class TwoSum {

}

fun twoSum(nums: IntArray, target: Int): IntArray {


    val numMap = mutableMapOf<Int, Int>()

    // 2. Iterate through the array only once (O(n))
    for (i in nums.indices) {
        val currentValue = nums[i]
        val complement = target - currentValue

        // 3. Check if the complement is already in the map
        // This is an O(1) lookup
        if (numMap.containsKey(complement)) {
            // Found the solution!
            // The complement's index is stored in the map.
            val complementIndex = numMap.getValue(complement)
            return intArrayOf(complementIndex, i)
        }

        // 4. If the complement wasn't found, add the current value and its index to the map
        numMap[currentValue] = i
    }

    // As per the problem constraints, we usually assume a solution exists.
    // If not, you might throw an exception or return an empty array.
    throw IllegalArgumentException("No two sum solution")


//    for (i in nums.indices) {
//        for (j in i + 1 until nums.size) {
//            if ((nums[j] + nums[i]) == target) {
//                return intArrayOf(i, j)
//            }
//        }
//    }
    return intArrayOf()
}

fun IntArray.toMyString() : String {
    var result = ""
    for (i in this.indices) {
        result += this[i]
    }
    return result
}

fun main(args: Array<String>) {
    val nums = twoSum(nums = intArrayOf(-3,4,3,90), target = 0)
    println(nums.toMyString())
}