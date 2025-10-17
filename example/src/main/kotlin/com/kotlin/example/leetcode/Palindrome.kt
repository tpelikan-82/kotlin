package com.kotlin.example.leetcode

class Palindrome {
}

fun cleanString(input: String): String {
    return input
        .replace(Regex("[^A-Za-z0-9]"), "")
        .lowercase()
}

//much faster then regex
fun String.cleanStringManualFilter(): String {
    // This creates a new string by filtering characters and converting to lowercase.
    return this.filter { it.isLetterOrDigit() }.lowercase()
}


fun isPalindrome(s: String): Boolean {

    var i = 0
    val chars = s.cleanStringManualFilter().toCharArray()


    while ( i < chars.size/2 ) {
        if (chars[i] !=  chars[ chars.size -1 - i]) { return false }
        i++
    }
    return true
}

//from AI
fun isPalindromeOptimized(s: String): Boolean {
    // Pointers start at the extremities of the original string
    var left = 0
    var right = s.length - 1

    while (left < right) {
        val leftChar = s[left]
        val rightChar = s[right]

        // 1. Skip non-alphanumeric characters from the left
        if (!leftChar.isLetterOrDigit()) {
            left++
            continue
        }

        // 2. Skip non-alphanumeric characters from the right
        if (!rightChar.isLetterOrDigit()) {
            right--
            continue
        }

        // 3. Compare the characters (case-insensitive)
        if (leftChar.lowercaseChar() != rightChar.lowercaseChar()) {
            return false // Mismatch found
        }

        // 4. Move both pointers inward
        left++
        right--
    }

    return true // All valid characters matched
}

fun longestPalindrome(s: String): String {

    if (s.length < 2) return s

    var start = 0
    var maxLength = 1

    /**
     * Helper function to expand outward from a center and update the result
     */
    fun expand(left: Int, right: Int) {
        var l = left
        var r = right

        // Expand as long as the indices are valid and characters match
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            // Check if the current length is the new maximum
            val currentLength = r - l + 1
            if (currentLength > maxLength) {
                maxLength = currentLength
                start = l // Update the starting index of the longest palindrome
            }
            l--
            r++
        }
    }

    // Iterate through every character as a potential center
    for (i in s.indices) {
        // Case 1: Odd-length palindrome (center is s[i])
        expand(i, i)

        // Case 2: Even-length palindrome (center is between s[i] and s[i+1])
        expand(i, i + 1)
    }

    // Return the substring based on the stored start index and max length
    return s.substring(start, start + maxLength)

}

fun main() {

    val s = "A man, a plan, a canal: Panama";

    println(isPalindrome(s))


    println(longestPalindrome(s.cleanStringManualFilter()))

}