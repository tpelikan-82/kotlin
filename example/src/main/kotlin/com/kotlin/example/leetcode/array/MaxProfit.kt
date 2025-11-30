package com.kotlin.example.leetcode.array

import kotlin.math.max


fun maxProfitAi(prices: IntArray): Int {
    // Check for trivial cases
    if (prices.size < 2) {
        return 0
    }

    // Initialize minPrice to the price on the first day
    // The problem guarantees we can buy on day i and sell on a day j > i
    var minPrice = prices[0]

    // Initialize maxProfit to 0 (as per the requirement if no profit is possible)
    var maxProfit = 0

    // Start iteration from the second day (index 1)
    for (i in 1 until prices.size) {
        val currentPrice = prices[i]

        // 1. Calculate the potential profit if we sell today
        // Profit = currentPrice - minPrice seen so far
        val currentProfit = currentPrice - minPrice

        // 2. Update the overall maximum profit
        // Keep the highest profit found across all days
        if (currentProfit > maxProfit) {
            maxProfit = currentProfit
        }

        // 3. Update the minimum buying price
        // If the current price is lower, it becomes the new best buying point for future days
        if (currentPrice < minPrice) {
            minPrice = currentPrice
        }

        // The two updates (steps 2 & 3) can be combined using standard library functions:
        // maxProfit = maxOf(maxProfit, currentPrice - minPrice)
        // minPrice = minOf(minPrice, currentPrice)
    }

    return maxProfit
}


fun maxProfit(prices: IntArray): Int {

    var maxProfit = 0

    var minPrice = prices[0]

    for (i in 1 until prices.size) {


        if (prices[i] < minPrice) {
            minPrice = prices[i]
        }

        maxProfit = max(maxProfit, prices[i] - minPrice)


    }


    return maxProfit;

}


fun main() {

    val input: IntArray = intArrayOf(7,1,5,3,6,4)
    println(maxProfit(input))
}