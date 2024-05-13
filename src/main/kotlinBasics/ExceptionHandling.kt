import java.io.FileInputStream

//There are no checked exceptions in kotlin so there is no throws keyword.
//If we want to specify our function throws some checked exception we can use @Throws annotation
//In Kotlin, try can also be used as expression
//Catch multiple exceptions like java using pipe operator is not supported in Kotlin.
//One can either multiple catch blocks or use when statement in 1 catch block like:
// when(ex){ is Exception1 -> {doSomethingForException1}
//           is Exception2 -> {doSomethingForException2}
//         }
//When we throw only exception from the function it's return type is 'Nothing'

fun throwException(x: Int, y: Int): Nothing {
    if (x == 0) {
        throw RuntimeException("X can't be zero")
    } else {
        throw RuntimeException("Y can't be zero")
    }
}

fun handleExceptions(x: Int, y: Int): Int {
    try {
        return x / y
    } catch (ex: ArithmeticException) {
        println("Error occurred while performing division: $ex.message")
        throw RuntimeException(ex.message)
    } finally {
        println("Finally block is executed irrespective of exception")
    }
}

fun main() {
    val result = try {
        5 / 0
    } catch (e: ArithmeticException) {
        0
    }
//    println(result)
//    println(handleExceptions(4, 5))
//    throwException(0, 1)

    //runCatching internally uses a try expression so we don't have to write try catch in our code.
    val runCatchResult = runCatching { 5 / 0 }
    println("perform operation or else return : ${runCatchResult.getOrElse { exception -> exception.message }}")
    println("perform operation or else return null: ${runCatchResult.getOrNull()}")
    println("perform operation or else get default : ${runCatchResult.getOrDefault(-1)}")
    println("perform operation or throw exception : ${runCatchResult.getOrThrow()}")
}

class MyCustomException(override val message: String, val errorCode: String) : Exception(message) {

}

fun readFile() {
    //.use is like try-with-resources in java
    FileInputStream("file. txt").use { input ->
        var data = input.read()
        println(data)
    }
}