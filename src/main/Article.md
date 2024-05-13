# Exploring Kotlin: A Guide to the Basics

Kotlin is a statically typed programming language that runs on the Java Virtual Machine (JVM) and can be used to develop
all kinds of applications, from server-side, client-side web to Android applications. This article aims to provide a
comprehensive understanding of Kotlin's basics, its advantages, data types, loops, functions, and how to run a Kotlin
program.

## Variables & Types

### Variables

In Kotlin, we can declare variables using `var`(Mutable) or `val`(Read-only) keyword.

```kotlin
val myVariable = "Hello World!"
var myMutableVariable = "Hello World!"

//Variables can be initialized after declaration, but in this case, it is necessary to specify the type of the variable.var variable: String
variable = "Can be initialized separately from declaration"
```

Since, variables declared using val are read-only if we try to assign other value to it be will get compiler error that
variable can't be reassigned. But if we have assigned an object to it then we can still modify the properties of the
object.

```kotlin
val listOfInt = mutableListOf(1, 2, 3, 4, 5)
listOfInt.add(6) //Works fine
listOfInt = mutableListOf(3, 2, 1, 4) //Compiler error(variable can't be reassigned)
```

### Types

In the variable declaration, you might have noticed that we didn't specify the type of the variable and it worked fine.
The ability of Kotlin to infer type of the variable from the value assigned to it is called type inference.

If we want to specify the type in Kotlin the syntax is `val variableName : DataType = literal`

#### Types available in Kotlin:

> Note: Kotlin doesn't have primitive types. But Whenever kotlin code is compiled to byte code it converts the data
> types to its primitive type wherever possible.
>
> Example: <br/>
> ```kotlin
> val myNonNullableInt : Int = 10
> val myNullableInt : Int? = 20
> //When the code is compiled to bytecode, myNonNullableInt will be converted to primitive type and myNullableInt will remain object as it can have null value
> ```

1. Number: Byte, Short, Int, Long, Float, Double

```kotlin
val intVariable: Int = 10
val longVariable: Long = 100
val floatVariable: Float = 10.3F
val doubleVariable: Double = 10.9
```

2. String

```kotlin
val stringVariable: String = "Hello"
val stringInUpperCase = stringVariable.uppercase()
val lengthOfString = stringVariable.length
val stringInLowerCase = stringVariable.lowercase()
val substring = stringVariable.substring(1, 4)
val concatenatedString = stringVariable.plus(" World")
```

> Strings are immutable in Kotlin.
> String interpolation:
> ```kotlin
> fun main(){
> val num1 = 10
> val num2 = 20
> println("Sum of num1 and num2 is: ${num1 + num2}")
> }
> ```

3. Boolean

```kotlin
val isTrue = true
val isFalse = false
```

4. Char

```kotlin
val charVariable: Char = 'A'
```

> Note: There is no implicit type casting in Kotlin. So we cannot directly assign Int variable to Long.
> We can use methods like toInt(), toFloat(), etc..
> ```kotlin
> val intVar : Int = 10
> var longVar : Long = intVar //Compile time error(type mismatch)
> longVar = intVar.toLong() //Works fine
> ```
> In Kotlin we cannot use arithmetic operators on two char data types:
> ```kotlin
> var myChar1 = 'A'
> var myChar2 = 'B'
> var sum = myChar1 + myChar2 //Compile time error(type mismatch). In Java this will work fine and print the sum of ASCII value of the chars.
> ```

5. Array

Declaring array:

```kotlin
var emptyArray = emptyArray<Int>()
var intArray: Array<Int> = arrayOf(1, 2, 3, 4, 5)
var primitiveIntArray = intArrayOf(1, 2, 3, 4)
var arrayWithSpecifiedElements =
    Array(3) { element -> element * 3 } //Creates an array of 3 elements and take a lambda function which determines the value of the elements
var arrayWithDefaultValue = IntArray(5) //Creates an array of 5 elements and all the elements will be 0.
var elementAtFirstIndex = primitiveIntArray[1]
var lengthOfArray = primitiveIntArray.size
primitiveIntArray[0] = 4
```

