package com.kotlin.example.leetcode.LinkedList

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class CycleLinkedList {
}

fun hasCycle(head: ListNode?): Boolean {
    val visited : MutableSet<ListNode>? = mutableSetOf() ;

    if (head?.next == null) { return false }

    var node = head.next;

    while (node != null) {

        if (visited?.contains(node) ?: false) { return true}

        visited?.add(node);
        node = node.next;
    }

    return false
}


fun main() {
    var head = ListNode(3);
    var second = ListNode(2);
    var third = ListNode(0);
    var tail = ListNode(- 4);

    head.next = second
    second.next = third
    third.next = tail;
    tail.next = null;

    print(hasCycle(head));

}