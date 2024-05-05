fun main() {
    //Kotlin            |Java
    //Array<Int>	    |Integer[]
    //Array<Array<Int>>	|Integer[][]
    //IntArray	        |int[]
    //Array<IntArray>	|int[][]
    var array = arrayOf("Rose", "Jasmine", "Lily", "Tulip")
    array.forEach { value -> println(value) }
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

    //Array construction through constructor
    var arr: Array<Int> = Array(5, { i -> i * 2 })
    println(arr[2])

    var primIntArr = intArrayOf(5, 4, 45, 322, 23232)
    //Other factory methods -> byteArrayOf(), shortArrayOf(), longArrayOf(), charArrayOf()
    println(primIntArr[3])
    var intArr = IntArray(5)
    println(intArr[2])

    for (i in arr.indices) {
        println(arr[i])
    }

    primIntArr.forEach { value -> println(value) }

    //Initialize array with size = 3 and 0 will be initialized.
    val initArray = Array<Int>(3) { 0 }
}