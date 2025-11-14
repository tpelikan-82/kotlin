package com.kotlin.example.leetcode.math

class AddBinary {
}

/**
 *
 * Time Complexity: O(max(N, M)), where N and M are the lengths of strings a and b.
 * Space Complexity: O(max(N, M)). This is the space required to store the resulting binary string, which will be at most max(N,M)+1 characters long.
 */


fun addBinary(a: String, b: String): String {

    val result = StringBuilder()

    // Pointers for iterating through strings a and b from the end.
    var i = a.length - 1
    var j = b.length - 1

    // Carry variable, initialized to 0.
    var carry = 0

    // Loop continues as long as we have digits in either string OR a carry remains.
    while (i >= 0 || j >= 0 || carry == 1) {

        // Start the sum with the current carry-over value.
        var sum = carry

        // 1. Add digit from string 'a' if pointer is valid.
        // We subtract '0' (the character) to get the integer value (0 or 1).
        if (i >= 0) {
            sum += a[i].code - '0'.code
            i--
        }

        // 2. Add digit from string 'b' if pointer is valid.
        if (j >= 0) {
            sum += b[j].code - '0'.code
            j--
        }

        // 3. Determine the new carry and the current result digit.
        // Carry is 1 if sum is 2 or 3 (sum / 2).
        carry = sum / 2

        // The current digit is 0 or 1 (sum % 2). Append it to the result.
        result.append(sum % 2)
    }

    // The result was built in reverse order, so we reverse it before converting to String.
    return result.reverse().toString()

}


fun main() {

    addBinary("101", "101")

}