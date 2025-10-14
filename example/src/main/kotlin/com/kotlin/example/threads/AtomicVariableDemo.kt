package com.kotlin.example.threads

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong


class WebServerDemo(val requestCount: AtomicInteger = AtomicInteger(0)) {

    fun handleRequest() {
        println(requestCount.incrementAndGet())
    }

    fun getRequestCount() = requestCount.get()

}


fun main() {

    val server = WebServerDemo(requestCount = AtomicInteger(0))

    val task = server::handleRequest
    for (i in 1..10) {
        Thread(task).start()
    }

    val test = AtomicLong(0.toLong())
    test.getAndIncrement()


}