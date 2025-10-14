package com.kotlin.example.threads

data class  BankAccount2(val accountNumber: String, val balance: Double) {

    fun deposit(amount: Double) : BankAccount2 {
        return BankAccount2(accountNumber, balance + amount)
    }

    fun withdraw(amount: Double) : BankAccount2 {
        if (amount > balance) throw IllegalArgumentException("Amount can't be greater than the balance")
        return BankAccount2(accountNumber, balance - amount)
    }

}

fun main(args: Array<String>) {

    val bankAccount = BankAccount2("123", 1000.0);

    var updatedAccount = bankAccount.deposit(500.0)
    updatedAccount = updatedAccount.withdraw(100.0)

    println(bankAccount)
    println(updatedAccount)

}