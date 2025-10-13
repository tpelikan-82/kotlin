package com.kotlin.example.threads

class MainThreadDemo {
}

fun main(args: Array<String>) {

    val mainThread = Thread.currentThread();

    println(mainThread.name)
    println(mainThread.getId())
    println(mainThread.state)
    println(mainThread.priority)
    println(mainThread.isDaemon)

    val threadGroup = mainThread.threadGroup

    println(threadGroup.name)
    println(threadGroup.parent)

    mainThread.stackTrace.forEach { println(it) }

}