## Nulls

Kotlin provides null safety, meaning that null values cannot be assigned to non-nullable types. This feature is similar
to the Optional type in Java, which is used to handle nullable values.

```kotlin
val string: String = null //Compile time error as we are trying to assign nullable value to non-nullable type.
val nullableString: String? = null //Works fine
println("Getting string length using safe call operator(?): ${nullableString?.length}")
println("Providing default value if string is null using elvis operator: ${nullableString ?: "Default value"}")
```

## Conditional Statements and Loops

### if

```kotlin
var array = arrayOf(1, 2, 3, 4, 5)
if (3 in array) {
    println("Found element 3 in the array")
} else {
    println("Element not found")
}
//If can be used as expression. It behaves as ternary operator
val time = 13
val greeting = if (time < 12) "Good Morning" else "Good afternoon"
```

### when

```kotlin
val int = 10
//When is equivalent of switch in Java
val result = when (int) {
    1 -> "One"
    2 -> "Two"
    in 3..6 -> "Between 3 to 6"
    else -> "Other"
}
```

### for

```kotlin
var array = arrayOf(1, 2, 3, 4, 5)

for (num in array) {
    println(num)
}

//For each take a lambda function
array.forEach { element -> println(element) }

for (i in array.indices) {
    println(array[i])
}

//There is no traditional for loop in Kotlin, but we can use ranges

for (ch in 'A'..'Z')
    println(ch) //Prints alphabets from 'A' to 'Z'

for (num in 1..10)
    println(num) //Prints number from 1 to 10

//for(int i = 1; i <= 20, i = i+2)
for (num in 1..20 step 2) {
    println(num)
}

//Ways to create range
for (num in 1.rangeTo(10)) {
}
//Last value is excluded
for (num in 1.until(10)) {
}
//Reverse for loop
val range = 1.until(10).reversed()
for (num in 20 downTo 0) {
}
```

### while

```kotlin
var int = 10
while (int > 0) {
    if (int == 5)
        break
    println(int)
    int--
}
```

### do-While

```kotlin
var int = 10
do {
    if (int == 5)
        break
    println(int)
    int--
} while (int > 0)
```

### Functions

Functions are considered first-class citizens in Kotlin. They can be passed as arguments, returned from the function and
stored in variable.

**main()** function is the entry point of a program. It is not necessary to put it in class as once it is compiled it
will be added in a class with the same name as the file in which it is defined.

> Syntax: `fun functionName(paramName: paramType) : returnType {
> //Function body
> }`

```kotlin
fun main() {
    val myVariable: Int = 10
    println(myVariable)
    println(performArithmeticOperations(2, 3, 1))
    println(singleExpressionFunction(1, 2))
    println(myVoidFunction(1, 2))
    println(myAnonymousFunction(1, 2))
    println("Calling lambda function: ${multiplierLambda(3)}")
    println("Passing function as an argument: ${functionIsPassedAsArgument(5, multiplierLambda)}")
    println(myFunctionWithDefaultValues(30))
}

fun performArithmeticOperations(x: Int, y: Int, choice: Int): Int {
    val result = when (choice) {
        1 -> x + y
        2 -> x - y
        3 -> x * y
        4 -> x / y
        else -> 0
    }
    return result
}

//Unit is equivalent to void in Java, though in Kotlin it is not mandatory to specify Unit type if the function doesn't return anything.
fun myVoidFunction(x: Int, y: Int): Unit {
    println("This function doesn't return anything")
}

//Here multiplier is a function which is passed as an argument to another function
fun functionIsPassedAsArgument(x: Int, multiplier: (Int) -> Int): Int {
    val multipliedValue = multiplier(x)
    return multipliedValue + 5
}

fun myFunctionWithDefaultValues(x: Int = 10, y: Int = 20): Unit {
    println("Sum of two numbers: ${x + y}")
}
```

