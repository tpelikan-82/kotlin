package com.kotlin.example.leetcode.math

class PlusOneLargeInteger {
}

fun plusOneAI(digits: IntArray): IntArray {
    // 1. Iterate backwards from the last digit.
    for (i in digits.size - 1 downTo 0) {
        // If the current digit is less than 9, we can increment it and stop,
        // as no carry is needed.
        if (digits[i] < 9) {
            digits[i]++
            return digits
        }

        // If the digit is 9, set it to 0 and let the loop continue
        // to the next digit to process the carry.
        digits[i] = 0
    }

    // 2. Handle the "all nines" overflow case (e.g., [9, 9, 9] -> [1, 0, 0, 0])
    // If the loop completes, it means the entire array was processed, and a final
    // carry is required, increasing the array size by one.

    // Create a new array one element larger than the original.
    val result = IntArray(digits.size + 1)

    // Set the first digit to 1 (the final carry).
    result[0] = 1
    // The rest of the elements default to 0, which is correct.

    return result
}

fun plusOne(digits: IntArray): IntArray {

    var plusOne: Int = 1;
    var pointer: Int = digits.size - 1;

    var result: IntArray = digits;

    while (plusOne > 0) {

        if (pointer < 0) {
            result= intArrayOf(1) + result
            plusOne--
            continue;
        }

        if (result[pointer] + 1 <= 9) {
            result[pointer]  += 1
            plusOne = 0
        } else {
            result[pointer] = 0
        }
        pointer--
    }


    return result
}

fun IntArray.printValues() {
    this.forEach { print("$it ") }
}

fun main(args: Array<String>) {

    val input : IntArray = intArrayOf(1,2,9);
    plusOne(input);
    input.printValues()

}