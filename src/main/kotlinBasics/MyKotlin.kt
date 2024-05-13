@file:JvmName("MyCustomKotlinFileName")

package kotlinBasics

import myjava.MyJava

fun main() {
    val result = add(5, 3)
    println(result)

    val area = MyJava().area(3, 4)
    println("Area: $area")
    println(ObjectInKotlinForStatic.count)
}

fun add(num1: Int, num2: Int) = num1 + num2

//Object keyword defines a class and creates the instance also. to access methods and properties use objectName.method()
object ObjectInKotlinForStatic {
    var count: Int = 0
    fun myMethod() {
        println("Printing from object")
    }

    init {
        println("init block in singleton")
    }
}

//lazy initialization is allowed for both val and var and also for nullable types.
val pi: Double by lazy { 3.14 } //At this point the memory is not allocated to this variable, the memory will be allocated only when it is used and next time we will the value from cache memory

class Country {
    //lateinit can be only used with mutable and non-nullable variables.
    //It should be initialized with a value before accessing the property.
    lateinit var name: String

    fun printName() {
        name = "India"
        println("Country name is $name")
    }
}