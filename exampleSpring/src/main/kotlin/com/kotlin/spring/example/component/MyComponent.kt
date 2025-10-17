package com.kotlin.spring.example.component

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component("coolBean")
@Scope("singleton")
@Lazy
class MyComponent {

    companion object {
        var counterGlobal  = 0;
    }

    init {
        val counter = counterGlobal++
        println("component id = $counter")
    }



    val someTime = System.currentTimeMillis();



}