package com.kotlin.example.collection

class GroupByExample {
}

fun <K, V> inverseMap(inputMap: Map<K, V>): Map<V, List<K>> {
    // 1. Group the map entries based on the entry's 'value' (V).
    // Result type: Map<V, List<Map.Entry<K, V>>>
    val groupedByValue = inputMap.entries.groupBy { it.value }
    println(groupedByValue)
    // 2. Transform the values of the grouped map.
    // We map the List<Entry> to a List<K> (the original keys).
    // Result type: Map<V, List<K>>


   val result =  groupedByValue.mapValues { (_, entries) ->
       entries.map { it.key } }

    return groupedByValue.mapValues { (_, entries) ->
        entries.map { it.key }
    }
}

fun main() {
    // The example object provided by the user
    val originalMap = mapOf("a" to "lower", "b" to "lower", "C" to "upper")

    println("Original Map:")
    println(originalMap)

    val invertedMap = inverseMap(originalMap)

    println("\nInverted Map:")
    println(invertedMap)

    // Expected Output: {lower=[a, b], upper=[C]}
}