**Lambda functions**: Always enclosed in curly braces. Before arrow(->), specify the input parameters and after arrow
specify the function body

```kotlin
val multiplierLambda: (Int) -> Int = { value -> value * 2 }
```

**Single expression functions**: These functions have only 1 statement so the return type and curly braces can be
omitted.

```kotlin
fun singleExpressionFunction(x: Int, y: Int) = x + y
```

**Anonymous functions**: Functions who doesn't have any name.

```kotlin
val myAnonymousFunction = fun(x: Int, y: Int): Int {
    return x + y
}
```

**Extension functions**: These functions provide a way to extend the functionality of existing class without using
inheritance. These functions are member functions defined outside the class.

```kotlin
fun String.capitalizeFirstLetter(): String {
    return this.replaceFirst(this[0], this[0].uppercaseChar())
}
fun main() {
    println("hello".capitalizeFirstLetter())
}
```

**Infix functions**: These functions can be called without the . and (). These functions should be either member
functions of a class or extension functions and can take only 1 parameter.

```kotlin
class MyClass(val num1: Int) {
    infix fun isEquals(num2: Int): Boolean {
        return num2 == num1
    }
    infix fun multiplier(num2: Int): Int {
        return num2 * 2
    }
}

fun main() {
    val myClassObj = MyClass(5)
    println(myClassObj isEquals 5)
    println(myClassObj multiplier 5)
}
```

### Classes

In Kotlin, a class is defined using class keyword.
Getter and setters are generated for the properties automatically, but they can be overridden if required.

* Class with primary constructor: The primary constructor is present in the class header. The constructor keyword can be
  omitted if there are no annotations of access modifier specified.

```kotlin
//In constructor, if we don't specify val or var keyword then those properties will not be class properties they will just be params passed to constructor.
//Both the class definition are same
class MyClass constructor(val name: String, val age: Int) {
    fun printProps() {
        println("$name - $age")
    }
}

class MyClass(val name: String, val age: Int) {
    fun printProps() {
        println("$name - $age")
    }
}
```

* Class with init blocks: Primary constructor cannot contain any runnable code hence init blocks are used to perform any
  operation just after primary constructor is called. They are executed in the order they are defined. If a class has
  both primary and secondary constructor and init blocks, the body of the secondary constructor will be executed after
  primary constructor and init block.

```kotlin
class ClassWithInitBlock(private var name: String, val age: Int) {
    init {
        name = name.uppercase()
    }

    init {
        name = name.lowercase()
    }
}
```

* Class with secondary constructor: `var` or `val` can't be used on secondary constructor.

```kotlin

class MyClass {
    var name: String
    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    fun printProps() {
        println("$name - $age")
    }
}
```

* Class with both primary and secondary constructor: Since this class has primary constructor specified it should be
  called from secondary constructor explicitly.

```kotlin

class MyClass(var name: String) {

    //Property should be initialized because the class object can be created by just primary constructor also
    var age: Int = 0

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }

    fun printProps() {
        println("$name - $age")
    }
}
```

* Inheritance: All the classes, variables & functions are public & final by default, so in order to allow them to be
  extended by other classes, open keyword is used.

```kotlin
open class Animal {
    val color: String = "Black"
    open fun eat() {
        println("Eating food.")
    }
}

class Cat : Animal() {
    val age: Int = 4
    fun speak() {
        println("Meow")
    }
}
class Dog : Animal() {
    val age: Int = 4
    fun speak() {
        println("Bark")
    }
}

fun main() {
    val dog = Dog()
    println("${dog.color} - ${dog.age} - ${dog.eat()} - ${dog.speak()}")
    val cat = Cat()
    println("${cat.color} - ${cat.age} - ${cat.eat()} - ${cat.speak()}")
}
```

* **Data class**: These are like POJOs in Java. If a class is defined using data keyword the getters, setters, hashCode,
  equals, toString, copy and componentN functions are generated by default.

