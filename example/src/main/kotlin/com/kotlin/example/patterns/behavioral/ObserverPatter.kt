package com.kotlin.example.patterns.behavioral

class ObserverPatter {
}

/**
 * 1. OBSERVER Interface
 * Defines the update method that the Subject calls to notify its observers.
 */
interface StockPriceObserver {
    fun update(stockSymbol: String, price: Double)
}

/**
 * 2. SUBJECT (Observable) Interface
 * Defines the methods for managing observers.
 */
interface StockPriceSubject {
    fun registerObserver(observer: StockPriceObserver)
    fun removeObserver(observer: StockPriceObserver)
    fun notifyObservers()
}

// --- CONCRETE IMPLEMENTATIONS ---

/**
 * Concrete Subject: Manages the current state (stock price) and observer list.
 */
class StockMarket(val stockSymbol: String) : StockPriceSubject {
    private val observers = mutableListOf<StockPriceObserver>()

    // The state being observed
    private var price: Double = 0.0

    /**
     * Sets a new price and notifies all registered observers.
     */
    fun setPrice(newPrice: Double) {
        if (newPrice != price) {
            println("\n[MARKET]: $stockSymbol price changed from $price to $newPrice.")
            this.price = newPrice
            notifyObservers()
        }
    }

    override fun registerObserver(observer: StockPriceObserver) {
        observers.add(observer)
        println("-> Observer registered: ${observer.javaClass.simpleName}")
    }

    override fun removeObserver(observer: StockPriceObserver) {
        if (observers.remove(observer)) {
            println("<- Observer removed: ${observer.javaClass.simpleName}")
        }
    }

    override fun notifyObservers() {
        println("Notifying ${observers.size} observers...")
        observers.forEach { it.update(stockSymbol, price) }
    }
}

/**
 * Concrete Observer: Reacts to updates from the Subject.
 */
class Trader(private val name: String) : StockPriceObserver {

    override fun update(stockSymbol: String, price: Double) {
        println("(${name}'s Terminal) Received update for $stockSymbol: $$price")
        when {
            price > 150.0 -> println("  -> ACTION: Price is high! Time to SELL.")
            price < 100.0 -> println("  -> ACTION: Price is low! Time to BUY.")
            else -> println("  -> ACTION: Price stable. Holding position.")
        }
    }
}

fun main() {
    // 1. Create the Subject (Observable)
    val teslaStock = StockMarket("TSLA")

    // 2. Create the Observers
    val alice = Trader("Alice")
    val bob = Trader("Bob")
    val charlie = Trader("Charlie")

    println("--- Initial Setup: Registering Observers ---")
    teslaStock.registerObserver(alice)
    teslaStock.registerObserver(bob)
    teslaStock.registerObserver(charlie)

    // 3. State Change 1: High Price
    teslaStock.setPrice(165.50)

    // 4. Observer Removal (Decoupling)
    println("\n--- Charlie decides to take a break ---")
    teslaStock.removeObserver(charlie)

    // 5. State Change 2: Low Price
    teslaStock.setPrice(98.25)

    // 6. State Change 3: Stable Price
    teslaStock.setPrice(115.00)


}