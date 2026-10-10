fun main() {
    // Membuat objek Car, Motorcycle, dan Truck
    val car = Car("B 1234 ABC", "Toyota", "Avanza", 2020, true, "Bensin", 4)
    val motorcycle = Motorcycle("D 5678 XYZ", "Honda", "Vario", 2022, true, 150, true)
    val truck = Truck("F 9012 DEF", "Mitsubishi", "Canter", 2018, true, 5.0, 2)

    // Menampilkan info masing-masing kendaraan
    println("=== INFORMASI KENDARAAN ===")
    println("----- Mobil -----")
    car.displayInfo()
    println()

    println("----- Motor -----")
    motorcycle.displayInfo()
    println()

    println("----- Truk -----")
    truck.displayInfo()
    println()

    // Menghitung tarif untuk jarak 20 km untuk masing-masing kendaraan
    println("=== HITUNG TARIF PERJALANAN (20.0 km) ===")
    println("Tarif Mobil : Rp${car.calculateFare(20.0)}")
    println("Tarif Motor : Rp${motorcycle.calculateFare(20.0)}")
    println("Tarif Truk  : Rp${truck.calculateFare(20.0)}")
    println()

    // 3 driver dengan 3 kendaraan berbeda
    val driver1 = Driver("D01", "Martin", "0851111111", car)
    val driver2 = Driver("D02", "Sean", "081222222", motorcycle)
    val driver3 = Driver("D03", "James", "089333333", truck)

    // Menampilkan info driver
    println("=== INFORMASI DRIVER DENGAN KENDARAAN BERBEDA ===")
    println("----- Driver 1 -----")
    driver1.displayInfo()
    println()

    println("----- Driver 2 -----")
    driver2.displayInfo()
    println()

    println("----- Driver 3 -----")
    driver3.displayInfo()
    println()
}