class Order(
    val id: String,
    val customer: Customer,
    val driver: Driver,
    val pickupLocation: String,
    val destination: String,
    val distanceKm: Double,
    var status: OrderStatus = OrderStatus.Waiting
) {
    private var _totalFare: Double = 0.0

    init {
        _totalFare = driver.vehicle.calculateFare(distanceKm)
    }

    fun getTotalFare(): Double {
        return _totalFare
    }

    fun startTrip(): Boolean {
        return if (status is OrderStatus.Waiting) {
            status = OrderStatus.OnGoing
            true
        } else {
            false
        }
    }

    fun completeTrip(): Boolean {
        return if (status is OrderStatus.OnGoing) {
            status = OrderStatus.Completed
            true
        } else {
            false
        }
    }

    fun cancelTrip(reason: String): Boolean {
        return if (!status.isFinal()) {
            status = OrderStatus.Cancelled(reason)
            true
        } else {
            false
        }
    }

    fun displayOrder() {
        println("ID Order     : $id")
        println("Pelanggan    : ${customer.name}")
        println("Driver       : ${driver.name}")
        println("Jemput       : $pickupLocation")
        println("Tujuan       : $destination")
        println("Jarak        : $distanceKm km")
        println("Total Tarif  : Rp$_totalFare")
        println("Status       : ${status.display()}")
    }
}