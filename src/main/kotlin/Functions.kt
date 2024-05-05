fun main(){
    myFunction()
    concatenateString("James", "Doe")
    println("Sum of two numbers: ${sum(3, 5)}")
    println("Summation of two numbers: ${summ(4, 5)}")
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