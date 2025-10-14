package com.kotlin.example.threads

interface Product {
    fun use()
}

class ProducOne: Product {
    override fun use() {
        println("I'm working one")
    }
}

class ProducTwo: Product {
    override fun use() {
        println("I'm working two")
    }
}

class ProductFactory {

    private val productMap = HashMap<String, Product>()

    @Synchronized
    fun getProduct(productName: String): Product {
        if (!productMap.containsKey(productName)) {
            println("It is create ${productName}")
            when(productName) {
                "One" -> productMap[productName] = ProducOne()
                "Two" -> productMap[productName] = ProducTwo()
                else -> throw IllegalArgumentException("Unknown product name: $productName")
            }

        }
        return productMap[productName]!!
    }

}

fun main() {

    val task = Runnable {
        val productOne = ProductFactory().getProduct("One")
        val productTwo = ProductFactory().getProduct("Two")

        productOne.use()
        productTwo.use()

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

