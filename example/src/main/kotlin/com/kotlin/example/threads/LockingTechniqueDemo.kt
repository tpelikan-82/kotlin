package com.kotlin.example.threads

import java.util.concurrent.locks.ReentrantLock


class SharedCounter {

    private var counter = 0
    companion object {
        private val lock = ReentrantLock()
    }

    fun increment() {
        lock.lock()
        counter++
        println( "Thread ${Thread.currentThread().name} counter:  $counter")
        lock.unlock()
    }

    fun getCounter(): Int {
        return counter
    }

}

class WorkerThread(val counter: SharedCounter) : Runnable {
    override fun run() {
       for (i in 0 until 10) {
           counter.increment()
           Thread.sleep(500)
       }
    }

}

fun main() {
    val sharedCounter = SharedCounter();

    val t1 = Thread(WorkerThread(sharedCounter))
    val t2 =  Thread(WorkerThread(sharedCounter))
    val t3 =  Thread(WorkerThread(sharedCounter))


    t1.start()
    t2.start()
    t3.start()

    t1.join()
    t2.join()
    t3.join()

    println("total count is ${sharedCounter.getCounter()}")

}