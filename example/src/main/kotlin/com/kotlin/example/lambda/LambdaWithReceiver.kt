package com.kotlin.example.lambda

/**
 *
 * Lambda expressions with receiver are also known as function literals with receiver.
 *
 * e.g. MutableList<Int>.() -> Unit -> I expect that it will run on variable type MutableList<Int> the function is
 *                             without parameters and return void -> you can call method which has mutableLIst
 *
 */


class LambdaWithReceiver {
}

fun mutableListExampleSum(callFunction: MutableList<Int>.() -> Int) : Int {
    val list = mutableListOf<Int>()
    return list.callFunction()
}


class MenuItem(val name: String)

class Menu(val name: String) {
    val items = mutableListOf<MenuItem>()

    fun item(name: String) {
        items.add(MenuItem(name))
    }

    fun countMenus() : Int {
        return items.size
    }
}

fun menu(name: String, init: Menu.() -> Unit): Menu {
    // Creates an instance of the Menu class
    val menu = Menu(name)
    // Calls the lambda expression with receiver init() on the class instance
    menu.init()
    return menu
}


fun printMenu(menu: Menu) {
    println("Menu: ${menu.name}")
    menu.items.forEach { println("  Item: ${it.name}") }
}


fun List<Int>.incremented(): List<Int> {
    val originalList = this
    return buildList {
        for (n in originalList) add(n + 1)
    }
}



// Use the DSL
fun main() {


    val result = mutableListExampleSum {
        add(1)
        add(2)
        add(3)
        sum()
    }

    println(result)

    // Create the menu
    val mainMenu = menu("Main Menu") {
        // Add items to the menu
        println(countMenus())
        item("Home")
        println(countMenus())
        item("Settings")
        println(countMenus())
        item("Exit")
        println(countMenus())
    }

    // Print the menu
    printMenu(mainMenu)
    // Menu: Main Menu
    //   Item: Home
    //   Item: Settings
    //   Item: Exit

    val originalList = listOf(1, 2, 3)
    val newList = originalList.incremented()
    println(newList)

}