```kotlin
data class Person(val name: String, val age: Int, val address: String)

fun main() {
    val person = Person("James", 25, "34, Street A, LA")
    val anotherPerson = person.copy(name = "John")
    //Since we get componentN functions we can de structure the properties.
    val (name, age) = person
    println("Name: $name, Age: $age $")
}
```

> Limitations of data class:
> These classes cannot be extended. They can't be sealed, open, abstract or inner classes.
> At least one property should be present in primary constructor.
> The property present in primary constructor will be used to generated hashCode, equals and toString methods.

* **Enum class**: Way to represent finite set of possible values. These classes can have member functions as well.

```kotlin
enum class MyEnum(val hexCode: String) {
    RED("#FF0000"),
    BLACK("#000000"),
    WHITE("#FFFFFF"),
    GREEN("#00FF00"); // semicolon is mandatory after last enum value if we are defining other things after enum values

    val family: String = "colors"

    fun printFromEnum() = println("Enum classes can have functions")
}

fun main() {
    println(MyEnum.RED.name)
    println(MyEnum.RED.family)
    println(MyEnum.RED.printFromEnum())
}
```

* **Sealed classes**: Just like Java 17's sealed classes only specific classes can extend it. The classes which extend
  sealed class should be present in same code base & same package.

```kotlin
import kotlin.math.pow

sealed class Shape(val type: String) {
    abstract fun area()
}

class Rectangle(val height: Int, val width: Int, type: String) : Shape(type) {
    override fun area() {
        println("Area of rectangle is: ${height * width}")
    }

}
class Square(val edge: Int, type: String) : Shape(type) {
    override fun area() {
        println("Area of square is: ${edge * edge}")
    }
}
class Circle(val radius: Double, type: String) : Shape(type) {
    override fun area() {
        println("Area of circle is: ${3.14 * radius.pow(2.0)}")
    }
}
```

* **Abstract class**: Abstract class is specified with abstract keyword. Abstract class cannot be instantiated.
  Abstract class and abstract methods are open by default but default methods are not open, to override them in child
  class use `open` keyword.

```kotlin
abstract class MyAbstractClass(val name: String, val age: Int) {
    fun printProps() {
        println("$name - $age")
    }
    abstract fun myAbstractFunction()
}

class Child(name: String, age: Int) : MyAbstractClass(name, age) {
    override fun myAbstractFunction() {
        println("Overridden abstract function in child class.")
    }
}

fun main() {
    val child = Child("James", 10)
    child.printProps()
    child.myAbstractFunction()
}
```

#### Objects

Objects can be created by calling the constructor using the class name and specifying properties inside parentheses.
Kotlin doesn't use `new` keyword for creating objects.

```kotlin
fun main() {
    val circle: Circle = Circle(10.0, "Circle")
    circle.area()
}
```

#### Visibility modifiers

In Kotlin, we have public, private and protected and internal access specifiers.

* public: Default specifier. Visible everywhere.
* private: Only visible in the same file.
* internal: Visible in same module(set of Kotlin files compiled together).
* protected: This specifier is not available for top level declarations. Visible to subclasses.

## Exception Handling

```kotlin
fun divide(num1: Double, num2: Double): Double {
    try {
        return num1 / num2
    } catch (ae: ArithmeticException) {
        println("Exception occurred while dividing two numbers: ${ae.message}")
        return 0.0
    }
}

fun divideWithoutTry(num1: Double, num2: Double) = num1 / num2

fun main() {
    val result = divideWithoutTry(12.0, 0.0)
    println(runCatching {
        divideWithoutTry(
            12.0,
            0.0
        )
    }.getOrNull()) //This will try to divide and returns null in case of error.
    println(runCatching {
        divideWithoutTry(
            12.0,
            0.0
        )
    }.getOrDefault(0.0)) //This will return the result and return 0.0 as default value in case of exception.
    println(runCatching { divideWithoutTry(12.0, 0.0) }.getOrElse { exception -> exception.message })
}
```