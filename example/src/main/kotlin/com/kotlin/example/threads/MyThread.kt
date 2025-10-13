package com.kotlin.example.threads

class MyThread : Thread() {

    override fun run() {

        println("MyThread started")

        Thread.sleep(2000)

        println("MyThread ended")

    }

}

fun main() {

    println("starting MyThread")

    val myThread = MyThread();
    myThread.start();
    myThread.join();

    println("ending MyThread")
}