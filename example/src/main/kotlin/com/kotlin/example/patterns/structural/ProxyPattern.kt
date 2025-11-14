package com.kotlin.example.patterns.structural

class ProxyPattern {
}

interface BankService {
    fun deposit(amount: Double)
    fun withdraw(amount: Double)
    fun getFullTransactionHistory(): String // Sensitive operation
}

class RealBankService : BankService {
    init {
        // Simulates resource setup, e.g., connecting to the database
        println("RealBankService: Database connection established.")
    }

    override fun deposit(amount: Double) {
        println("RealBankService: Deposited $amount.")
    }

    override fun withdraw(amount: Double) {
        println("RealBankService: Withdrew $amount.")
    }

    override fun getFullTransactionHistory(): String {
        return "RealBankService: Successfully retrieved ALL transaction history."
    }
}

class BankServiceProxy(private val userRole: String) : BankService {

    // Lazy initialization of the Real Subject. It's only created when needed.
    private val realService: RealBankService by lazy {
        RealBankService()
    }

    override fun deposit(amount: Double) {
        // Deposit is a safe operation, access is granted.
        realService.deposit(amount)
    }

    override fun withdraw(amount: Double) {
        // Withdrawal is a safe operation, access is granted.
        realService.withdraw(amount)
    }

    override fun getFullTransactionHistory(): String {
        // --- PROXY PROTECTION LOGIC ---
        if (userRole == "ADMIN") {
            println("Proxy: Access granted. User is $userRole.")
            return realService.getFullTransactionHistory()
        } else {
            println("Proxy: Access denied. User role $userRole is not authorized for full history.")
            return "ERROR: Unauthorized access."
        }
    }
}

fun main() {
    // Client A: Regular User
    val regularUser = BankServiceProxy("USER")
    println("--- Test Case 1: Regular User ---")
    regularUser.deposit(100.0)
    val history1 = regularUser.getFullTransactionHistory()
    println(history1)

    // Note: The RealBankService's database connection was NOT established,
    // because the sensitive method was blocked before it could execute.

    println("\n" + "=".repeat(40) + "\n")

    // Client B: Administrator
    val adminUser = BankServiceProxy("ADMIN")
    println("--- Test Case 2: Administrator ---")

    // The Real Service is initialized *only now* because the admin calls the protected method.
    val history2 = adminUser.getFullTransactionHistory()
    println(history2)

    /*
    Output:
    --- Test Case 1: Regular User ---
    RealBankService: Deposited 100.0.
    Proxy: Access denied. User role USER is not authorized for full history.
    ERROR: Unauthorized access.

    ========================================

    --- Test Case 2: Administrator ---
    RealBankService: Database connection established.
    Proxy: Access granted. User is ADMIN.
    RealBankService: Successfully retrieved ALL transaction history.
    */
}