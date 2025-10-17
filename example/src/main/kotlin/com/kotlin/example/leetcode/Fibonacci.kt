package com.kotlin.example.leetcode

//O 2^N
fun fib(n: Int): Int {

    if (n == 0) return 0

    if (n == 1) return 1

    return fib(n-1) + fib(n -2)
}

// O(N)
fun fibFast(n: Int): Int {
    if (n <= 1) return n

    var a = 0
    var b = 1

    // We calculate fib(2) up to fib(n)
    for (i in 2..n) {
        // Calculate the next number: a + b
        val next = a + b

        // Move the window forward
        a = b
        b = next
    }

    // b holds the result of the last calculated number, fib(n)
    return b
}