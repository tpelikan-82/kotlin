package com.kotlin.example.threads

import java.util.concurrent.atomic.AtomicInteger

class CounterSingleton private constructor() {

    private var counter = 0

    companion object {

        @Volatile
        private var counter: CounterSingleton? = null

        @Synchronized
        fun getInstance(): CounterSingleton {
            if (counter == null) {
                counter = CounterSingleton()
            }
            return counter!!
        }
    }

    @Synchronized
    fun increment() {
        counter++
    }

    @Synchronized
    fun getCount(): Int {
        return counter
    }


}

fun main(args: Array<String>) {

    val runnable = Runnable {
        val counter = CounterSingleton.getInstance()
        for (i in 0 until 100000) {
            counter.increment()
        }
        println("Thread : ${Thread.currentThread().name}  ${counter.getCount()}")
    }

    val t1 = Thread(runnable)
    val t2 = Thread(runnable)
    val t3 = Thread(runnable)


    t1.start()
    t2.start()
    t3.start()

    t1.join()
    t2.join()
    t3.join()

    println("Final count  ${CounterSingleton.getInstance().getCount()}")

}

