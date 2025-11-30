package com.kotlin.example.component

import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class MyComponent {

    private val localTime = LocalTime.now()

    override fun toString(): String {
        return String.format("%02d:%02d", localTime.hour, localTime.minute)
    }

}