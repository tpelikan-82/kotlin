package com.kotlin.example.threads

class Counter : Thread() {

    companion object {
        var counter = 0
        val counterAtomic = java.util.concurrent.atomic.AtomicInteger(0)
        val lock = "string"
    }

    override fun run() {
        for (i in 1..100000) {
            counterAtomic.incrementAndGet()
            incrementCounter()
        }
    }

    fun incrementCounter() {
        synchronized(lock) {
            counter++
        }
    }

}

fun main(args : Array<String>) {

    println("Start")

    val counters = listOf(Counter(), Counter(), Counter(), Counter(), Counter())

    counters.forEach {
        it.start()
    }

    counters.forEach {
        it.join()
    }

    println("Counters: ${Counter.counter}")
    println("Counters atomic: ${Counter.counterAtomic}")


}
