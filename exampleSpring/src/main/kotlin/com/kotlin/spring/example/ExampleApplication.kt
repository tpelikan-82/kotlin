package com.kotlin.spring.example

import com.kotlin.spring.example.component.BeanWithArgs
import com.kotlin.spring.example.component.MyComponent
import com.kotlin.spring.example.component.MyPrototypeComponent
import com.kotlin.spring.example.configuration.SimplePojoClass
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleApplication

fun main(args: Array<String>) {

    println("Application init started")
	val ctx = runApplication<ExampleApplication>(*args)
    println("Application init ended")
    val beanRef0 = ctx.getBean(MyComponent::class.java)
    val beanRef = ctx.getBean(MyComponent::class.java)
    println(beanRef)
    println(beanRef.someTime)


    val beanRef1 = ctx.getBean(MyPrototypeComponent::class.java)
    val beanRef3 = ctx.getBean(MyPrototypeComponent::class.java)
    val beanRef2 = ctx.getBean(MyPrototypeComponent::class.java)

    val beanWithArgs  = ctx.getBean(BeanWithArgs::class.java)

    val simplePojoClass = ctx.getBean(SimplePojoClass::class.java)
    println(simplePojoClass.name)

}
