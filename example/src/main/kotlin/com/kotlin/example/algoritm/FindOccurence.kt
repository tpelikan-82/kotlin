package com.kotlin.example.algoritm

import com.sun.tools.javac.Main

class FindOccurence {
}

data class Segment(val startTime: Int, val endTime: Int)

fun segmentsCover(segments: List<Segment>) : List<Int> {
    val result = ArrayList<Int>();
    for (segment in segments.sortedBy { it.endTime }) {
        if (result.isEmpty() || result.last() < segment.startTime) {
            result.add(segment.endTime)
        }
    }
    return result
}


fun main(args: Array<String>) {

    val input  = listOf<Char>('0','1','0','1')

    var i = 0;
    var r = input.size

    while (i + 1 < r) {
        val m = (r + i) /2
        if (input[m] == '0') {
            i = m
        } else {
            r = m
        }
    }

    println(i)

    val visitTime = listOf<Segment>(
        Segment(10, 20),
        Segment(14,16),
        Segment(5,13))

    println(segmentsCover(visitTime))


}