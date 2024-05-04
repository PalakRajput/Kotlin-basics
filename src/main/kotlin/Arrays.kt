fun main() {
    var array = arrayOf("Rose", "Jasmine", "Lily", "Tulip")
    println("Element at 0th index: ${array[0]}")
    println("Size of array is: ${array.size}")

    array[0] = "Sunflower"
    println("Element at 0th index: ${array[0]}")

    //check if element exists
    if ("Rose" in array) {
        println("Exists")
    } else {
        println("Doesn't exists")
    }

    //Looping through array
    for (flower in array) {
        println(flower)
    }
}