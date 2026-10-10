class TransportSystem(val name: String) {
    val vehicles: MutableList<Vehicle> = mutableListOf()
    val drivers: MutableList<Driver> = mutableListOf()
    val customers: MutableList<Customer> = mutableListOf()
    val orders: MutableList<Order> = mutableListOf()
    val payments: MutableList<Payment> = mutableListOf()

    // Metode Manajemen
    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    fun addDriver(driver: Driver) {
        drivers.add(driver)
    }

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    fun findVehicle(plateNumber: String): Vehicle? {
        return vehicles.find { it.plateNumber == plateNumber }
    }

    fun findDriver(id: String): Driver? {
        return drivers.find { it.id == id }
    }

    fun findCustomer(id: String): Customer? {
        return customers.find { it.id == id }
    }

    // Metode Operasi
    fun createOrder(
        customerId: String,
        driverId: String,
        pickup: String,
        dest: String,
        distance: Double
    ): Order? {
        val customer = findCustomer(customerId)
        val driver = findDriver(driverId)

        return if (customer != null && driver != null) {
            val orderId = "ORD00${orders.size + 1}"
            val order = Order(orderId, customer, driver, pickup, dest, distance)
            orders.add(order)
            order
        } else {
            println("Gagal membuat order: Customer atau Driver tidak ditemukan.")
            null
        }
    }

    fun processPayment(
        orderId: String,
        method: PaymentMethod,
        paidAmount: Double
    ): PaymentResult {
        val order = orders.find { it.id == orderId }
        if (order == null) {
            return PaymentResult.Failed("Order tidak ditemukan", 404)
        }

        val payment = Payment(order, method)
        payments.add(payment)

        val totalFareWithFee = order.getTotalFare() + method.getFee(order.getTotalFare())
        return if (paidAmount >= totalFareWithFee) {
            val result = payment.processPayment()
            result
        } else {
            PaymentResult.Failed("Nominal pembayaran kurang (Tagihan + Biaya: Rp$totalFareWithFee)", 400)
        }
    }

    fun completeOrder(orderId: String): Boolean {
        val order = orders.find { it.id == orderId }
        return order?.completeTrip() ?: false
    }

    fun cancelOrder(orderId: String, reason: String): Boolean {
        val order = orders.find { it.id == orderId }
        return order?.cancelTrip(reason) ?: false
    }

    // Metode Laporan
    fun displayAllVehicles() {
        println("=== DAFTAR KENDARAAN ===")
        vehicles.forEach {
            it.displayInfo()
            println("-----------------------------------")
        }
    }

    fun displayAllDrivers() {
        println("=== DAFTAR DRIVER ===")
        drivers.forEach {
            it.displayInfo()
            println("-----------------------------------")
        }
    }

    fun displayAllCustomers() {
        println("=== DAFTAR CUSTOMER ===")
        customers.forEach {
            it.displayInfo()
            println("-----------------------------------")
        }
    }

    fun displayAllOrders() {
        println("=== DAFTAR ORDER ===")
        orders.forEach {
            it.displayOrder()
            println("-----------------------------------")
        }
    }

    fun displayRevenueReport() {
        val totalRevenue = orders
            .filter { it.status is OrderStatus.Completed }
            .sumOf { it.getTotalFare() }

        println("=== LAPORAN PENDAPATAN ===")
        println("Nama Sistem      : $name")
        println("Order Selesai    : ${orders.count { it.status is OrderStatus.Completed }}")
        println("Total Pendapatan : Rp$totalRevenue")
    }
}