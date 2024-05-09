fun main() {
    val text = "String in Kotlin"
    val emptyString = String()

    val sb : StringBuilder = StringBuilder("Hello, ")
    sb.append("World")
    val str : String = sb.toString()

    val nullableValue : String? = null
    //Usage of ?: (Elvis operator)
    println("If variable is null then default value will be printed: ${nullableValue ?: "Default"}")

    //To access particular character in string
    println(text[0])
    println(text.get(0))

    var char = text[1]
    println(char)

    println("Length of text is: ${text.length}")
    println("UPPERCASE: ${text.uppercase()}")
    println("lowercase: ${text.lowercase()}")
    println("Substring: ${text.substring(0, 5)}")
    println("Replace characters in string: ${text.replace("i", "::")}")

    //Print 0 if both are equal
    println(text.compareTo("String in Kotlin"))

    println(text.indexOf("in"))

    var quotesInString = "That's it"
    println(quotesInString)

    var stringConcatenation = "James" + " " + "Doe"
    println(stringConcatenation)

    var firstName = "James"
    var lastName = "Doe"
    println(firstName.plus(" ").plus(lastName))

    println("String templates ex: $firstName $lastName")
}