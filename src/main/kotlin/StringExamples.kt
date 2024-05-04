fun main(){
    val text = "String in Kotlin"

    //To access particular character in string
    println(text[0])

    var char = text[1]
    println(char)

    println("Length of text is: ${text.length}")
    println("UPPERCASE: ${text.uppercase()}")
    println("lowercase: ${text.lowercase()}")

    //Print 0 if both are equal
    println(text.compareTo("String in Kotlin"))

    println(text.indexOf("in"))

    var quotesInString = "That's it"
    println(quotesInString)

    var stringConcatenation =  "James" + " " + "Doe"
    println(stringConcatenation)

    var firstName = "James"
    var lastName = "Doe"
    println(firstName.plus(" ").plus(lastName))

    println("String templates ex: $firstName $lastName")
}