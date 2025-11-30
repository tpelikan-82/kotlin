package com.kotlin.example.algoritm

class SimpleHashingAlgorithm {
}


fun main() {
    val hashTableSize = 10 // The fixed size of our conceptual array/hash table
    println("--- Simple Hashing Demonstration ---")
    println("Target Hash Table Size: $hashTableSize\n")

    val keysToHash = listOf("apple", "banana", "cherry", "orange", "zebra")

    for (key in keysToHash) {
        val hashValue = simpleHash(key, hashTableSize)
        println("Input: \"$key\"")
        println("  -> ASCII Sum: ${getAsciiSum(key)}")
        println("  -> Hash Index: $hashValue (Index must be < $hashTableSize)")
        println("-" * 30)
    }

    // Example of a collision: two different inputs producing the same hash value
    val collisionKey1 = "cat" // 99 + 97 + 116 = 312
    val collisionKey2 = "act" // 97 + 99 + 116 = 312
    val hash1 = simpleHash(collisionKey1, hashTableSize)
    val hash2 = simpleHash(collisionKey2, hashTableSize)

    println("Collision Example:")
    println("Hash for \"$collisionKey1\": $hash1")
    println("Hash for \"$collisionKey2\": $hash2")
    println("Note: Both keys map to the same index, demonstrating a 'collision'.")
}

/**
 * Calculates the ASCII sum of the input string.
 */
fun getAsciiSum(input: String): Int {
    return input.sumOf { it.code } // 'it.code' gives the ASCII/Unicode value of the character
}

/**
 * A simple, non-cryptographic hashing algorithm.
 *
 * This function converts an arbitrarily long string into a fixed index (hash value)
 * within the specified table size.
 *
 * @param input The string to hash.
 * @param tableSize The size of the array/hash map (e.g., 10).
 * @return An integer hash value (index) between 0 and tableSize - 1.
 */
fun simpleHash(input: String, tableSize: Int): Int {
    // 1. Accumulate a large numerical value from the input.
    // We use the sum of the ASCII values of all characters.
    val largeSum = getAsciiSum(input)

    // 2. Map the large sum to a fixed index using the Modulo Operator (%).
    // This is the CRITICAL step that guarantees the output is within the range [0, tableSize - 1].
    //
    val hashIndex = largeSum % tableSize

    // 3. Return the fixed-size index.
    return hashIndex
}

// Simple extension function for demonstration formatting
private operator fun String.times(n: Int): String = repeat(n)