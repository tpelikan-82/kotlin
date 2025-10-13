package com.kotlin.example.threads

class NamingThread {
}

fun main() {

    val thread1 = Thread { println("I'm working in thread 1: ${Thread.currentThread().name}")  }

    thread1.name = "test-thread"

    thread1.start()

}