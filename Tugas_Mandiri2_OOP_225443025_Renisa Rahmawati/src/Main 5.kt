fun main() {
    // 1. Inisialisasi TransportSystem
    val system = TransportSystem("Go-Transport 2024")

    // 2. Tambah Data Kendaraan
    val car1 = Car("B 1234 XYZ", "Toyota", "Innova", 2021, true, "Bensin", 4)
    val motor1 = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, true, 125, true)
    val truck1 = Truck("E 9012 DEF", "Hino", "Dutro", 2020, true, 5.0, 2)

    system.addVehicle(car1)
    system.addVehicle(motor1)
    system.addVehicle(truck1)

    // Tambah Data Driver
    val driver1 = Driver("D001", "Andi", "08123456789", car1)
    val driver2 = Driver("D002", "Budi", "08129876543", motor1)
    val driver3 = Driver("D003", "Citra", "08125678901", truck1)

    system.addDriver(driver1)
    system.addDriver(driver2)
    system.addDriver(driver3)

    // Tambah Data Customer
    val cust1 = Customer("C001", "Dewi", "08134567890", "dewi@email.com", 100000.0)
    val cust2 = Customer("C002", "Eko", "08135678901", "eko@email.com", 50000.0)
    val cust3 = Customer("C003", "Fani", "08136789012", "fani@email.com", 200000.0)

    system.addCustomer(cust1)
    system.addCustomer(cust2)
    system.addCustomer(cust3)

    // 3. Tampilkan Data Awal
    println("=== DATA AWAL ===")
    system.displayAllVehicles()
    println()
    system.displayAllDrivers()
    println()
    system.displayAllCustomers()
    println()

    // 4, 5, 6. Buat Pesanan
    val order1 = system.createOrder("C001", "D001", "Kampus A", "Mall B", 12.0)
    order1?.startTrip()

    val order2 = system.createOrder("C002", "D002", "Stasiun", "Kantor", 8.0)
    order2?.startTrip()

    val order3 = system.createOrder("C003", "D003", "Gudang", "Pelabuhan", 25.0)
    order3?.startTrip()

    // 7. Tampilkan Semua Order
    println("=== SEMUA ORDER (SEBELUM PEMBAYARAN) ===")
    system.displayAllOrders()
    println()

    // 8. Proses Pembayaran Order 1 (Dewi -> QRIS)
    println("=== PEMBAYARAN ORDER 1 (DEWI - QRIS) ===")
    if (order1 != null) {
        val qrisMethod = QRIS("QRIS1234567890")
        val fareWithFee = order1.getTotalFare() + qrisMethod.getFee(order1.getTotalFare())
        val result1 = system.processPayment(order1.id, qrisMethod, fareWithFee)
        println("Hasil Pembayaran: ${result1.display()}")
    }
    println()

    // 9. Proses Pembayaran Order 2 (Eko -> Tunai kurang, lalu top up & Kredit)
    println("=== PEMBAYARAN ORDER 2 (EKO - PEMBAYARAN GAGAL LALU ULANG) ===")
    if (order2 != null) {
        val cashMethod = Cash()
        val failResult = system.processPayment(order2.id, cashMethod, order2.getTotalFare() - 10000.0)
        println("Percobaan 1 (Nominal Kurang): ${failResult.display()}")

        println("Eko Melakukan Top-Up Saldo...")
        cust2.topUp(50000.0)

        val creditCardMethod = CreditCard("1234567890123456")
        val fareWithFee = order2.getTotalFare() + creditCardMethod.getFee(order2.getTotalFare())
        val successResult = system.processPayment(order2.id, creditCardMethod, fareWithFee)
        println("Percobaan 2 (Kartu Kredit Valid): ${successResult.display()}")
    }
    println()

    // 10. Selesaikan Order 1
    if (order1 != null) {
        system.completeOrder(order1.id)
    }

    // 11. Batalkan Order 3 (Fani -> Hujan deras)
    if (order3 != null) {
        system.cancelOrder(order3.id, "Hujan deras")
    }

    // 12. Tampilkan Status Akhir
    println("=== STATUS AKHIR PERUBAHAN ORDER ===")
    system.displayAllOrders()
    println()

    // 13. Tampilkan Laporan Pendapatan
    println("=== LAPORAN PENDAPATAN ===")
    system.displayRevenueReport()
    println()

    // 14. Demonstrasi Polimorfisme
    println("=== DEMONSTRASI POLIMORFISME (TARIF 15 KM) ===")
    for (v in system.vehicles) {
        println("Kendaraan [${v.getType()} - ${v.plateNumber}] Tarif 15km: Rp${v.calculateFare(15.0)}")
    }
    println()

    // 15. Demonstrasi Smart Casting
    println("=== DEMONSTRASI SMART CASTING DRIVER D001 ===")
    val d001 = system.findDriver("D001")
    if (d001 != null) {
        val vehicle = d001.vehicle
        if (vehicle is Car) {
            println("Driver D001 (${d001.name}) menggunakan Mobil dengan Bahan Bakar: ${vehicle.fuelType}")
        }
    }
    println()

    // 16. Demonstrasi Sealed Class
    println("=== DEMONSTRASI SEALED CLASS OrderStatus ===")
    val allStatuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.OnGoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Driver Tidak Merespon")
    )
    for (st in allStatuses) {
        val statusText = when (st) {
            is OrderStatus.Waiting -> "Waiting: ${st.display()}"
            is OrderStatus.OnGoing -> "OnGoing: ${st.display()}"
            is OrderStatus.Completed -> "Completed: ${st.display()}"
            is OrderStatus.Cancelled -> "Cancelled: ${st.display()}"
        }
        println(statusText)
    }
}