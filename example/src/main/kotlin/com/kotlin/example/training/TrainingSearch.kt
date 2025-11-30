package com.kotlin.example.training

class TrainingSearch {
}

fun insertSort(input: IntArray): IntArray {

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

    return input;
}

fun binarySearch(input: IntArray, searchValue: Int): Int {

    var lowIndex = 0
    var highIndex = input.size - 1

    while (lowIndex <= highIndex) {

        val mid = lowIndex + (highIndex - lowIndex) / 2

        if (input[mid] > searchValue) {
            highIndex = mid - 1
        } else if (input[mid] < searchValue) {
            lowIndex = mid + 1
        } else {
            return mid;
        }

    }

    return -1;
}

fun lambdaWithReeceverTest(someValue: Int, blockaa: ArrayList<Int>.(Int) -> Unit) : ArrayList<Int> {
    val arrayList = ArrayList<Int>();
    arrayList.blockaa(someValue);
    return arrayList;
}

fun main(args: Array<String>) {

   val sortedArray = insertSort(intArrayOf(4,2,3,8,1))

    println(binarySearch(sortedArray, 8))

    val resut  = lambdaWithReeceverTest(10) {
            numberOfElements ->
        // 'numberOfElements' is the 'count' value (7) passed in
        // 'this' is the ArrayList<Int>
        for (i in 1..numberOfElements) {
            add(i)
        }
    }

    println(resut)



}