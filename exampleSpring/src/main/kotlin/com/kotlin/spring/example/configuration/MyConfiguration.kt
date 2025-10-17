package com.kotlin.spring.example.configuration

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class MyConfiguration {

    @Bean
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun createSimplePojoClass(): SimplePojoClass {
        val simplePojo = SimplePojoClass("Tom", 44)
        return simplePojo;
    }

}