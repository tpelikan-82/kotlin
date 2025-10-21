package com.kotlin.spring.example.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class BankAccount(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var accountId: Int,
    var name: String,
    var amount: Double,
    // 1. @ManyToOne: Defines the relationship type.
    // 2. @JoinColumn: Specifies the actual foreign key column name in the 'bank_account' table.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id", nullable = true)
    val client: Client,
    ) {
}