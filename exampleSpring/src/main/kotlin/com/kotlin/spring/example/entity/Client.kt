package com.kotlin.spring.example.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.FetchType



@Entity
data class  Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val clientId: Int,
    val name: String,

    // 1. @OneToMany: Defines the relationship type.
    // 2. mappedBy: Tells JPA which field in the 'BankAccount' entity holds the foreign key (the 'many' side).
    // 3. cascade: Specifies that operations (like deletion) should cascade to the BankAccount entities.
    // 4. orphanRemoval: If an account is removed from this list, it should be deleted from the database.
    @OneToMany(
        mappedBy = "client",    // The field name in the BankAccount class
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER )
    val bankAccount: MutableList<BankAccount> = mutableListOf<BankAccount>()

)  {
}