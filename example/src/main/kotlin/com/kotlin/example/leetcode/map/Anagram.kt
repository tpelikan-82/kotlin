package com.kotlin.example.leetcode.map

class Anagram {
}

fun isAnagram(s: String, t: String): Boolean {

    if (s.length < t.length) return false

    val letters: MutableMap<Char, Int> = mutableMapOf()

    for (c in s) {
        letters[c]  = letters.getOrDefault(c, 0) + 1
    }

    for (c in t) {
        letters[c]  = letters.getOrDefault(c, 0) - 1
        letters[c]?.let { if (it < 0) return false }
    }

    return true
}


fun main() {
    println(isAnagram("anagram", "nagaram"))
}
