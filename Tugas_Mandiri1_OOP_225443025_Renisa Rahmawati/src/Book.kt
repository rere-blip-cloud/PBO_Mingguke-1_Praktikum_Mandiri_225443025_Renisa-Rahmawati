/**
 * Kelas Book merepresentasikan item berupa buku yang mewarisi kelas Item.
 *
 * @property author Penulis buku.
 * @property pages Jumlah halaman buku.
 * @property genre Genre dari buku.
 */
class Book(
    id: String,
    title: String,
    year: Int,
    val author: String,
    val pages: Int,
    val genre: String
) : Item(id, title, year) {

    /** Mengembalikan besar denda per hari untuk buku (Rp2.000). */
    override fun calculateFinePerDay(): Double {
        return 2000.0
    }

    /** Mengembalikan jenis item yaitu "Buku". */
    override fun getItemType(): String {
        return "Buku"
    }

    /** Mengembalikan batas maksimal peminjaman buku (14 hari). */
    override fun getMaxBorrowDays(): Int {
        return 14
    }

    /** Menampilkan informasi detail buku dengan memanggil super.displayInfo(). */
    override fun displayInfo() {
        super.displayInfo()
        println("Penulis        : $author")
        println("Jumlah Halaman : $pages")
        println("Genre          : $genre")
    }
}