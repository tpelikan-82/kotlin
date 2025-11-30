package com.kotlin.example

import com.kotlin.example.component.MyComponent
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleApplication

fun main(args: Array<String>) {
	val  context = runApplication<ExampleApplication>(*args)
    val beanRef = context.getBean("myComponent",)
    println(beanRef)
}
