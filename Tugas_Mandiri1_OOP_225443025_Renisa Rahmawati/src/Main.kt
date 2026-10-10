/**
 * Fungsi utama untuk mendemonstrasikan seluruh fitur sistem perpustakaan
 * sesuai dengan skenario wajib D.1 dan D.2.
 */
fun main() {
    // 1. Inisialisasi Sistem Perpustakaan
    println("=== INISIALISASI PERPUSTAKAAN ===")
    val library = Library("Perpustakaan Kampus")
    println("Sistem '${library.name}' berhasil dibuat.\n")

    // 2. Tambah Item Minimal 6 item (2 dari tiap jenis)
    println("=== MENAMBAHKAN ITEM PERPUSTAKAAN ===")
    val book1 = Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming")
    val book2 = Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education")
    val journal1 = Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2)
    val journal2 = Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1)
    val dvd1 = DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi")
    val dvd2 = DVD("D002", "The Matrix", 1999, "Wachowski", 136, "Action")

    library.addItems(book1, book2, journal1, journal2, dvd1, dvd2)
    println()

    // 3. Registrasi Anggota (Minimal 3 anggota)
    println("=== REGISTRASI ANGGOTA ===")
    library.registerMember("M001", "Ahmad Fauzi", "ahmad@email.com", "08123456789")
    library.registerMember("M002", "Dewi Lestari", "dewi@email.com", "08129876543")
    library.registerMember("M003", "Rizky Pratama", "rizky@email.com", "08125678901")
    println()

    // 4. Tampilkan Semua Item
    println("=== SEMUA ITEM ===")
    library.displayAllItems()
    println()

    // 5. Peminjaman (Skenario A)
    println("=== PEMINJAMAN ITEM ===")
    println("--> Ahmad (M001) meminjam 'Pemrograman Kotlin' (B001):")
    library.borrowItem("M001", "B001")

    println("\n--> Ahmad (M001) meminjam 'Inception' (D001):")
    library.borrowItem("M001", "D001")

    println("\n--> Dewi (M002) meminjam 'Jurnal Teknologi Informasi' (J001):")
    library.borrowItem("M002", "J001")

    println("\n--> Rizky (M003) meminjam 'Dasar-Dasar OOP' (B002):")
    library.borrowItem("M003", "B002")
    println()

    // 6. Tampilkan Item Tersedia
    println("=== ITEM TERSEDIA ===")
    library.displayAvailableItems()
    println()

    // 7. Tampilkan Transaksi Anggota (Ahmad & Dewi)
    println("=== TRANSAKSI ANGGOTA ===")
    val ahmad = library.findMember("M001")
    val dewi = library.findMember("M002")

    ahmad?.displayTransactions()
    dewi?.displayTransactions()

    // 8. Pengembalian (Skenario B)
    println("=== PENGEMBALIAN ITEM ===")
    println("--> Ahmad mengembalikan 'Pemrograman Kotlin' (B001) tepat waktu (daysLate = 0):")
    library.returnItem("M001", "B001", daysLate = 0)

    println("\n--> Dewi mengembalikan 'Jurnal Teknologi Informasi' (J001) terlambat 3 hari (daysLate = 3):")
    library.returnItem("M002", "J001", daysLate = 3)
    println()

    // 9. Tampilkan Transaksi Setelah Pengembalian
    println("=== TRANSAKSI SETELAH PENGEMBALIAN ===")
    ahmad?.displayTransactions()
    dewi?.displayTransactions()

    // 10. Demonstrasi Polimorfisme
    println("=== DEMONSTRASI POLIMORFISME ===")
    val sampleItems: List<Item> = listOf(book1, journal1, dvd1)
    for (item in sampleItems) {
        println("${item.getItemType()} - Denda/hari: Rp${item.calculateFinePerDay()}")
    }
    println()

    // 11. Demonstrasi Sealed Class
    println("=== DEMONSTRASI SEALED CLASS ===")
    val statuses: List<TransactionStatus> = listOf(
        TransactionStatus.Borrowed,
        TransactionStatus.Returned,
        TransactionStatus.Overdue(5),
        TransactionStatus.Cancelled
    )

    for (status in statuses) {
        val result = when (status) {
            is TransactionStatus.Borrowed -> "Status: ${status.display()}"
            is TransactionStatus.Returned -> "Status: ${status.display()}"
            is TransactionStatus.Overdue -> "Status: ${status.display()}"
            is TransactionStatus.Cancelled -> "Status: ${status.display()}"
        }
        println(result)
    }
    println()

    // 12. Demonstrasi Smart Casting
    println("=== DEMONSTRASI SMART CASTING ===")
    val targetItem: Item? = library.findItem("B001")

    if (targetItem != null) {
        // Pengecekan tipe menggunakan operator is (Smart Casting)
        when (targetItem) {
            is Book -> println("Item B001 adalah Buku (Penulis: ${targetItem.author})")
            is Journal -> println("Item B001 adalah Jurnal (Penerbit: ${targetItem.publisher})")
            is DVD -> println("Item B001 adalah DVD (Sutradara: ${targetItem.director})")
        }

        // Mencoba safe casting (as?) ke DVD
        val castToDvd: DVD? = targetItem as? DVD
        if (castToDvd != null) {
            println("Casting B001 ke DVD Berhasil: ${castToDvd.director}")
        } else {
            println("Casting B001 ke DVD Gagal (Hasil bernilai null karena B001 bukan DVD).")
        }
    }
    println()

    // Demonstrasi Enkapsulasi
    println("=== DEMONSTRASI ENKAPSULASI ===")
    println("Enkapsulasi melindungi data internal objek dari perubahan tidak sah:")
    println("- Properti 'isAvailable' pada Item memiliki 'private set', sehingga nilainya tidak bisa diubah langsung dari luar (harus melalui metode borrow() / returnItem()).")
    println("- Properti 'email' dan 'phone' pada Member bersifat 'private val', sehingga tidak dapat diakses langsung tanpa getter (getEmail() / getPhone()).")
    println()

    // 14. Tampilkan Laporan Akhir
    println("=== LAPORAN AKHIR PERPUSTAKAAN ===")
    library.displayReport()
}