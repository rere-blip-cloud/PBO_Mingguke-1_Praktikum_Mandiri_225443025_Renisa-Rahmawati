fun main() {
    val vehicle = Vehicle("B 1234 ABC", "Totoya", "Avanza", 2020)
    val driver = Driver("71", "Surya", "085541251627", vehicle)
    val customer = Customer("84", "Ali", "091116299162", "123@gmail.com", 50000.0)

    val order = Order("ORD001", customer, driver, "Bandung", "Jakarta", 15.0)

    println("-----Detail Order-----")
    order.displayOrder()
    println()

    order.startTrip()
    order.completeTrip()

    val cancelSuccess = order.cancelTrip("Ingin ganti jadwal")
    println("Apakah pembatalan berhasil? $cancelSuccess\n")

    val payment = Payment(order)
    payment.setMethod("QRIS")

    val isPaidSuccess = payment.processPayment(35000.0)
    println("Apakah pembayaran berhasil? $isPaidSuccess\n")

    println("-----Detail Pembayaran-----")
    payment.displayPayment()
    println()

    // Demonstrasi Enkapsulasi
    order._status // ERROR: Cannot access private property
}