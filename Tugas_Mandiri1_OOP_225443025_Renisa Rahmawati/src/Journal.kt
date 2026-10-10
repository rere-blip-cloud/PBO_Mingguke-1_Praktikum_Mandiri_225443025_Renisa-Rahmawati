/**
 * Kelas Journal merepresentasikan item berupa jurnal yang mewarisi kelas Item.
 *
 * @property publisher Penerbit jurnal.
 * @property volume Volume jurnal.
 * @property issueNumber Nomor edisi jurnal.
 */
class Journal(
    id: String,
    title: String,
    year: Int,
    val publisher: String,
    val volume: Int,
    val issueNumber: Int
) : Item(id, title, year) {

    /** Mengembalikan besar denda per hari untuk jurnal (Rp3.000). */
    override fun calculateFinePerDay(): Double {
        return 3000.0
    }

    /** Mengembalikan jenis item yaitu "Jurnal". */
    override fun getItemType(): String {
        return "Jurnal"
    }

    /** Mengembalikan batas maksimal peminjaman jurnal (7 hari). */
    override fun getMaxBorrowDays(): Int {
        return 7
    }

    /** Menampilkan informasi detail jurnal dengan memanggil super.displayInfo(). */
    override fun displayInfo() {
        super.displayInfo()
        println("Penerbit       : $publisher")
        println("Volume         : $volume")
        println("Edisi          : $issueNumber")
    }
}