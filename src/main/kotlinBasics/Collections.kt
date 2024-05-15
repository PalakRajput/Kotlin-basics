import java.time.LocalDateTime

fun main() {
    //Mutable list is like ArrayList of Java
    val stringList: MutableList<String> = mutableListOf("One", "Two", "Three")
    stringList += "Four"
    stringList.add("Five")

    //Cannot add any values to this list since it is immutable
    val stringImmutableList: List<String> = listOf("One", "Two", "Three")


    //If a lambda has only param, it can be omitted and 'it' will be provided by default
    stringList.forEach({ println("$it") })
    println("Size of collection: ${stringList.size}")
    println("Accessing element at 1st index: ${stringList.get(1)} or ${stringList[1]}")
    println("Get index of particular element: ${stringList.indexOf("Three")}")

    println("Remove element from mutableList: ${stringList.removeAt(1)}")
    stringList[1] = "Ten"
//    stringImmutableList[1] = "Three" -> error


    //Set
    //Mutable set's default implementation is linkedHashSet
    val mutableSet: MutableSet<Int> = mutableSetOf(2, 4, 3, 43, 4, 43, 1, 32, 43, 43, 454, 4)
    mutableSet.forEach({ println("$it") })
    println("Size of collection: ${mutableSet.size} or using count: ${mutableSet.count()}")
    println("Get index of particular element: ${mutableSet.indexOf(2)}")
    println("Does collection contains specific element: ${mutableSet.contains(32)}")

    val set: HashSet<Int> = hashSetOf(2, 343, 43)

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    println("All keys: ${numbersMap.keys}")
    println("All values: ${numbersMap.values}")
    if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
    if (1 in numbersMap.values) println("The value 1 is in the map")
    if (numbersMap.containsValue(1)) println("The value 1 is in the map") // same as previous

    //Default implementation is LinkedHashMap
    val mutableMap1 = hashMapOf(1 to 2, 3 to 4, 5 to 3)
    val mutableMap = mutableMapOf("one".to(1), "two" to 2)
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

    println(mutableSet.map { i -> i * 3 })
    println(mutableSet.mapIndexed() { index: Int, element: Int -> index * element })

    //Keys are modified and values remain same
    println(numbersMap.mapKeys { element -> element.key.uppercase() })
    //Values are modified and key remains same
    println(numbersMap.mapValues { entry -> entry.value + entry.key.length })

    //For building maps from collections
    println(numbers.associateBy { element -> element.first().uppercaseChar() })
    println(
        numbers.associateBy(
            keySelector = { elem -> elem.first().uppercaseChar() },
            valueTransform = { elem -> elem.length })
    )


    //Building collections from builder functions buildMap(), buildList(), buildSet()
    val x = listOf('b', 'c')

    val y = buildList() {
        add('a')
        addAll(x)
        add('d')
    }

    val mySet: Set<Long> = buildSet {
        add(1)
        add(3)
        add(5)
    }
    val myMap = buildMap<String, Int> {
        put("One", 1)
        put("Two", 2)
    }

    val emptyList = emptyList<String>()
    val emptySet: Set<String> = emptySet()
    //Copy a collection creates a shallow copy and hence altering copy will alter original collection as well
    emptySet.toSet()
    emptySet.toMutableSet()
    emptyList.toMutableSet()
    emptyList.toMutableList()

    //In this case the original list was not modified because it was immutable list
    val originalList = listOf(1, 2, 3, 4, 5, 6)
    val copy = originalList.toMutableList()
    copy.add(7)
    println("Original list: $originalList")
    println("Copied list: $copy")

    //But if I add the type in the variable declaration then the copied list will be immutable
    val copiedList: List<Int> = originalList.toList()
//    copiedList[3] = 4 -> compilation error

    val iterator: Iterator<Int> = originalList.iterator()
    while (iterator.hasNext()) {
        print("${iterator.next()} ")
    }
    println("-------------------------------")
    for (string in originalList) {
        print("$string ")
    }

    //ListIterator allows list to be traversed in both directions
    val listIterator: ListIterator<Int> = originalList.listIterator()
    println("\n************Forward*****************")
    while (listIterator.hasNext()) {
        print("${listIterator.next()} ")
    }
    println("\n************Backward*****************")
    while (listIterator.hasPrevious()) {
        print("${listIterator.previous()} ")
    }

    println("\nMutable iterator")
    //ListIterator on mutable list gives mutable iterator which supports removing, adding and setting of elements while iterating
    val mutableIterator = copy.listIterator()
    while (mutableIterator.hasNext()) {
        print("${mutableIterator.next()} ")
        if (mutableIterator.nextIndex() == 3) {
            mutableIterator.add(9)
        } else if (mutableIterator.nextIndex() == 4) {
            mutableIterator.remove()
        }
    }
    println("\nMutable list after mutable iterator iterations: $copy")

    //In Kotlin all the collections already support the operations from stream api in java and it is eagerly evaluated unlike java. so for lazy evaluation use sequences
    println(LocalDateTime.now())
    println(
        "Multiply each elem by 3 and filter elements that are greater than 10 and find first element: ${
            originalList.map { element -> element * 3 }.filter { element -> element > 10 }.first()
        }"
    )
    println(LocalDateTime.now())
    val sequence = originalList.asSequence()
    println(
        "Multiply each elem by 3 and filter elements that are greater than 10 and find first element using sequence: ${
            sequence.map { element -> element * 3 }.filter { element -> element > 10 }.first()
        }"
    )
    println(LocalDateTime.now())
    //Stateless operations: filter(), map(), each element is processed independently
    //Stateful operations: take() or drop()
    //Intermediate(map(), filter()) and terminal operations(sum(), toList())
    //Since in sequence each element is processed individually so if we use operations like take(), first() then it takes less time and if the condition is satisfied than further elements will not be processed
    val sentence: String = "The quick brown fox jump over the lazy dog"
    val mySequence = sentence.splitToSequence(" ")
    println(mySequence.filter { element -> element.length > 3 }.map { element -> element.uppercase() }.toList())

    //For transforming collection the operations are .map(), .mapIndexed(), .mapNotNull()[this removes null elements], .mapNotNullIndexed()
    val listWithNulls = mutableListOf("Axe", "Ape", "Tap", "Mug", null)
    println(listWithNulls.mapNotNull { element -> element?.uppercase() })

    //Zipping returns a collection of pairs with elements at same index
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals) //or colors.zip(animals)

    //flatten or flatMap
    val multiDList = listOf(listOf(2, 4, 5), listOf(4, 3, 4, 4), listOf(87, 65, 43))
    println("2D list: $multiDList")
    println("Flat list: ${multiDList.flatten()}")
    println("Using flat map: ${multiDList.flatMap { element -> element }}")

    val numberList = listOf("one", "two", "three", "four")

    println(numberList)
    println(numberList.joinToString("-", "[ ", " ]"))

    val listString = StringBuffer("The list of numbers: ")
    numberList.joinTo(listString)
    println(listString)

    //Filtering -> filter(), filterIndexed(), filterNot()[opposite of filter()], .filterNotNull()[returns all non nullable elements]
    //PartioningBy -> partition()
    val (match, notMatch) = numberList.partition { element -> element.length > 3 }
    println("Elements that matched the condition: $match")
    println("Elements that didn't matched the condition: $notMatch")

    //Alternatives of anyMatch(), allMatch(), noneMatch() -> any(), all(), none()
    println("Any match: ${numberList.any { element -> element.length == 4 }}")
    println("All match: ${numberList.all { element -> element.length == 4 }}")
    println("No match: ${numberList.none { element -> element.length == 10 }}")

    //+ to add element., - to remove element
    println(numberList + "five")
    println(numberList - "one")

    //Grouping by
    println("Group by first char: ${numberList.groupBy { element -> element.first() }}")
    //Collectors.counting()
    println("Group by first char: ${numberList.groupingBy { element -> element.first() }.eachCount()}")

    //Slice the collection
    println(numbers.slice(1..3))
    println(numbers.slice(1..4 step 2))

    println("Take first 3 elements: ${numbers.take(3)}")
    println("Take last 3 elements: ${numbers.takeLast(3)}")
    println("Drop first element: ${numbers.drop(1)}")
    println("Drop last 5 elements: ${numbers.dropLast(5)}")

    //Retrieve elements
    println("First element: ${numberList.first()}")
    println("Last element: ${numberList.last()}")
    println("Element at: ${numberList.elementAt(0)}")
    println("ElementAtOrNull: ${numberList.elementAtOrNull(10)}")
    println("ElementAtOrElse: ${numberList.elementAtOrElse(10) { "Not found" }}")

    //Comparator has compare which returns int and Comparable has compareTo which returns int
    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    println(listOf("aaa", "bb", "c").sortedWith(lengthComparator))
    //Short way to write comparator is to use compareBy()
    println(listOf("aaa", "bb", "c").sortedWith(compareBy({ element -> element.length })))

    println("Sorted ascending: ${numbers.sorted()}")
    println("Sorted descending: ${numbers.sortedDescending()}")
    //And there are sortedBy() and sortedByDescending() methods also available which takes lambda and sort thr elements based on the condition provided

    //There are reversed() and reversedAs() functions for reversing the collection
    //reversed() -> return the new collection with the copies of elements from original collection so the changes in original collection is not reflected in the reversed() collection
    //reversedAs() -> returns the view of the collection so it is lightweight than reversed() but the changes to collection will reflect in the resulting output also

    //Aggregate functions
    val intList = listOf(6, 42, 10, 4)

    println("Count: ${intList.count()}")
    println("Max: ${intList.maxOrNull()}")
    println("Min: ${intList.minOrNull()}")
    println("Average: ${intList.average()}")
    println("Sum: ${intList.sum()}")

    val simpleSum = intList.reduce { sum, element -> sum + element }
    println(simpleSum)

    //To merge two sets, use union and two find common elements use intersect
    val set1 = setOf("One", "Two", "Three")
    println(set1.union(setOf("Five", "Two", "Ten")))
    println(set1.intersect(setOf("Five", "Two", "Ten")))

    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap = map.filter { (key, value) -> key.endsWith("1") && value > 10}
    println(filteredMap)
    val filteredKeysMap = map.filterKeys { it.endsWith("1") }
    val filteredValuesMap = map.filterValues { it < 10 }
    println(filteredKeysMap)
    println(filteredValuesMap)

    println(map + Pair("four", 4))
    println(map + Pair("one", 10))
    println(map + mapOf("five" to 5, "one" to 11))
}