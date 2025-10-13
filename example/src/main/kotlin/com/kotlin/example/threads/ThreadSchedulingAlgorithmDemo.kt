package com.kotlin.example.threads

import java.util.LinkedList

class RoundRobinScheduler(val timeQuantum: Int) {

    val queue = LinkedList<Thread>()


    fun add(thread: Thread) {
        queue.add(thread)
    }

    fun start() {
        while (!queue.isEmpty()) {
            println(queue.size)
            val current = queue.pop()
            println("${Thread.currentThread().name}")
            println("current thread ${current.name}")

            current.start()
            try {
                Thread.sleep(timeQuantum.toLong())
                current.interrupt()
            } catch (e: InterruptedException) {
                println("interrupted in start")
                Thread.currentThread().interrupt()
                queue.add(current)
            }

        }
    }


}

fun main(args: Array<String>) {

    val roundRobinScheduler = RoundRobinScheduler(100)
    for (i in 1..5) {
        val thread = Thread{
            println("Running in ${Thread.currentThread().name}")
            try {
                Thread.sleep(20000)

            } catch (e: InterruptedException) {
                println("interrupted in ${Thread.currentThread().name}")
                Thread.currentThread().interrupt()
            }
            println("It's done in ${Thread.currentThread().name}")

        }
        roundRobinScheduler.add(thread)
    }
    roundRobinScheduler.start()
}