package com.kotlin.spring.example.controller

import com.kotlin.spring.example.entity.BankAccount
import com.kotlin.spring.example.service.BankAccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["/api"])
@CrossOrigin
class BankAccountController(
    private val bankAccountService: BankAccountService
) {

    @GetMapping(value = ["/bankAccounts"])
    fun getBankAccounts() : ResponseEntity<List<BankAccount>> {

        val bankAccounts = bankAccountService.getBanksAccounts()

        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping(value = ["/{bankAccountId}"])
    fun getBankAccount(@PathVariable bankAccountId: Int) : ResponseEntity<BankAccount> {
        val bankAccount = bankAccountService.findBankAccountById(bankAccountId)
        if (bankAccount == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(bankAccount)
    }

    @PostMapping(value = ["/bankAccount"])
    fun createBankAccount(@RequestBody bankAccount: BankAccount) : ResponseEntity<BankAccount> {
        val bankAccount  = bankAccountService.createBankAccount(bankAccount);
        return ResponseEntity.ok(bankAccount)
    }

    @PutMapping(value = ["/bankAccount/{bankAccountId}"])
    fun updateBankAccount(@PathVariable bankAccountId :Int, @RequestBody bankAccount: BankAccount) : ResponseEntity<BankAccount> {
        return ResponseEntity.ok(bankAccount)
    }


    @DeleteMapping(value = ["/bankAccount/{bankAccountId}"])
    fun deleteBankAccount(@PathVariable bankAccountId: Int) : ResponseEntity<Any> {
        return ResponseEntity.ok(bankAccountId)
    }

}