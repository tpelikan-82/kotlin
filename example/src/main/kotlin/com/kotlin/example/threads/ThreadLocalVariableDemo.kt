package com.kotlin.example.threads

import com.kotlin.example.threads.ThreadLocalVariableDemo.Companion.threadLocalValue

class ThreadLocalVariableDemo {

    companion object{
        val threadLocalValue =  ThreadLocal.withInitial { 0 }

    }

}

fun main() {

    val task = Runnable {
        val value = threadLocalValue.get()
        threadLocalValue.set(value + 1)
        println("Thread id ${Thread.currentThread().id} value ${threadLocalValue.get()}")
         }

    val t1 = Thread(task)
    val t2 = Thread(task)
    val t3 = Thread(task)


    t1.start()
    t2.start()
    t3.start()

    t1.join()
    t2.join()
    t3.join()

}