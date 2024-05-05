fun main() {
    println("Hello World!")
    //Single line comment
    /* Multiline comments.
    Line 1
    Line 2
     */
    // variables declared with var can be modified
    var name = "Jessica"
    //variables declared with val cannot be reassigned
    val age = 23
    // age = 25 -> Compilation error
    name = "Jane"
    println(name)
    print(3+3)

    var name1 : String = "James"
    val age1 : Int = 26

    println(name1)
    print(age1)

    //Variables can be declared without assigning the printValues but only when type is specified
    var name2 : String
    name2 = "John"

    /*var name3
    name3 = "Jess"*/

    var myNum = 2 //Int
    var myDecimal = 5.99 //Double
    var myString = "James" //String
    var myBoolean = true //Boolean
    var myChar = 'C' //Char
    var myFloat = 4.5f //Float

    //Data types are divided into Numbers, Characters, Booleans, Arrays, Strings
    //Numbers -> Int(default if type is not specified), Long, Byte, Short & Decimal -> Float, Double(default if type is not specified)

    // Byte -> -128 to 127
    var myByte: Byte = 124

    //Short -> -32768 to 32767
    var myShort: Short = 200

    //Int -> -2^31 to 2^31-1
    var myInt : Int = 32999

    //Long
    var myLong : Long = 2323232332

    //Float -> precision upto 6-7 digits
    var myFloatNum : Float = 4.55F

    //Double -> precision upto 15 digits
    var myDoubleNum : Double = 3.544

    //Booleans
    var isKotlinFun : Boolean = false
    var myTrue : Boolean = true

    ///type of variables in Kotlin
    println(myByte::class)
    println("Type of myNum variable: ${myNum::class}")

    //var myCharacter : Char = 66 -> Error in Kotlin but will print the char corresponding to this ASCII value in java
    var myChar1 = 'A';
    var myChar2 = 'B';
    //println(myChar1 + myChar2) -> error in kotlin but will print summation of ASCII value of A and B in java

    //Direct type conversion throws type mismatch compile time error in kotlin
    var myInt2 = 15
    //var myLong2 : Long = myInt2 -> this would work fine in java

    //to convert to diff type use toLong(), toByte(), toShort(), toInt(), toFloat(), toDouble(), toChar()

    var myLong2 : Long = myInt2.toLong();

    // == check for .equals(), === checks if two objects are pointing to same reference






}