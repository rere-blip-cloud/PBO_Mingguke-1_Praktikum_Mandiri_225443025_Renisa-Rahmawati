import java.time.LocalDate

/**
 * Kelas Transaction merepresentasikan transaksi peminjaman item oleh anggota perpustakaan.
 *
 * @property id ID unik transaksi.
 * @property item Item perpustakaan yang dipinjam.
 * @property member Anggota perpustakaan yang meminjam.
 * @property borrowDate Tanggal peminjaman transaksi.
 * @property status Status transaksi saat ini (default: TransactionStatus.Borrowed).
 */
class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString(),
    var status: TransactionStatus = TransactionStatus.Borrowed
) {

    /**
     * Memproses pengembalian item dan mengkalkulasi denda keterlambatan jika ada.
     *
     * @param daysLate Jumlah hari keterlambatan pengembalian (default: 0).
     * @return Total denda keterlambatan dalam tipe data Double.
     */
    fun returnItem(daysLate: Int = 0): Double {
        if (status.isFinal()) {
            println("Error: Transaksi $id sudah final dan tidak dapat diubah.")
            return 0.0
        }

        val fine = item.returnItem(daysLate)
        status = if (daysLate > 0) {
            TransactionStatus.Overdue(daysLate)
        } else {
            TransactionStatus.Returned
        }
        return fine
    }

    /**
     * Membatalkan transaksi peminjaman jika status transaksi belum final.
     */
    fun cancel() {
        if (status.isFinal()) {
            println("Error: Transaksi $id sudah final dan tidak dapat dibatalkan.")
            return
        }

        status = TransactionStatus.Cancelled
        item.returnItem(0)
        println("Transaksi $id berhasil dibatalkan.")
    }

    /**
     * Menampilkan seluruh informasi detail transaksi peminjaman.
     */
    fun displayTransaction() {
        println("ID Transaksi   : $id")
        println("Item           : ${item.title} (${item.getItemType()})")
        println("Peminjam       : ${member.name} (${member.id})")
        println("Tanggal Pinjam : $borrowDate")
        println("Status         : ${status.display()}")
    }
}