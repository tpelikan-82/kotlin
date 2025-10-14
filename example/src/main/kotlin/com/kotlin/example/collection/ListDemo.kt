package com.kotlin.example.collection

class ListDemo {
}

data class Person(
    val name: String,
    val age: Int,
    val driverLicense: Boolean = false
)

fun main(args: Array<String>) {

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


    val partitions =  list.partition { x -> x > 5 }

    println("first part : ${partitions.first} second part: ${partitions.second}")

    val chunkedList = list.chunked(4)

    println("chunked to ${list.chunked(4)}  back to original list  ${list.chunked(4).flatten()} ")


    val friendGroup = listOf<Person> (
        Person("Tom", 43, true),
        Person("John", 48),
        Person("Jane", 25, true),
        Person("Mary", 23, true),
    )

    println("Can travel by car? ${friendGroup.any{ it.driverLicense }} ")

    println(friendGroup.none{ it.age < 20})
    println(friendGroup.all{ it.age > 20})

    val nobody = emptyList<Person>()
    nobody.all{ it.age > 20}

    val driver = friendGroup.groupBy { it.driverLicense }
    println(driver)
    println(driver.size)
    println(driver.filter { it.value.size > 2 })


    val test = friendGroup.associateBy { it.age }
    println("associate ${test}")
//    println(test.getValue())


    println(listOf<String>("test", "test", "test", "test").flatMap { it.toSet() })

    val input = listOf(4, 3,2,1)

    val factorial = input.reduce { a, b -> a * b }
    val calculation = input.runningReduce { a, b -> a * b }
    println(factorial)
    println(calculation)
}