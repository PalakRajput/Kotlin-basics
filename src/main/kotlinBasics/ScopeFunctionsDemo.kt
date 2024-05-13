package kotlinBasics

//Kotlin provides 5 scope functions: with, let, apply, run, also
//It makes our code concise and eay to read and these are higher order functions.
//Scope functions can be differentiated using the return value(context object or lambda result) and the way they refer to the context object(using this or it keyword)

class DisneyCharacter {
    var name: String = "Donald duck"
    var age: Int = 90
}

fun main() {
    val person = DisneyCharacter()
    person.age = 101
    person.name = "Mickey mouse"

    //with -> returns lambda result and refer to context object using this keyword.
    //Calls the specified function block with the given receiver as its receiver and returns its result.
    with(person) {//this=person
        println(name)
        println(this.age)
    }

    val ageAfter5Years: Int = with(person) {
        age + 5
    }
    println(ageAfter5Years)

    //apply returns the context object and refer to context object using this
    //Calls the specified function block with this value as its receiver and returns this value.
    val character = DisneyCharacter().apply {//this=DisneyCharacter()
        //this keyword is optional
        this.age = 101
        this.name = "Mickey mouse"
    }

    //also refer to context object using it and returns the context object.
    val myMutableList: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5)
    //suppose we want to first print the list then add an element and again print it, remove an element and again print it so we can use also function in this case.
    //Calls the specified function block with this value as its argument and returns this value.
    val dupMyMutableList = myMutableList.also {//it=mutableList
        println(it)
        it.add(10)
        println(it)
        it.removeAt(0)
        println(it)
    }
    println(myMutableList == dupMyMutableList)

    character.also {
        it.name = "Snow white"
    }

    //let returns lambda result and refers to context object using it. let can be used to prevent NPE along with safe call operator(?.)
    //Calls the specified function block with this value as its argument and returns its result.
    val string: String? = null
    val length: Int? = string?.let {
//        println(it!!.reversed())
        println(it.uppercase())
        println(it.length)
        it.length
    }
    println(length)

    //run returns lambda and refer to context object using this keyword. combination of with and let function, used to avoid NPE

    val result : String? = person?.run {
        name = "Tinker bell"
        age = 56
        "Tinker bell is 56 years old."
    }
    println(result)

}


