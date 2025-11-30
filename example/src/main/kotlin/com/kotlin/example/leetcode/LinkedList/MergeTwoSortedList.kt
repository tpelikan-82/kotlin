package com.kotlin.example.leetcode.LinkedList

class MergeTwoSortedList {



}


fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {

    if (list1 == null) return  list2
    if (list2 == null) return  list1

    var currentNode1 = list1
    var currentNode2 = list2

    var firstNode: ListNode? = null;

    if (list1.`val` < list2.`val`)  {
        firstNode = ListNode(list1.`val`);
        currentNode1 = currentNode1.next
    } else  {
        firstNode =  ListNode(list2.`val`)
        currentNode2 = currentNode2.next
    }
    var processNode: ListNode? = firstNode

    while (currentNode1 != null && currentNode2 != null) {

        if (currentNode1.`val` < currentNode2.`val`) {
            processNode?.next =  ListNode(currentNode1.`val`)
            currentNode1 = currentNode1.next
        } else {
            processNode?.next =   ListNode(currentNode2.`val`)
            currentNode2 = currentNode2.next
        }
        processNode = processNode?.next

    }

    if (currentNode1 != null) {
        processNode?.next = currentNode1
    } else if (currentNode2 != null) {
        processNode?.next = currentNode2
    }


    return firstNode

}

fun main(args: Array<String>) {

    val list1 = ListNode(1)
    val list2 = ListNode(2)
    val list4 = ListNode(4)

    list1.next = list2
    list2.next = list4


    val listb1 = ListNode(1)
    val listb3 = ListNode(3)
    val listb4 = ListNode(4)

    listb1.next = listb3
    listb3.next = listb4

    val result = mergeTwoLists(list1, listb1)

}