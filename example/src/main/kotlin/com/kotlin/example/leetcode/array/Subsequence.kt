package com.kotlin.example.leetcode.array

import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType

class Subsequence {

    fun isSubsequence(s: String, t: String): Boolean {

        if (s.isEmpty()) return true

        var pointerS = 0

        t.forEach {
            if (s[pointerS] == it) {
                pointerS++
                if (pointerS == s.length) {
                    return true
                }
            }


        }

        return false

    }

}

fun main (args: Array<String>) {

    val s = Subsequence()

    println(s.isSubsequence("abc", "ssadebfsdsdvcca"))

}