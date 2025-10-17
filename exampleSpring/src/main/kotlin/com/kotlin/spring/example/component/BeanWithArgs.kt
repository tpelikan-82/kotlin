package com.kotlin.spring.example.component

import org.springframework.boot.ApplicationArguments
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
@Lazy
class BeanWithArgs(
    val args: ApplicationArguments
) {

    init {
        println("test Arguments")
        args.sourceArgs.forEach { println(it) }

    }

}