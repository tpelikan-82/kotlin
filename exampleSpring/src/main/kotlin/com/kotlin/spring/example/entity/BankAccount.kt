package com.kotlin.spring.example.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class BankAccount(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var accountId: Int,
    var name: String,
    var amount: Double) {
}