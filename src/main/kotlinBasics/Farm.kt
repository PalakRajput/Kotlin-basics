interface Farm {
    val name: String
    fun speak()
}

interface Eat {
    fun eat(){
        println("Animal is eating")
    }
}

class Chicken(override val name: String, var noOfEggs: Int) : Farm, Eat {
    override fun speak() {
        println("Cluck")
    }
}

class Cow(override val name: String) : Farm, Eat {
    override fun speak() {
        println("Moooo")
    }
}

class Pig(override val name: String, val excitementLevel: Int) : Farm, Eat {
    override fun speak() {
        repeat(excitementLevel) {
            println("Oink")
        }
    }
}

class Farmer(val name: String) {
    fun greet(farmAnimal: Farm) {
        println("Good morning!, ${farmAnimal.name}")
        farmAnimal.speak()
    }
}

fun main() {
    val chicken: Farm = Chicken("Henry", 2)
    val pig: Farm = Pig("Piggy", 4)
    val cow: Farm = Cow("Chloe")

    val farmer: Farmer = Farmer("Harry")
    farmer.greet(chicken)
    farmer.greet(cow)
    farmer.greet(pig)

    val chicken1 = Chicken("Henn", 5)
    chicken1.noOfEggs =
        4//we can access the property here because the chicken1 is not assigned to FarmAnimal variable hence not wearing the interface mask

    farmer.greet(chicken1)
}