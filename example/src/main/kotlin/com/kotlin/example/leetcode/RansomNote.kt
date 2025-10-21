package com.kotlin.example.leetcode

class RansomNote {
}

fun canConstruct(ransomNote: String, magazine: String): Boolean {

    val letters = mutableMapOf<Char, Int>()

    magazine.forEach {
        if (letters.containsKey(it)) {
            letters[it] = letters[it]!! + 1
        } else {
            letters.put(it, 1)
        }

    }

    ransomNote.forEach {
        if (letters.containsKey(it) &&
            letters[it]!! > 0) {
            letters[it] = letters[it]!! - 1
        } else {
            return false
        }
    }

    return true
}

fun main() {
    canConstruct("aaaa", "trtrwaadas")
}