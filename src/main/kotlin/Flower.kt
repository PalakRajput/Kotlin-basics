import java.util.concurrent.CompletableFuture

//If we don't specify var keyword, the properties can't be accessed
/**
 * KDoc comment
 */
class Flower(var name: String, var color: String) {
    //Class/member function
    fun printValues(){
        println("Flower properties: $name $color")
    }
}

fun main(){
    val f1 = Flower("Rose", "Red")
    val f2 = Flower("Sunflower", "Yellow")

//    println("Flower1: ${f1.name} ${f1.color}")
//    println("Flower2: ${f2.name} ${f2.color}")

    f1.printValues()
    f2.printValues()
}