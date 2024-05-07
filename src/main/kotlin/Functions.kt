fun main(){
    myFunction()
    concatenateString("James", "Doe")
    println("Sum of two numbers: ${sum(3, 5)}")
    println("Summation of two numbers: ${summ(4, 5)}")
    println("Divide two nums: ${divide(6, 0)}")
}

fun myFunction(){
    println("Function in kotlin")
}

//Unit means void in Java
fun concatenateString(val1 : String, val2: String) : Unit {
    println(val1.plus(" ").plus(val2))
}

fun sum(x: Int, y: Int) : Int {
    return x + y
}

fun summ(x: Int, y: Int) = x+y

fun divide(x: Int, y: Int) : Int {
    try {
        return x/y
    } catch (e: ArithmeticException){
        println(e.message)
        throw RuntimeException(e.message)
    }
}