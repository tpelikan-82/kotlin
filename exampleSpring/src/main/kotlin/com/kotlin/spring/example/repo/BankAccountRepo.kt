package com.kotlin.spring.example.repo

import com.kotlin.spring.example.entity.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankAccountRepo : JpaRepository<BankAccount, Int> {
}