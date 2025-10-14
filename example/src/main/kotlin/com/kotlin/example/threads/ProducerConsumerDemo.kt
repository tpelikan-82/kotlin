package com.kotlin.example.threads


class SharedBuffer: java.lang.Object() {
    private var data: Int? = null
    private var empty: Boolean = true

    @Synchronized
    fun produce(value: Int)  {
        while(!empty) {
            this.wait()
        }
        data = value
        empty = false
        println("Produced $data")
        this.notify()
    }

    @Synchronized
    fun consume(): Int? {
        while(empty) {
            this.wait()
        }
        empty = true
        println("Consumed $data")
        this.notify()
        return data
    }

}

class Producer(val buffer: SharedBuffer): Runnable{

    override fun run() {
        for (i in 1..5) {
            buffer.produce(i)
        }
    }

}

class Consumer(val buffer: SharedBuffer): Runnable{

    override fun run() {
        for (i in 1..5) {
            println("Consume data ${buffer.consume()}")
        }
    }

}

fun main() {

    val sharedBuffer = SharedBuffer()

    val t1 = Thread(Consumer(sharedBuffer))
    val t2 = Thread(Producer(sharedBuffer))

    t1.start()
    t2.start()

    t1.join()
    t2.join()


}
