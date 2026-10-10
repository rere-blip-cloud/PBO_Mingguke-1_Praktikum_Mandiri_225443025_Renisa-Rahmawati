/**
 * Kelas DVD merepresentasikan item berupa DVD yang mewarisi kelas Item.
 *
 * @property director Sutradara DVD.
 * @property duration Durasi DVD dalam menit.
 * @property genre Genre DVD.
 */
class DVD(
    id: String,
    title: String,
    year: Int,
    val director: String,
    val duration: Int,
    val genre: String
) : Item(id, title, year) {

    /** Mengembalikan besar denda per hari untuk DVD (Rp5.000). */
    override fun calculateFinePerDay(): Double {
        return 5000.0
    }

    /** Mengembalikan jenis item yaitu "DVD". */
    override fun getItemType(): String {
        return "DVD"
    }

    /** Mengembalikan batas maksimal peminjaman DVD (3 hari). */
    override fun getMaxBorrowDays(): Int {
        return 3
    }

    /** Menampilkan informasi detail DVD dengan memanggil super.displayInfo(). */
    override fun displayInfo() {
        super.displayInfo()
        println("Sutradara      : $director")
        println("Durasi         : $duration menit")
        println("Genre          : $genre")
    }
}