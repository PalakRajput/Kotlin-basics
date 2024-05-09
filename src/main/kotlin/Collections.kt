fun main(){
    //Mutable list is like ArrayList of Java
    val stringList : MutableList<String> = mutableListOf("One", "Two", "Three")
    stringList += "Four"
    stringList.add("Five")

    //Cannot add any values to this list since it is immutable
    val stringImmutableList : List<String> = listOf("One", "Two", "Three")


    //If a lambda has only param, it can be omitted and 'it' will be provided by default
    stringList.forEach({println("$it")})
    println("Size of collection: ${stringList.size}")
    println("Accessing element at 1st index: ${stringList.get(1)} or ${stringList[1]}")
    println("Get index of particular element: ${stringList.indexOf("Three")}")

    println("Remove element from mutableList: ${stringList.removeAt(1)}")
    stringList[1] = "Ten"
//    stringImmutableList[1] = "Three" -> error


    //Set
    //Mutable set's default implementation is linkedHashSet
    val mutableSet : MutableSet<Int> = mutableSetOf(2, 4, 3, 43, 4, 43, 1, 32,43,43, 454, 4)
    mutableSet.forEach({println("$it")})
    println("Size of collection: ${mutableSet.size} or using count: ${mutableSet.count()}")
    println("Get index of particular element: ${mutableSet.indexOf(2)}")
    println("Does collection contains specific element: ${mutableSet.contains(32)}")

    val set : HashSet<Int> = hashSetOf(2, 343, 43)

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    println("All keys: ${numbersMap.keys}")
    println("All values: ${numbersMap.values}")
    if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
    if (1 in numbersMap.values) println("The value 1 is in the map")
    if (numbersMap.containsValue(1)) println("The value 1 is in the map") // same as previous

    //Default implementation is LinkedHashMap
    val mutableMap1 = hashMapOf(1 to 2, 3 to 4, 5 to 3)
    val mutableMap = mutableMapOf("one" to 1, "two" to 2)
    mutableMap.put("three", 3)
    mutableMap["one"] = 11

    println(mutableMap)

    //We don't need to open stream on collection and using.collect in kotlin
    //These functions doesn't modify the source collection and returns the final collection after performing specified operations
    //So to store them we can also use operationTo methods like filterTo
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)

    val filterResults = mutableListOf<String>()  //destination object
    numbers.filterTo(filterResults) { it.length > 3 }
    println(filterResults)

    //returns map
    println(numbers.associateWith { it.length })

    println(mutableSet.map { i -> i*3 })
    println(mutableSet.mapIndexed() { index: Int, element: Int ->  index * element })

    //Keys are modified and values remain same
    println(numbersMap.mapKeys { element -> element.key.uppercase() })
    //Values are modified and key remains same
    println(numbersMap.mapValues { entry ->  entry.value + entry.key.length })

    //For building maps from collections
    println(numbers.associateBy { element -> element.first().uppercaseChar() })
    println(numbers.associateBy(keySelector = { elem -> elem.first().uppercaseChar() }, valueTransform = { elem -> elem.length }))
}