// Kelas Induk: Product
open class Product(
    val id: String,
    val name: String,
    val price: Double
) {
    open fun getDiscountedPrice(): Double = price

    fun tampilkanInfo() {
        println("ID             : $id")
        println("Nama Produk    : $name")
        println("Harga Normal   : Rp $price")
        println("Harga Diskon   : Rp ${getDiscountedPrice()}")
        println("----------------------------------")
    }
}

// Subclass: ElectronicProduct
class ElectronicProduct(
    id: String,
    name: String,
    price: Double,
    val warrantyMonths: Int
) : Product(id, name, price) {
    override fun getDiscountedPrice(): Double {
        return if (warrantyMonths > 12) price * 0.90 else price
    }
}

// Subclass: FoodProduct
class FoodProduct(
    id: String,
    name: String,
    price: Double,
    val expiryDate: String,
    val isNearExpiry: Boolean
) : Product(id, name, price) {
    override fun getDiscountedPrice(): Double {
        return if (isNearExpiry) price * 0.80 else price
    }
}

// Subclass: ClothingProduct
class ClothingProduct(
    id: String,
    name: String,
    price: Double,
    val size: String,
    val material: String
) : Product(id, name, price) {
    override fun getDiscountedPrice(): Double {
        return if (size.equals("XL", ignoreCase = true)) price * 0.85 else price
    }
}

// Fungsi Utama
fun main() {
    val earphone = ElectronicProduct("E01", "Earphone", 150000.0, 24)
    val susu = FoodProduct("F01", "Susu UHT", 20000.0, "2026-08-20", true)
    val jaket = ClothingProduct("C01", "Jaket Parasut", 300000.0, "XL", "Kanvas")

    println("===== DAFTAR PRODUK & DISKON =====")
    earphone.tampilkanInfo()
    susu.tampilkanInfo()
    jaket.tampilkanInfo()
}
