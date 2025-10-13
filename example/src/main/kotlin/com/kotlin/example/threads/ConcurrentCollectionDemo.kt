package com.kotlin.example.threads

import java.util.concurrent.ConcurrentHashMap

class ConcurrentCollectionDemo {

    companion object{

        fun concurrentHashMap() {

            val map =  ConcurrentHashMap<String, Int>()

            map.put("one", 1)
            map.put("two", 2)
            map.put("three", 3)

            println("Value for one is " + map.get("one"))

            map.forEach{ println(it.key + ": " + it.value  ) }

        }

    }

}

fun main() {

    ConcurrentCollectionDemo.concurrentHashMap()

}