package com.kotlin.example.patterns.creation

/**
 * creation pattern
 */

abstract class AbstractFactory {
}

interface Chair {
    fun hasLegs(): Boolean
    fun sitOn()
}

// Abstract Product B
interface Table {
    fun putObjectsOn()
    fun getStyle(): String
}

class ModernChair : Chair {
    override fun hasLegs() = true
    override fun sitOn() = println("Sitting on a sleek, modern chair.")
}

class ModernTable : Table {
    override fun putObjectsOn() = println("Objects placed on a glass-top modern table.")
    override fun getStyle() = "Modern"
}

// Victorian Family Products
class VictorianChair : Chair {
    override fun hasLegs() = true
    override fun sitOn() = println("Sitting on a richly carved Victorian chair.")
}

class VictorianTable : Table {
    override fun putObjectsOn() = println("Objects placed on a heavy, wooden Victorian table.")
    override fun getStyle() = "Victorian"
}

interface FurnitureFactory {
    fun createChair(): Chair
    fun createTable(): Table
}

class ModernFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = ModernChair()
    override fun createTable(): Table = ModernTable()
}

// Concrete Factory B: Creates only Victorian products
class VictorianFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = VictorianChair()
    override fun createTable(): Table = VictorianTable()
}

// Client code relies only on the Abstract Factory and Product interfaces
fun client(factory: FurnitureFactory) {
    println("\nClient received a factory: ${factory::class.simpleName}")

    val chair = factory.createChair()
    val table = factory.createTable()

    chair.sitOn()
    println("Table style: ${table.getStyle()}")
    table.putObjectsOn()
}

fun main() {
    // 1. Configure the system to use the MODERN family
    val modernFactory = ModernFurnitureFactory()
    client(modernFactory)
    // Output shows: Modern Chair, Modern Table

    println("\n" + "=".repeat(40) + "\n")

    // 2. Easily switch the system to use the VICTORIAN family
    val victorianFactory = VictorianFurnitureFactory()
    client(victorianFactory)
    // Output shows: Victorian Chair, Victorian Table
}