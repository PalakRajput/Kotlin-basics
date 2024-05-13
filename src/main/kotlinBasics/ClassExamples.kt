package kotlinBasics


//All classes in kotlin have common superclass 'Any' which has 3 methods, equals(), hashCode() and toString()
/**
 * Class with secondary constructor
 */

class Person {
    val name: String
    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

/**
 * Class with primary constructor
 */
class PersonPC(val name: String, val age: Int) {

}

open class ClassWithSecondaryConstructor {
    var name: String
    var age: Int
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
    open fun printProps() {
        println("$name - $age")
    }
}

open class ClassWithBothPrimaryAndSecondaryConstructor(val name: String) {
    var age: Int = 0

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }
    open fun printProps() {
        println("$name - $age")
    }
}

/**
 * Class with both primary and secondary constructor. If both primary and secondary constructor are present then  the values not assigned in primary constructor should be marked as nullable
 */
class PersonPCSC(val name: String) {
    var age: Int? = null

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }
}

/**
 * By default, Kotlin classes are final so use open keyword.
 * properties and methods can be overridden in child using override keyword
 */
open class Base(open val name: String) {
    open fun printName() {
        println("Hi, I am $name")
    }
}

class Derived(override val name: String) : Base(name) {
    override fun printName() {
        super.printName()
        println("Hey I am called from derived class")
    }
}


open class Base1(val name: String, val age: Int) {

    open fun printProps() {
        println("$name - $age")
    }
}

class Derived1 : Base1 {
    val addr: String

    constructor(name: String, age: Int, addr: String) : super(name, age) {
        this.addr = addr
    }

    override fun printProps() {
        super.printProps()
        println(addr)
    }
}

open class Rectangle {
    open fun draw() { /* ... */
    }
}

interface Polygon {
    fun draw() { /* ... */
    } // interface members are 'open' by default
}

class Square() : Rectangle(), Polygon {
    // The compiler requires draw() to be overridden: Class 'Square' must override public open fun draw(): Unit defined in Rectangle because it inherits many implementations of it
    override fun draw() {
        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
    }
}


open class Rect {
    open fun draw() {
        println("Drawing a rectangle")
    }

    open val borderColor: String get() = "black"
}

/**
 * Inner class has access to properties of outer class.
 * Regular nested class(without the keyword inner), doesn't have access to properties of outer class.
 *
 *
 * Nested Classes: Static by default, can be accessed without creating the object of outer class
 * Use cases for nested classes: Utility classes that are closely related to outer class but doesn't need access to outer class state
 *
 * Inner class: Non static by default hence an instance of outer class is needed to create instance of inner class
 * Use case: Helper classes specifically designed for outer classes
 *
 */
class FilledRectangle : Rect() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    override val borderColor: String
        get() = "Blue"

    inner class Filler {
        fun fill() {
            println("Filling")
        }

        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rect's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color $borderColor") //Uses FilledRectangle borderColor property
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}")// Uses Rect's implementation of borderColor's get()
        }
    }
}

/**
 * Abstract class and functions are open by default
 * Non abstract function can be overridden as abstract function
 * Abstract classes cannot be instantiated
 */
abstract class Vehicle {
    abstract fun run()
    open fun computeTaxes() {}
}

abstract class SafeCar : Vehicle() {
    override fun run() {
        println("SafeCar is running safely..")
    }

    override abstract fun computeTaxes()
}

/**
 * An interface can have abstract and non abstract methods
 * Interface can only have abstract members
 */
interface Pet {
    var name: String
    fun eat()
    fun sleep()
    fun drink() {
        println("$name is drinking water.")
    }
}



class Cat(override var name: String) : Pet {
//    override var name: String = ""
//        get() = field
//        set(value) {
//            name = value
//        }

    override fun eat() {
        println("Cat eats fish")
    }

    override fun sleep() {
        println("Cat sleeps a lot")
    }

    //Companion objects, the variable count and method staticMethod can be called without creating the constructor of this class.
    companion object {
        var count: Int = 1
        fun staticMethod() {
            println("Hey I am static method, current count is $count")
            count++
        }
    }
}

/**
 * Data classes can't be sealed, inner, abstract or open, so these can't be extended.
 * Getters, setters, hashcode, tostring, equals, componentN, copy functions are created by default.
 * These are like pojos.
 * Since these classes provide componentN functions we can do destructuring to get values of the properties
 * There should be atleast 1 property present in primary constructor because that is used by equals, hashcode and toString methods
 * Any other properties defined in the body of the class will not be considered by the 3 methods.
 */
data class Character(val name: String, val age: Int)

enum class EnumWithPropsAndFunctions(val height : Int){
    CORGI(23),
    GOLDEN(30); //-> semicolon is required after last enum entry

    val family: String = "Dogs"

    fun printFromEnum() = println("Enum can have functions ${this.height}")

    //To access these function and property outside the class use EnumName.property/function
}

enum class Types {
    BASIC,
    STANDARD,
    CLASSIC
}

fun main() {
//    val derived: Base = Derived("Base")
//    derived.printName()

    val fr = FilledRectangle()
    fr.draw()

    val pet: Pet = Cat("Tom")
    pet.eat()
    pet.sleep()
    pet.drink()
    Cat.staticMethod()
    Cat.staticMethod()

    val mickeyMouse = Character("Mickey Mouse", 82)
    //create a copy of mickey mouse and only change the age to 83
    val mickeyMouseToday = mickeyMouse.copy(age = 83)

    // destructuring declarations
    val (name, age) = mickeyMouseToday
    println("$name, $age years of age")

    mickeyMouseToday.component1() // => name
    mickeyMouseToday.component2() // => age

    println(Types.BASIC.name)
    println("Calling function present in enum class: ${EnumWithPropsAndFunctions.CORGI.printFromEnum()}")


    val printVal = PetEx { val1: String, val2: String  -> println("$val1 - $val2") }
    println(printVal.printValues("Tom", "Corgi"))
}


/**
 * Functional interface in kotlin is specified using fun keyword as opposed to @FunctionalInterface in Java.
 *
 */
fun interface PetEx {
    fun printValues(name: String, breed: String)
}

class ShapeExt(type: String) : Shape(type){
    override fun area() {
        println("In different package")
    }

}