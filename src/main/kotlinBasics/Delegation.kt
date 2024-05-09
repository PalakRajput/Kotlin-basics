package kotlinBasics

/**
 * Sometimes an object is fully capable of fulfilling the request on its own, and sometimes it might need to hand off the request to other object.
 * Delegating is an alternative to inheritance.
 * Use inheritance only when you are sure that your class and the class you inherit from share all the common traits.
 * If you think your class doesn’t need some variables or methods from its possible parent,
 * and “is a” relationship doesn’t apply, it’s better to use delegation.
 */
enum class Entree {
    TOSSED_SALAD,
    SALMON_ON_RICE,
    PLAIN_DAL_RICE,
    DAL
}

enum class Beverage {
    WATER,
    SODA,
    PEACH_ICED_TEA,
    TEA_LEMONADE
}

interface BarStaff {
    fun prepareBeverage(name: String): Beverage?
}

open class Bartender : BarStaff {
    override fun prepareBeverage(name: String): Beverage? = when (name) {
        "Water" -> Beverage.WATER
        "Soda" -> Beverage.SODA
        "Peach Tea" -> Beverage.PEACH_ICED_TEA
        "Tea-Lemonade" -> Beverage.TEA_LEMONADE
        else -> null
    }
}

interface KitchenStaff {
    fun prepareEntree(name: String): Entree?
    //Again for below methods we will have to override them in Chef and Waiter class and Waiter class will just delegate the request to Chef class
//    fun prepareAppetizer(name: String): Appetizer?
//    fun prepareDessert(name: String): Dessert?
//    fun receiveCompliment(message: String)
}

open class Chef : KitchenStaff {
    override fun prepareEntree(name: String): Entree? = when (name) {
        "Tossed Salad" -> Entree.TOSSED_SALAD
        "Salmon on Rice" -> Entree.SALMON_ON_RICE
        "Plain Dal Rice" -> Entree.PLAIN_DAL_RICE
        "Dal" -> Entree.DAL
        else -> null
    }
}

/**
 * If multiple objects have same methodName, returnType then the Waiter class will have to override it and manually delegate the request to the specific object(method like receiveCompliments as it can be received by Chef, Bartender or Waiter itself
 * If we want to handle few operations by ourselves and delegate the rest to the specific object then also we can override the specific function and do the operation in Waiter class in this case.
 */
//Waiter implements KitchenStaff by delegating to chef.
class Waiter(private val chef: Chef, private val barStaff: Bartender) : KitchenStaff by chef, BarStaff by barStaff {
    //Now after adding Interface by concrete class for both Bartender and Chef and we don't need to override these methods in this class and manually delegate the work to those classes
    fun acceptPayment(money: Int) = println("Thank you for paying for your meal")

}

//Inheritance example -> Only 1 class can be extended and now Waiter2 is child of Chef but in case of delegation Waiter and Chef are at same level
class Waiter2() : Chef() {
    //Now after adding Interface by concrete class for both Bartender and Chef and we don't need to override these methods in this class and manually delegate the work to those classes
    fun acceptPayment(money: Int) = println("Thank you for paying for your meal")

}

fun main() {
    val waiter = Waiter(Chef(), Bartender())

    println(waiter.prepareBeverage("Soda"))
    println(waiter.prepareEntree("Salmon on Rice"))

    val waiter2 = Waiter2()
    println(waiter2.prepareEntree("Salmon on Rice"))
}