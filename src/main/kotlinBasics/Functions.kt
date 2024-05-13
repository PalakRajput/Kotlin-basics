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
    println(ifd multiplier 5)

    println("Calling extension function ${"Kotlin".removeFirstLastChar()}")
    println("hello".capitalizeFirstLetter())

    println(applyFivePercentDiscount(20.0))
    println(apply5PerDiscount(20.0))

    println(calculateTotalPrice(30.0, discountForCouponCode("FIVE_BUCKS")))
    println(calculateTotalPrice(30.0, discountForCouponCode("TAKE_10")))

    //calling calculate function with passing lambda as parameter
    println(calculateTotalPrice(30.0, { price -> price - 5.0 }))
    println(calculateTotalPrice(30.0, { price -> price * 0.9 }))

}


fun applyFivePercentDiscount(price: Double): Double = price - 5.0
fun apply10PercentDiscount(price: Double): Double = price * 0.9
fun noDiscount(price: Double): Double = price

//Assigning function to a variable
val apply5PerDiscount = ::applyFivePercentDiscount

//lambda functions, the expression inside {} is called lambda or function literal
//write input params before `->` and return
val applyDiscount: (Double) -> Double = { price -> price - 5.0 }

//applyDiscount is function which accepts double input and returns a double input
//Creating type of the function -> (inputParamType) -> outputParamType
//calculateTotalPrice is a higher order function because it takes a function as input
fun calculateTotalPrice(initialPrice: Double, applyDiscount: (Double) -> Double): Double {
    return applyDiscount(initialPrice) * 1.9;
}

//Returning function from a function
fun discountForCouponCode(couponCode: String): (Double) -> Double = when (couponCode) {
    "FIVE_BUCKS" -> ::applyFivePercentDiscount
    "TAKE_10" -> ::apply10PercentDiscount
    else -> ::noDiscount
}

fun discountForCouponCodeWithLambdas(couponCode: String): (Double) -> Double = when (couponCode) {
    "FIVE_BUCKS" -> { price -> price - 5.0 }
    "TAKE_10" -> { price -> price * 0.9 }
    else -> { price -> price }
}

/**
 * Extension function is a member function of a class which is defined outside the class.
 */
fun String.removeFirstLastChar(): String = this.substring(1, this.length - 1)
fun String.capitalizeFirstLetter(): String {
    return this.replaceFirst(this[0], this[0].uppercaseChar())
}

/**
 * Infix function can only be member functions(defined inside class) or extension functions, they can have only 1 param and it should not be varargs or it cannot have default value
 */
class InfixFunctionDemo(val num: Int) {
    infix fun add(num1: Int): Int {
        return this.num + num1
    }
    infix fun multiplier(num2: Int): Int {
        return num2 * 2
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

