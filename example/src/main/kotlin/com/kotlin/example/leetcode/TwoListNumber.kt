package com.kotlin.example.leetcode

class ListNode(var `val`: Int) {
         var next: ListNode? = null
    override fun toString(): String {

        var result = "$`val`"
        var node = next;

        while (node != null) {
            result += node.`val`
            node = node.next
        }

        return result
    }
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

    if (l1 == null && l2 == null) {
        return null
    }

    val result = ListNode(0)
    var higherLevel = 0

    result.`val` = (l1?.`val` ?: 0) + (l2?.`val` ?: 0)
    if (result.`val` > 9) {
        result.`val` -= 10
        higherLevel++
    }

    result.next = recursion(l1?.next, l2?.next, higherLevel)

    return result
}

fun recursion(l1: ListNode?, l2: ListNode?, higherLevel: Int): ListNode? {

    if (l1 == null && l2 == null) {
        if (higherLevel == 1) {
            return  ListNode(1)
        } else {
            return null
        }
    }

    val result = ListNode(0)
    var increase = 0

    result.`val` = (l1?.`val` ?: 0) + (l2?.`val` ?: 0) + higherLevel
    if (result.`val` > 9) {
        result.`val` -= 10
        increase++
    }

    result.next = recursion(l1?.next, l2?.next, increase)

    return result

}


fun main() {
    val listNode1 = ListNode(2)
        listNode1.next = ListNode(4)
    listNode1.next!!.next = ListNode(3)

    val listNode2 = ListNode(5)
    listNode2.next = ListNode(6)
    listNode2.next!!.next = ListNode(4)


    val result = addTwoNumbers(listNode1, listNode2)

    println(result)

}