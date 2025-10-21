package com.kotlin.example.leetcode

class RemoveElement {
}

fun removeElement(nums: IntArray, `val`: Int): Int {
//    val result = nums.filter { it != `val` }
//    for (i in 0..nums.lastIndex) {
//        if(result.size > i) {
//            nums[i] = result[i]
//        } else {
//            nums[i] = `val` + 1;
//        }
//
//    }

    var size = nums.size
    var j = nums.lastIndex

    for (i in 0..j) {
        if (`val` == nums[i]) {
            size--
            while  (i < j) {
                if (nums[j] != `val` ) {
                    nums[i] = nums[j]
                    j--
                    break
                }
                j--
            }
        }
    }

    return size;
}

fun removeElementsAI(nums: IntArray, `val`: Int): Int {
    var k = 0

    // 'i' is the "read" pointer. It iterates through the entire array.
    for (i in nums.indices) {
        // If the current element is NOT the value to remove, we keep it.
        if (nums[i] != `val`) {
            // Write the value from the read pointer (i) to the write pointer (k)
            nums[k] = nums[i]

            // Advance the write pointer to the next position
            k++
        }
        }
    return k
}

fun main() {

    val input = intArrayOf(1,2,3,1)
    println(removeElement(input, 1))
    println(input.toList())
}