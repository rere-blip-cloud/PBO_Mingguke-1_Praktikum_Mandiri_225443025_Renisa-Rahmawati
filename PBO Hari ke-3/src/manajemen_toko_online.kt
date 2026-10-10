// Kelas Induk: Product
open class Product(
    val id: String,
    val nama: String,
    val harga: Double,
    var stok: Int
) {

    open fun hitungDiskon(): Double = 0.0

    fun hitungHargaJual(): Double {
        return harga - hitungDiskon()
    }

    // Tampilan dibuat 1 baris menggunakan |
    open fun tampilkanInfo(): String {
        return "$id | $nama | Rp${"%.0f".format(harga)} | " +
                "Diskon: Rp${"%.0f".format(hitungDiskon())} | " +
                "Harga Jual: Rp${"%.0f".format(hitungHargaJual())} | " +
                "Stok: $stok"
    }

    fun kurangiStok(jumlah: Int): Boolean {
        return if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah
            true
        } else {
            false
        }
    }
}


// Elektronik
class Elektronik(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val garansi: Int,
    val daya: Int
) : Product(id, nama, harga, stok) {

    override fun hitungDiskon(): Double {
        return if (garansi > 12) {
            harga * 0.10
        } else {
            0.0
        }
    }

    override fun tampilkanInfo(): String {
        return super.tampilkanInfo() +
                " | Kategori: Elektronik | Garansi: ${garansi}bln | ${daya}W"
    }
}


// Makanan
class Makanan(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val tanggalKadaluarsa: String,
    val berat: Double,
    val sisaHari: Int
) : Product(id, nama, harga, stok) {

    override fun hitungDiskon(): Double {
        return if (sisaHari < 7) {
            harga * 0.20
        } else {
            0.0
        }
    }

    override fun tampilkanInfo(): String {
        return super.tampilkanInfo() +
                " | Kategori: Makanan | Exp: $tanggalKadaluarsa | ${berat}g"
    }
}


// Pakaian
class Pakaian(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val ukuran: String,
    val bahan: String
) : Product(id, nama, harga, stok) {

    override fun hitungDiskon(): Double {
        return if (ukuran.equals("XL", ignoreCase = true)) {
            harga * 0.15
        } else {
            0.0
        }
    }

    override fun tampilkanInfo(): String {
        return super.tampilkanInfo() +
                " | Kategori: Pakaian | Ukuran: $ukuran | Bahan: $bahan"
    }
}



// KELAS TOKO
class Toko(
    val nama: String
) {

    private val daftarProduk = mutableListOf<Product>()

    fun tambahProduk(product: Product) {
        daftarProduk.add(product)
    }

    fun cariProduk(keyword: String): List<Product> {
        return daftarProduk.filter {
            it.id.contains(keyword, ignoreCase = true) ||
                    it.nama.contains(keyword, ignoreCase = true)
        }
    }

    fun tampilkanSemuaProduk() {
        println("\n========== SEMUA PRODUK ==========")
        println("ID | Nama | Harga | Diskon | Harga Jual | Stok | Kategori | Detail")
        println("--------------------------------------------------------------------------")

        daftarProduk.forEach {
            println(it.tampilkanInfo())
        }
    }

    fun tampilkanProdukByKategori(kategori: String) {
        println("\n========== KATEGORI: ${kategori.uppercase()} ==========")

        val hasil = when (kategori.lowercase()) {
            "elektronik" -> daftarProduk.filter { it is Elektronik }
            "makanan" -> daftarProduk.filter { it is Makanan }
            "pakaian" -> daftarProduk.filter { it is Pakaian }
            else -> emptyList()
        }

        if (hasil.isEmpty()) {
            println("Produk tidak ditemukan.")
        } else {
            hasil.forEach {
                println(it.tampilkanInfo())
            }
        }
    }

    fun tampilkanProdukDiskon() {
        println("\n========== PRODUK YANG SEDANG DISKON ==========")

        val produkDiskon = daftarProduk.filter {
            it.hitungDiskon() > 0
        }

        if (produkDiskon.isEmpty()) {
            println("Tidak ada produk yang sedang diskon.")
        } else {
            produkDiskon.forEach {
                println(it.tampilkanInfo())
            }
        }
    }
}


// main function
fun main() {

    val toko = Toko("Toko Serba Ada")

    // 2 Elektronik
    toko.tambahProduk(
        Elektronik(
            "E001",
            "Laptop ASUS",
            10_000_000.0,
            5,
            24,
            65
        )
    )

    toko.tambahProduk(
        Elektronik(
            "E002",
            "Televisi Samsung",
            6_000_000.0,
            3,
            12,
            120
        )
    )

    // 2 Makanan
    toko.tambahProduk(
        Makanan(
            "M001",
            "Cokelat SilverQueen",
            25_000.0,
            20,
            "10 Oktober 2026",
            100.0,
            5
        )
    )

    toko.tambahProduk(
        Makanan(
            "M002",
            "Biskuit Roma",
            15_000.0,
            15,
            "25 Oktober 2026",
            200.0,
            20
        )
    )

    // 2 Pakaian
    toko.tambahProduk(
        Pakaian(
            "P001",
            "Kaos Oversize",
            100_000.0,
            10,
            "XL",
            "Cotton Combed"
        )
    )

    toko.tambahProduk(
        Pakaian(
            "P002",
            "Kemeja Formal",
            200_000.0,
            8,
            "L",
            "Katun"
        )
    )


    // Menampilkan semua produk
    toko.tampilkanSemuaProduk()


    // Menampilkan produk diskon
    toko.tampilkanProdukDiskon()


    // Pembelian
    println("\n========== PEMBELIAN ==========")

    val laptop = toko.cariProduk("Laptop ASUS").firstOrNull()

    if (laptop != null && laptop.kurangiStok(2)) {
        println("Berhasil membeli 2 Laptop ASUS")
    }

    val cokelat = toko.cariProduk("Cokelat SilverQueen").firstOrNull()

    if (cokelat != null && cokelat.kurangiStok(3)) {
        println("Berhasil membeli 3 Cokelat SilverQueen")
    }

    val kaos = toko.cariProduk("Kaos Oversize").firstOrNull()

    if (kaos != null && kaos.kurangiStok(1)) {
        println("Berhasil membeli 1 Kaos Oversize")
    }


    // Status akhir
    println("\n========== STATUS AKHIR ==========")
    toko.tampilkanSemuaProduk()
}