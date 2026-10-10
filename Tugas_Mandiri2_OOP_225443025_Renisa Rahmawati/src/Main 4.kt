fun main() {
    // 1. Buat List<Vehicle> berisi Car, Motorcycle, Truck (Polymorphic references)
    val vehicles: List<Vehicle> = listOf(
        Car("B 1234 ABC", "Toyota", "Avanza", 2020, true, "Bensin", 4),
        Motorcycle("D 5678 XYZ", "Honda", "Vario", 2022, true, 150, true),
        Truck("F 9012 DEF", "Mitsubishi", "Canter", 2018, true, 5.0, 2)
    )

    println("=== POLYMORPHISM DAN SMART CASTING ===")
    for (v in vehicles) {
        println("Tipe      : ${v.getType()}")
        println("Tarif 10km: Rp${v.calculateFare(10.0)}")

        // Smart casting menggunakan when + is
        when (v) {
            is Car -> println("Atribut   : Bahan Bakar ${v.fuelType}, ${v.numberOfDoors} Pintu")
            is Motorcycle -> println("Atribut   : Mesin ${v.engineCapacity}cc, Helm: ${if (v.hasHelmet) "Ya" else "Tidak"}")
            is Truck -> println("Atribut   : Kapasitas ${v.loadCapacity} ton, ${v.numberOfAxles} Sumbu Roda")
        }
        println("--------------------------------------------------")
    }
    println()

    // 2. Buat List<PaymentMethod> berisi CreditCard, QRIS, Cash
    val paymentMethods: List<PaymentMethod> = listOf(
        CreditCard("1234567890123456"),
        QRIS("QRIS123456789"),
        Cash()
    )

    println("=== PROSES PEMBAYARAN (SEALED CLASS PaymentResult) ===")
    val amountToPay = 100000.0
    for (method in paymentMethods) {
        val result = method.processPayment(amountToPay)

        // When ekshaustif untuk PaymentResult
        val statusText = when (result) {
            is PaymentResult.Success -> "SUKSES -> ${result.display()}"
            is PaymentResult.Failed -> "GAGAL -> ${result.display()}"
            is PaymentResult.Pending -> "PENDING -> ${result.display()}"
        }
        println("Metode [${method.name}]: $statusText (Biaya Tambahan: Rp${method.getFee(amountToPay)})")
    }
    println()

    // 3. Simulasi berbagai status OrderStatus
    println("=== SEALED CLASS OrderStatus ===")
    val statuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.OnGoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Driver Tidak Merespon")
    )

    for (st in statuses) {
        // When ekshaustif untuk OrderStatus
        val info = when (st) {
            is OrderStatus.Waiting -> "Status: ${st.display()} (Selesai: ${st.isFinal()})"
            is OrderStatus.OnGoing -> "Status: ${st.display()} (Selesai: ${st.isFinal()})"
            is OrderStatus.Completed -> "Status: ${st.display()} (Selesai: ${st.isFinal()})"
            is OrderStatus.Cancelled -> "Status: ${st.display()} (Selesai: ${st.isFinal()})"
        }
        println(info)
    }
    println()

    // 4. Demonstrasi Safe Casting (as?)
    println("=== DEMONSTRASI SAFE CASTING (as?) ===")

    // Kondisi 1: Safe Casting Berhasil (Objek asli adalah Car)
    val vehicle1: Vehicle = Car("B 9999 DEF", "Honda", "Civic", 2023, true, "Bensin", 4)
    val safeCar1: Car? = vehicle1 as? Car

    if (safeCar1 != null) {
        println("Casting Berhasil! Mobil ini memiliki ${safeCar1.numberOfDoors} pintu dan bahan bakar ${safeCar1.fuelType}.")
    } else {
        println("Casting Gagal! Objek bukan tipe Car.")
    }

    // Kondisi 2: Safe Casting Gagal (Objek asli adalah Motorcycle, dicoba cast ke Car)
    val vehicle2: Vehicle = Motorcycle("D 1111 EFG", "Yamaha", "NMAX", 2023, true, 155, true)
    val safeCar2: Car? = vehicle2 as? Car

    if (safeCar2 != null) {
        println("Casting Berhasil! Mobil ini memiliki ${safeCar2.numberOfDoors} pintu dan bahan bakar ${safeCar2.fuelType}.")
    } else {
        println("Casting Gagal! Objek bernilai null karena tipe aslinya bukan Car.")
    }
}