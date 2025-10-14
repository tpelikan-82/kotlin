package com.kotlin.example.threads

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.LongAccumulator
import java.util.concurrent.atomic.LongAdder
import java.util.function.LongBinaryOperator

class LongAdderDemo {

    companion object {
        val counter = LongAdder()
    }

}

class LongAccumulatorDemo{
    companion object {
        val acumulated = LongAccumulator(LongBinaryOperator {
            current, new -> maxOf(current, new)
        }, Long.MIN_VALUE)
    }
}

fun main(args: Array<String>) {
    val t1 = Thread {
        for (i in 0..10000) {
            LongAdderDemo.counter.increment()
        }
    }

    val t2 = Thread {
        for (i in 0..10000) {
            LongAdderDemo.counter.increment()
        }
    }


    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(LongAdderDemo.counter)


    val t3 = Thread {
        for (i in 0..10000) {
            LongAccumulatorDemo.acumulated.accumulate(i.toLong())
        }
    }

    val t4 = Thread {
        for (i in 0..10000) {
            LongAccumulatorDemo.acumulated.accumulate((i ).toLong())
        }
    }

    t3.start()
    t4.start()

    t3.join()
    t4.join()

    println("Max value: ${LongAccumulatorDemo.acumulated.get()}")



}