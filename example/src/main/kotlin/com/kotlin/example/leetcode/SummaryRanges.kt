package com.kotlin.example.leetcode

class SummaryRanges {
}


fun summaryRanges(nums: IntArray): List<String> {

    var firstValue = nums[0]
    val result: MutableList<String> = mutableListOf();


    if (nums.isEmpty()) return result


    for (i in 0 until nums.size) {

        if (i + 1 >= nums.size || nums[i + 1] != nums[i] + 1) {
            if  (firstValue == nums[i]) {
                result.add("${nums[i]}");
            } else {
                result.add("$firstValue->${nums[i]}")
            }
        }

        if ( i + 1 < nums.size && nums[i + 1] != nums[i] + 1) {
            firstValue = nums[i + 1]
        }



    }

    return result

}

fun main() {

    println(summaryRanges(intArrayOf(0,2,3,4,6,8,9)))

}