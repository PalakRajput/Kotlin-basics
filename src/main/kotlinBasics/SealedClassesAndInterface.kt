package kotlinBasics

import kotlin.math.pow
import kotlin.random.Random

//Sealed classes and interface can be used to make when statement exhaustive
sealed interface Request {
    val id : Int
}

class SR(override val id: Int, val count : Int) : Request{

}
class OR(override val id: Int, val count : Int) : Request{

}
class RR(override val id: Int, val reason: String, val count: Int) : Request{

}

object HandleRequest {
    fun handleRequest(request: Request){
        when(request){
            is SR -> println("Handling service request")
            is OR -> println("Handling Order request")
            is RR -> println("Handling Refund request")
        }
    }
}

//The sealed classes are abstract
//Earlier the subclasses of sealed class should be defined inside the class body
//Now after 1.5, the subclasses of sealed class should be present in same package and code base.
sealed class RequestClass{
    val id : Int = Random.nextInt()
}

class OrderReq(val count: Int) : RequestClass(){

}
class ServiceReq(val count: Int) : RequestClass(){

}
class RefundReq(val count: Int, val reason: String) : RequestClass(){

}

fun main(){
    val sr = SR(101, 1)
    val or = OR(102, 3)
    val rr = RR(103, "NA", 2)
    HandleRequest.handleRequest(sr)
    HandleRequest.handleRequest(or)
    HandleRequest.handleRequest(rr)
    val shape1: Shape = RectangleSh(12, 15,"Rectangle")
    val shape2: Shape = SquareSh(6, "Square")
    val shape3 = CircleSh(5.0, "Circle")
    shape1.area()
    shape3.area()
}

sealed class Shape(val type: String) {
    abstract fun area()
}

class RectangleSh(val height: Int, val width: Int, type: String) : Shape(type) {
    override fun area() {
        println("Area of rectangle is: ${height * width}")
    }

}
class SquareSh(val edge: Int, type: String) : Shape(type) {
    override fun area() {
        println("Area of square is: ${edge * edge}")
    }
}
class CircleSh(val radius: Double, type: String) : Shape(type) {
    override fun area() {
        println("Area of circle is: ${3.14 * radius.pow(2.0)}")
    }
}