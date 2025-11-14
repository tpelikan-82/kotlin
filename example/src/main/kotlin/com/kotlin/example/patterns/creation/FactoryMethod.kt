package com.kotlin.example.patterns.creation

/**
 * It is creation pattern
 */

class FactoryMethod {
}


interface Vehicle {
    fun drive()
}

class Car : Vehicle {
    override fun drive() {
        println("Driving a Car: smooth and fast.")
    }
}

class Truck : Vehicle {
    override fun drive() {
        println("Driving a Truck: heavy and slow.")
    }
}

abstract class VehicleFactory {
    // The Factory Method: Subclasses must implement this to create a Product.
    abstract fun createVehicle(): Vehicle

    // Creator can also contain core logic that relies on the factory method.
    fun startDriving() {
        val vehicle = createVehicle() // Calls the factory method
        println("--- Starting up the process ---")
        vehicle.drive()
        println("-------------------------------")
    }
}

class CarFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Car()
    }
}

class TruckFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Truck()
    }
}

fun main() {
    // Client code uses the abstract creator interface

    val carCreator: VehicleFactory = CarFactory()
    println("Client: Requesting a Car.")
    carCreator.startDriving()

    // Output:
    // Client: Requesting a Car.
    // --- Starting up the process ---
    // Driving a Car: smooth and fast.
    // -------------------------------

    println("\n" + "=".repeat(30) + "\n")

    val truckCreator: VehicleFactory = TruckFactory()
    println("Client: Requesting a Truck.")
    truckCreator.startDriving()

    // Output:
    // Client: Requesting a Truck.
    // --- Starting up the process ---
    // Driving a Truck: heavy and slow.
    // -------------------------------
}