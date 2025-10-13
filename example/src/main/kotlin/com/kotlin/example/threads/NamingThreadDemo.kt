package com.kotlin.example.threads

class NamingThreadDemo {



}

fun main() {

    val thread1 = Thread{
        println("NamingThreadDemo start")
    }
    thread1.name = "Custom thread - 1"
    thread1.start()

    println("NamingThreadDemo start ${Thread.currentThread().name} ")
    println("NamingThreadDemo start ${thread1.name}")


}