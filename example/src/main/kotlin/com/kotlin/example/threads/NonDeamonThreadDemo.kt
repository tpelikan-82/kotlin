package com.kotlin.example.threads

class NonDeamonThreadDemo {
}

fun main() {

    val deemonThread = Thread{
        while(true) {
            println("Non Deamon  thread running")
            Thread.sleep(1000)
            println("Non Deamon  thread finished")
        }
    }

    deemonThread.start()
    println("Min thread work here. ..")

    Thread.sleep(5000)

    println("DeamonThreadDemo deamon finished")

}