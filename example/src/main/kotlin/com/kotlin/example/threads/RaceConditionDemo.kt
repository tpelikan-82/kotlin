package com.kotlin.example.threads

class BankAccount {

    private var balance = 100

    @Synchronized
    fun withdraw(amount: Int) {

        if (balance >= amount) {

            Thread.sleep(500)
            balance -= amount
            println("Balance: $balance")
        } else {
            println("Insufficient balance")
        }
    }

}


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

    println("BankAccount example")

    val bankAccount = BankAccount()

    val runnable = Runnable {
        bankAccount.withdraw(80)
    }

    val t1 = Thread(runnable)

    val t2 = Thread(runnable)

    t1.start()
    t2.start()
    t1.join()
    t2.join()




}
