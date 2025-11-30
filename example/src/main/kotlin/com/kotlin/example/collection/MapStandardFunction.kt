package com.kotlin.example.collection

class MapStandardFunction {
}


fun testAll(input : Map.Entry<String, Int>  ) : Boolean {
    return input.value > 0
}



fun main(args: Array<String>) {

    val map = mutableMapOf<String, Int>()

    map.put("one", 1)
    map.put("two", 2)
    map.put("three", 3)
    map.put("four", 4)
    map.put("five", 5)
    map.put("six", 6)
    map.put("seven", 7)
    map.put("eight", 8)
    map.put("nine", 9)
    map.put("ten", 10)


//    (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean


    if (map.all { it.value > 0 }) println("All value are greater than zero")
    if (map.all(::testAll)) println("All value are less than zero")


    if (map.any { it.value > 8 }) println("Some value is greater than 8")




}