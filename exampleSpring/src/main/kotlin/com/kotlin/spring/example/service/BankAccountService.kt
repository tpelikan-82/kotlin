package com.kotlin.spring.example.service

import com.kotlin.spring.example.entity.BankAccount
import com.kotlin.spring.example.repo.BankAccountRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class BankAccountService(
    // 1. Use constructor injection: This is the preferred way in Kotlin and modern Spring.
    //    It makes dependencies immediately available and easier to test.
    // 2. 'private val' makes 'bankAccountRepo' an immutable property that is initialized
    //    by the constructor.
    @Qualifier("bankAccountRepo")
    private val bankAccountRepo: BankAccountRepo,
    @Value("\${spring.application.name}")
    private val applicationName: String
) {




    fun depositAmount(accountId: Int, amount: Double) {
        val bankAccount  = bankAccountRepo.findById(accountId).orElse(null);
        bankAccount.amount += amount;
        bankAccountRepo.save(bankAccount);

    }

    fun getBankAccountByAccountId(accountId: Int): BankAccount {
        val bankAccount = bankAccountRepo.findById(accountId).orElse(null);
        return bankAccount;
    }

    fun findBankAccountById(accountId: Int): BankAccount {
        val bankAccount = bankAccountRepo.findById(accountId).orElse(null);
        return bankAccount;
    }

    fun getBanksAccounts() : List<BankAccount> {
        val bankAccounts = bankAccountRepo.findAll();
        return bankAccounts;
    }

    fun createBankAccount(bankAccount: BankAccount): BankAccount {
        return bankAccountRepo.save(bankAccount);
    }

}