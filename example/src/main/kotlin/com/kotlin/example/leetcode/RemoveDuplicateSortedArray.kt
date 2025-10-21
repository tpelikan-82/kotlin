package com.kotlin.example.leetcode



class RemoveDuplicateSortedArray {


}

fun removeDuplicates(nums: IntArray): Int {

    var k = 1


    for (i in 1 .. nums.size - 1) {
        if (nums[i] != nums[i - 1]) {
            nums[k] = nums[i]
            k++
        }
    }
    return k
}

fun removeDuplicatesMoreThanTwo(nums: IntArray): Int {

    var k = 1
    var second =false

    for (i in 1 .. nums.size - 1) {
        if (nums[i] != nums[i - 1]) {
            nums[k] = nums[i]
            k++
            second = false
        } else if (!second) {
          nums[k] = nums[i]
          k++
          second = true
        }
    }
    return k
}

fun main() {
    val nums = intArrayOf(1,1,1,2,2,3)

//    println(removeDuplicates(nums))

    println(removeDuplicatesMoreThanTwo(nums))

}