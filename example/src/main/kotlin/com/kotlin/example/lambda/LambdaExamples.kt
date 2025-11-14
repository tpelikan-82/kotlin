package com.kotlin.example.lambda

class LambdaExamples {
}

fun sumFun(x: Int, y: Int): Int {
    return x + y
}

fun parameterFunction(predicate: (Int, Int) -> Int , x: Int, y: Int): Int {
    return predicate(x, y);
}

fun makeCounter(): () -> Int {
    var count = 0 // This variable is outside the lambda

    // The lambda 'captures' the 'count' variable.
    return {
        count++ // It can modify 'count' even after makeCounter returns
        count
    }
}



fun main(args: Array<String>) {

    val sum1 = { x: Int, y: Int -> x +y }
    val sum: (Int, Int) -> Int = { x, y -> x + y }


    println(sum(1, 2))
    println(sum1(1, 2))
    println(sumFun(1, 2))

    println(parameterFunction(sum, 1, 2))
    println(parameterFunction (::sumFun, 1, 2))


    val counterA = makeCounter()
    println(counterA()) // Output: 1
    println(counterA()) // Output: 2


}