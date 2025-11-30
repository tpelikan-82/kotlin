package com.kotlin.example.leetcode.array

class LengthLastWord {
}

fun lengthOfLastWord(s: String): Int {

    var length = 0
    var isWord = false;

    for (i in s.length-1 downTo 0) {
        if (s[i] != ' ') { isWord = true  }
        if (s[i] == ' ' && isWord) { return length }
        if (isWord) {
            length++
        }
    }

    return length
}

fun main(args: Array<String>) {

    lengthOfLastWord("Hello World")

}