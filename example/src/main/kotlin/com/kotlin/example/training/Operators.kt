package com.kotlin.example.training

class Operators {
}

class Person(val name: String, var age: Int)

fun isOdd(x: Int) = x % 2 != 0

fun operatorDoubleExclamationMark(value: String?) {

    val notNullValue:String = value!!

    // Pass a reference to the isOdd function
    //  1, Function References (Method References)
    val numbers = listOf(1, 2, 3, 4, 5)
    val oddNumbers = numbers.filter(::isOdd)
    println(oddNumbers) // Output: [1, 3, 5]

    // 2, Property reference
    val person = Person("Alice", 30)
    // Get a reference to the 'name' property
    val nameProp = Person::name
    println(nameProp.get(person))


    val listClass = numbers::class

    println(listClass.simpleName)   //ArrayList
}

fun checkNumpointerException(value: String?) {
    // 1. first safe
    value?.let {
        println("this is safe ${value.length} ")
    }

    // 2. second safe
    val nonNullName: String = value ?: "Guest"

    // 3 third not safe solution
    val nonNullData: String = value!!
}



fun main() {

    operatorDoubleExclamationMark("test");


}