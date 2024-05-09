//Functions are first class citizen in kotlin means they can be stored in a variable, returned from a function, passed as arguments.

fun main() {
//    myFunction()
//    concatenateString("James", "Doe")
//    println("Sum of two numbers: ${sum(3, 5)}")
//    println("Summation of two numbers: ${summ(4, 5)}")
//    println("Divide two nums: ${divide(6, 0)}")
    appendPrefix("Jessica")
    //named arguments
    appendPrefix(prefix = "Mr", name = "James")
    varargExample()
    varargExample(1, 2, 3)
    val arr = intArrayOf(1, 2, 3)
    varargExample(1, 5, *arr, 7)
    val ifd = InfixFunctionDemo(5)
    println("Calling infix function with infix notation(omitting . and parenthesis) ${ifd add 3}")
    println(ifd.add(4))

    println("Calling extension function ${"Kotlin".removeFirstLastChar()}")

}

/**
 * Extension function is a member function of a class which is defined outside the class.
 */
fun String.removeFirstLastChar(): String = this.substring(1, this.length - 1)


/**
 * Infix function can only be member functions(defined inside class) or extension functions, they can have only 1 param and it should not be varargs or it cannot have default value
 */
class InfixFunctionDemo(val num: Int) {
    infix fun add(num1: Int): Int {
        return this.num + num1
    }
}

fun varargExample(vararg names: Int) {
    println("Argument has ${names.size} elements")
}

/**
 * Function with default values of params
 */
fun appendPrefix(name: String, prefix: String = "Ms") {
    println("$prefix $name")
}

/**
 * Basic function
 */
fun myFunction() {
    println("Function in kotlin")
}

/**
 * Function which doesn't return anything(void in java)
 */
//Unit means void in Java
fun concatenateString(val1: String, val2: String): Unit {
    println(val1.plus(" ").plus(val2))
}

/**
 * Function that takes 2 int params and return an int result
 */
fun sum(x: Int, y: Int): Int {
    return x + y
}

/**
 * Single expression function, since the function has 1 statement the curly braces are omitted and the return type can also be omitted as it can be inferred by the compiler
 */
fun summ(x: Int, y: Int) = x + y

fun divide(x: Int, y: Int): Int {
    try {
        return x / y
    } catch (e: ArithmeticException) {
        println(e.message)
        throw RuntimeException(e.message)
    }
}

//Generic functions
//After fun the angle brackets contains the types of input and output param
fun <T, R> singletonList(item: T, value: R): List<T> {
    return emptyList()
}

//Extension function with generics
fun <T> T.basicToString(): String { // extension function
    return ""
}

