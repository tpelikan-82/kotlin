package com.kotlin.spring.example.component

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
@Lazy
class MyPrototypeComponent {

    companion object {
        var counterGlobal  = 0;
    }

    init {
        val counter = counterGlobal++
        println("prototype component id = $counter")
    }

    val someTime = System.currentTimeMillis();

}