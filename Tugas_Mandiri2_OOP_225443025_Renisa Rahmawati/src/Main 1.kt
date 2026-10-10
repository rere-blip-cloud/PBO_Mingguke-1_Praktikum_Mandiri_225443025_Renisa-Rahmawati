fun main(){
    val vehicle = Vehicle("B 1234 ABC", "Totoya", "Avanza", 2020)
    val driver = Driver("71", "Surya", "085541251627", vehicle)
    val customer = Customer("84", "Ali", "091116299162", "123@gmail.com", 50000.0)

    println("-----Info Vehicle-----")
    vehicle.displayInfo()
    println()

    println("-----Info Driver-----")
    driver.displayInfo()
    println()

    println("-----Info Customer-----")
    customer.displayInfo()
    println()

    val fare = vehicle.calculateFare(10.0)
    println("Tarif (10.0 km) : Rp$fare\n")

    println("-----Top Up Saldo-----")
    customer.topUp(100000.0)
    println()

    val isAccepted = driver.acceptOrder()
    println("Apakah pesanan diterima? $isAccepted")
}
