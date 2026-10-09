// KELAS BUKU
class Buku(
    val judul: String,
    val penulis: String,
    val isbn: String,
    val tahunTerbit: Int
) {
    var isDipinjam: Boolean = false
        private set
    var peminjam: String? = null
        private set

    // Meminjam buku jika tersedia
    fun pinjam(namaPeminjam: String): Boolean {
        return if (!isDipinjam) {
            isDipinjam = true
            peminjam = namaPeminjam
            println("Berhasil meminjam buku \"$judul\" untuk $namaPeminjam.")
            true
        } else {
            println("Gagal: Buku \"$judul\" sedang dipinjam oleh $peminjam.")
            false
        }
    }

    // Mengembalikan buku
    fun kembalikan(): Boolean {
        return if (isDipinjam) {
            println("Buku \"$judul\" berhasil dikembalikan.")
            isDipinjam = false
            peminjam = null
            true
        } else {
            println("Gagal: Buku \"$judul\" belum dipinjam.")
            false
        }
    }

    // Ketersediaan
    fun isTersedia(): Boolean = !isDipinjam

    // Menampilkan informasi buku
    fun tampilkanInfo(): String {
        val status = if (isDipinjam) "Dipinjam oleh $peminjam" else "Tersedia"
        return """
            Judul        : $judul
            Penulis      : $penulis
            ISBN         : $isbn
            Tahun Terbit : $tahunTerbit
            Status       : $status
        """.trimIndent()
    }
}

// KELAS PERPUSTAKAAN
class Perpustakaan(val nama: String) {
    private val daftarBuku: MutableList<Buku> = mutableListOf()

    // Menambah buku ke daftar
    fun tambahBuku(buku: Buku) {
        daftarBuku.add(buku)
    }

    // Mencari buku berdasarkan judul/penulis
    fun cariBuku(keyword: String): List<Buku> {
        return daftarBuku.filter {
            it.judul.contains(keyword, ignoreCase = true) || it.penulis.contains(keyword, ignoreCase = true)
        }
    }

    // Meminjam buku berdasarkan ISBN
    fun pinjamBuku(isbn: String, peminjam: String): Boolean {
        val buku = daftarBuku.find { it.isbn == isbn }
        return buku?.pinjam(peminjam) ?: run {
            println("Buku dengan ISBN $isbn tidak ditemukan")
            false
        }
    }

    // Mengembalikan buku berdasarkan ISBN
    fun kembalikanBuku(isbn: String): Boolean {
        val buku = daftarBuku.find { it.isbn == isbn }
        return buku?.kembalikan() ?: run {
            println("Buku dengan ISBN $isbn tidak ditemukan")
            false
        }
    }

    // Menampilkan semua buku
    fun tampilkanSemuaBuku() {
        println("\n=== Buku di $nama ===")
        daftarBuku.forEach {
            println(it.tampilkanInfo())
            println("-----------------------------")
        }
    }

    // Menampilkan buku yang tersedia
    fun tampilkanBukuTersedia() {
        println("\n=== BUKU TERSEDIA ===")
        daftarBuku.filter { it.isTersedia() }.forEach {
            println(it.tampilkanInfo())
            println("-----------------------------")
        }
    }

    // Menampilkan buku yang sedang dipinjam
    fun tampilkanBukuDipinjam() {
        println("\n=== BUKU DIPINJAM ===")
        daftarBuku.filter { it.isDipinjam }.forEach {
            println(it.tampilkanInfo())
            println("-----------------------------")
        }
    }
}

// FUNGSI MAIN
fun main() {
    val perpustakaan = Perpustakaan("Perpustakaan Kampus")

    // Tambah minimal 5 buku
    perpustakaan.tambahBuku(Buku("Dinar", "Martin", "KB01", 2023))
    perpustakaan.tambahBuku(Buku("Rahasia Pensiun Dini", "Edwars", "KB02", 2021))
    perpustakaan.tambahBuku(Buku("Tabungan Pensiun", "Lala", "KB03", 2022))
    perpustakaan.tambahBuku(Buku("Pemrograman Web", "Ali", "KB04", 2020))
    perpustakaan.tambahBuku(Buku("Jaringan Komputer", "Rara", "KB05", 2019))

    // Tampilkan semua buku
    perpustakaan.tampilkanSemuaBuku()

    // Peminjaman oleh mahasiswa
    println("\n--- Proses Peminjaman ---")
    perpustakaan.pinjamBuku("KB01", "Ahmad")
    perpustakaan.pinjamBuku("KB03", "Rian")
    perpustakaan.pinjamBuku("KB01", "Dedi")

    // Tampilkan buku tersedia dan dipinjam
    perpustakaan.tampilkanBukuTersedia()
    perpustakaan.tampilkanBukuDipinjam()

    // Pengembalian buku
    println("\n--- Proses Pengembalian ---")
    perpustakaan.kembalikanBuku("KB01")

    // Status akhir
    perpustakaan.tampilkanSemuaBuku()
}