package com.kotlin.example.leetcode.stack

import java.util.Stack

class ValidParantheses {

    private fun isExpectedCharacter(c: Stack<Char>, expectedCharacter: Char): Boolean {
        if (c.isEmpty()) { return false }

        return c.pop() == expectedCharacter;
    }

    fun isValid(s: String): Boolean {

        val stack = Stack<Char>();

        for (c in s) {

            var isCorrect = true;

            when (c) {
                '}' -> { isCorrect =isExpectedCharacter(stack, '{')  }
                ']' -> { isCorrect =isExpectedCharacter(stack, '[')  }
                ')' -> { isCorrect =isExpectedCharacter(stack, '(')  }
                else -> stack.push(c);

            }

            if (!isCorrect) {
                return false
            }
        }

        if (stack.isNotEmpty()) { return false }

        return true
    }

}


fun main() {

    println(ValidParantheses().isValid(s = "[(()){}[]]"))

    println(ValidParantheses().isValid(s = "{{{{{{[[[[]]]]}}}}"))

}