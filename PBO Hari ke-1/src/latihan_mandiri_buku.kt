class Buku(
    val judul: String,
    val penulis: String,
    val tahunTerbit: Int,
    var isDipinjam: Boolean = false,
    var peminjam: String? = null
) {
    // Metode untuk meminjam buku
    fun pinjam(namaPeminjam: String) {
        if (isDipinjam) {
            println("Buku \"$judul\" sedang dipinjam oleh $peminjam.\n")
        } else {
            isDipinjam = true
            peminjam = namaPeminjam
            println("Berhasil meminjam buku \"$judul\" atas nama $namaPeminjam.\n")
        }
    }

    // Metode untuk mengembalikan buku
    fun kembalikan() {
        if (!isDipinjam) {
            println("Buku \"$judul\" belum dipinjam.\n")
        } else {
            println("Buku \"$judul\" telah dikembalikan oleh $peminjam.\n")
            isDipinjam = false
            peminjam = null
        }
    }

    // Metode untuk menampilkan semua informasi buku
    fun tampilkanInfo() {
        println("=== Informasi Buku ===")
        println("Judul        : $judul")
        println("Penulis      : $penulis")
        println("Tahun Terbit : $tahunTerbit")
        println("Status       : ${if (isDipinjam) "Dipinjam" else "Tersedia"}")
        println("Peminjam     : ${peminjam ?: "-"}")
        println("======================\n")
    }
}

fun main() {
    // Membuat objek Buku (isDipinjam dan peminjam menggunakan nilai default)
    val buku1 = Buku("GALAKSI", "Poppi Pertiwi", 2017)

    // Menampilkan informasi awal
    buku1.tampilkanInfo()

    // Meminjam buku
    buku1.pinjam("Martin Edwards")
    buku1.tampilkanInfo()

    // Mengembalikan buku
    buku1.kembalikan()
    buku1.tampilkanInfo()
}