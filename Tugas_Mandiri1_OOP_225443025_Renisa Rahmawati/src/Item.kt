/**
 * Kelas abstrak induk untuk semua item perpustakaan.
 */
abstract class Item(
    val id: String,
    val title: String,
    val year: Int
) {
    /** Status ketersediaan item untuk dipinjam. */
    var isAvailable: Boolean = true
        private set

    /** Menghitung denda per hari sesuai keterlambatan. */
    abstract fun calculateFinePerDay(): Double

    /** Mengembalikan jenis item ("Buku", "Jurnal", "DVD"). */
    abstract fun getItemType(): String

    /** Mengembalikan batas maksimal hari peminjaman. */
    abstract fun getMaxBorrowDays(): Int

    /** Memproses peminjaman item. */
    fun borrow(): Boolean {
        return if (isAvailable) {
            isAvailable = false
            println("Peminjaman berhasil: '$title' ($id) telah dipinjam.")
            true
        } else {
            println("Peminjaman gagal: '$title' ($id) sedang tidak tersedia.")
            false
        }
    }

    /** Memproses pengembalian item dan menghitung denda. */
    fun returnItem(daysLate: Int = 0): Double {
        if (!isAvailable) {
            isAvailable = true
            val totalFine = if (daysLate > 0) daysLate * calculateFinePerDay() else 0.0

            println("Pengembalian berhasil: '$title' ($id) telah dikembalikan.")
            if (totalFine > 0) {
                println("Keterlambatan: $daysLate hari. Total denda: Rp$totalFine")
            } else {
                println("Pengembalian tepat waktu. Tidak ada denda.")
            }
            return totalFine
        } else {
            println("Peringatan: '$title' ($id) tidak sedang dalam status dipinjam.")
            return 0.0
        }
    }

    /** Menampilkan informasi detail item. */
    open fun displayInfo() {
        println("Jenis Item     : ${getItemType()}")
        println("ID Item        : $id")
        println("Judul          : $title")
        println("Tahun          : $year")
        println("Status         : ${if (isAvailable) "Tersedia" else "Dipinjam"}")
        println("Denda / Hari   : Rp${calculateFinePerDay()}")
        println("Maks Pinjam    : ${getMaxBorrowDays()} hari")
    }
}