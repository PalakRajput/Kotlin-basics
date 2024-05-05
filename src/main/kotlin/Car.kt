class Car {
    var brand = ""
    var model = ""
}

fun main(){
    val car = Car()
    car.brand = "Maruti Suzuki"
    car.model = "Swift"

    println(car.brand + " " + car.model)
}