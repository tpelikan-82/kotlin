package com.kotlin.example.leetcode.math

class PalindromNumber {
}

fun isPalindrome(x: Int): Boolean {

    var digits: List<Int>

    try {
        digits = x.toString().map { char ->
            char.digitToInt()
        }
    } catch(e: IllegalArgumentException) {
        return false
    }

    var beginPointer = 0;
    var endPointer = digits.size - 1;

    while (beginPointer <= endPointer) {
        if (digits[beginPointer] != digits[endPointer]) { return false }
        beginPointer++
        endPointer--
    }

    return true

}

fun main() {
    val palindromNumber: Int = 12321

    var digits: List<Int> = mutableListOf()

    try {
        digits = palindromNumber.toString().map { char ->
        char.digitToInt()
    }
    } catch(e: IllegalArgumentException) {

    }

    var midlle: Int



    if (digits.size % 2 == 0) {
        midlle = digits.size / 2;
    } else {
        midlle = digits.size / 2 + 1;
    }

    var beginPointer = 0;
    var endPointer = digits.size - 1;

    while (beginPointer <= endPointer) {

        if (digits[beginPointer] != palindromNumber) { }
        beginPointer++
        endPointer--

    }


    println(midlle)




}