fun main() {
    var a = 10
    var b = 15
    if (a == b) {
        println("Both are equal")
    } else if (a > b) {
        println("a is greater than b")
    } else {
        println("b is greater than a")
    }

    // in kotlin if..else can be used as expression
    var greeting = if (a > b) {
        "Good morning"
    } else {
        "Good night"
    }

    println(greeting)

    //{} can be omitted in case of only 1 if statement, act as ternary operator
    val time = 20
    val greeting2 = if (time < 18) "Good day." else "Good evening."
    println(greeting2)


    //Kotlin has when loop similar to switch in java
    println("When")
    var day = 1
    var result = when (day) {
        0 -> "Sunday"
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        else -> "Invalid"
    }

    println(result)

    println("while")
    var i = 1
    while (i < 5) {
        if (i == 2) {
            i++
            continue
        }
        println(i)
        i++
    }

    println("doWhile")
    var j = 0
    do {
        println(j)
        if (j == 4) {
            break
        }
        j++
    } while (j <= 5)

    //There is no traditional for loop in java, but we can generate ranges
    //First and last value is included in the range
    //last value is not excluded
    for (k in 1..5) {
        println(k)
    }

    for (ch in 'a'..'e') {
        println(ch)
    }

    //backward loop
    val range = 5 downTo 1
    for(rangeNum in range){
        println(rangeNum)
    }
    val rangeWithDotsAndStep = 1..5 step 2
    //last value is excluded from the range
    val rangeWithUntil = 1.until(5)
    val charRange = 'A'.until('G')
    val rangeTo = 1.rangeTo(5)
    println("Is range empty- ${rangeWithUntil.isEmpty()}")
    println("Does range contains 5- ${rangeWithUntil.contains(5)}")
    println("Number of elements in specified range: ${rangeWithUntil.count()}")
    val reversedRange = rangeTo.reversed()
    val rangeWithStep = rangeTo step 2
    println(rangeWithStep.forEach({i -> println(i)}))

    //break
    for (nums in 5..15) {
        if (nums == 10) {
            break
        }
        println(nums)
    }
}