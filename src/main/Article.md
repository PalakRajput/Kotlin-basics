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

//Variables can be initialized value after declaration but in this it is necessary to specify the type of the variable.
var variable: String
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

In the variable declaration you might have noticed that we didn't specify the type of the variable and it worked fine.
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
> var sum = myChar1 + myChar2 //Compile time error(type mismatch)
> ```

5. Array

Declaring array:

```kotlin
var array = emptyArray<Int>()
var array: Array<Int> = arrayOf(1, 2, 3, 4, 5)
var array = intArrayOf(1, 2, 3, 4)
var array =
    Array(3) { element -> element * 3 } //Creates an array of 3 elements and take a lambda function which determines the value of the elements
var array = IntArray(5) //Creates an array of 5 elements and all the elements will be 0.
var elementAtFirstIndex = array[1]
var lengthOfArray = array.size
array[0] = 4
```

## Loops & Statements

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
//There is no traditional for loop in Java but we can use ranges

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

### if

```kotlin
var array = arrayOf(1, 2, 3, 4, 5)
if (3 in array) {
    println("Found element 3 in the array")
} else {
    println("Element not found")
}
//If can be used as expression. It behaves are ternary operator
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

Functions are called first class citizen in Kotlin. They can be passed as arguments, returned from the function and
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

### Class

In kotlin, a class is defined using class keyword. All the classes are final by default so in order to allow them to be
extended by other classes, open keyword is used.
Classes have primary and secondary constructor. All the secondary constructor of a class should call primary constructor
of that class if present.

```kotlin
class MyClass {

}
```