package com.kotlin.example.lambda

import com.kotlin.example.algoritm.depthFirstSearchRecursive

/**
 * Kotlin has five scope functions in total: let, apply, run, also, and with.
 */

class ScopeFunction {
}


/**
 * LET - it is useful when you need null check before you call some method which doesn't allow null value
 */

class ExampleLet {

    private fun getNullableStringValue() : String? {
        return null
    }

    private fun onlySetValue(value: String) {
        println(value)
    }

    fun exampleUsingLet() {
        val testNullable: String? = getNullableStringValue()


        /**
         * replace if(testNullable != null) ...
         */
        val useLet = testNullable?.let { onlySetValue(it)  }
    }


}

/**
 *  APPLY
 */

class ExampleApply {
    var someValue: String? = null
    fun someFunction() {
        println(someValue)
    }
}

/**
 * ALSO can be use add some function to chain of function. It is useful for logging
 */
class ExampleAlso {
     val listName: List<String> = listOf("Tomik", "Lenik", "Vitik", "Jitik")

    fun useAlso() {
        val result = listName.map { it.uppercase() }.also { println(it) }.map { it.lowercase() }.also { println(it) }.reversed()
        println(result)
    }

}

class ExampleWith {

    fun draw(positionx: Int, postiony: Int) {
        println("move to x $positionx and y $postiony")
    }

}


fun main() {

    // LET
    val exampleLet = ExampleLet()
    exampleLet.exampleUsingLet();

    //APPLY
    ExampleApply().apply {
        someValue = "easy to set"
        //call method
        someFunction()
    }

    //RUN and in method uses ALSO
    ExampleAlso().run {
        useAlso()
    }


    //WITH
    with(ExampleWith()) {
        draw(0, 0)
        draw(10,25)
    }


}