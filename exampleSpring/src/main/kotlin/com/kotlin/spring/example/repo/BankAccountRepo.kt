package com.kotlin.spring.example.repo

import com.kotlin.spring.example.entity.BankAccount
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
interface BankAccountRepo : JpaRepository<BankAccount, Int> {

    @Query("select b from BankAccount b where b.accountId >= ?1 and b.accountId <= ?2")
    fun findBankAccountByAccountIdRange(from: Int, to: Int): List<BankAccount>

    fun findBankAccountByAccountId(accountId: Int, pageable: Pageable ): Page<BankAccount>

}