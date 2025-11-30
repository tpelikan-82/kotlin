package com.kotlin.example.leetcode.map

class HappyNumber {
}


fun calculateSquarePlus(digits: List<Int>) :Int  = digits.sumOf { it * it }


fun isHappy(n: Int): Boolean {

    var result: Int = n;
    val visitedNumbers: MutableSet<Int> = mutableSetOf();


    while (result != 1){
        result = result.toString().map { it.digitToInt() }.sumOf { it * it }
        if (visitedNumbers.contains(result)) { return false; }
        visitedNumbers.add(result)
    }

    return true;

}

fun main(args: Array<String>) {

    println(isHappy(19))
    println(isHappy(2))
}