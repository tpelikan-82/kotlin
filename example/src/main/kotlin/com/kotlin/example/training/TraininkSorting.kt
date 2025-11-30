package com.kotlin.example.training

class TraininkSorting {
}


data object Sigleton {
    var value: Int = 0
}

fun insertSort(input: Array<Int>) : Array<Int> {

    for (i in 1 until input.size) {

        val key = input[i]
        var j = i - 1

        // Move elements of arr[0..i-1], that are greater than the key,
        // to one position ahead of their current position.
        while (j >= 0 && input[j] > key) {
            input[j + 1] = input[j]
            j--
        }
        // Insert the key into its correct position.
        input[j + 1] = key
    }

    return input
}

fun Array<Int>.output() : String {

    val builder = StringBuilder()
    this.forEach { builder.append(it).append(", ") }
    return builder.toString()
}


fun main(args: Array<String>) {
    val input: Array<Int> = arrayOf(12, 32, 31, 14, 5, 69, 7, 58, 9, 1)

    val result = insertSort(input);
    println(result.output())

    Sigleton.value = 10;
    println(Sigleton.value);
    Sigleton.value = 15
    println(Sigleton.value);

}