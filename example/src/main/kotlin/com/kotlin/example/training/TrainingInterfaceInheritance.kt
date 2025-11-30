package com.kotlin.example.training

import kotlin.math.PI

class TrainingInterfaceInheritance {
}

interface Shape {
    val positionX: Int
    val positionY: Int
    fun getArea(): Double
}

class Circle(override val positionX: Int, override val positionY: Int, val radius: Int) : Shape {
    override fun getArea() : Double = PI * radius * radius
}

abstract class Quadrilateral(override val positionX: Int, override val positionY: Int,
    val sideA: Int, val sideB: Int = 0) : Shape {

    override fun getArea(): Double = (sideA * sideB).toDouble()
    abstract fun getPerimeter() : Int


}

class Square(override val positionX: Int, override val positionY: Int,
             val side: Int) : Quadrilateral(positionX,positionY,side) {
    override fun getPerimeter() = 4 * side
    override fun getArea(): Double = (side * side).toDouble()
}

fun main() {

    val square = Square(1, 1, 1)
    println(square.getArea())
    println(square.getPerimeter())
}