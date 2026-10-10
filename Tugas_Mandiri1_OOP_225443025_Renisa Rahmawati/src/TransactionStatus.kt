/**
 * Sealed class TransactionStatus mewakili status transaksi peminjaman.
 */
sealed class TransactionStatus {

    /**
     * Mengembalikan representasi String dari status transaksi.
     */
    abstract fun display(): String

    /**
     * Mengembalikan true jika status transaksi sudah final (Returned atau Cancelled).
     */
    fun isFinal(): Boolean {
        return this is Returned || this is Cancelled
    }

    /** Status item sedang dipinjam. */
    object Borrowed : TransactionStatus() {
        override fun display(): String = "Dipinjam"
    }

    /** Status item sudah dikembalikan. */
    object Returned : TransactionStatus() {
        override fun display(): String = "Dikembalikan"
    }

    /** Status peminjaman terlambat beserta jumlah hari keterlambatan. */
    data class Overdue(val daysLate: Int) : TransactionStatus() {
        override fun display(): String = "Terlambat ($daysLate hari)"
    }

    /** Status transaksi dibatalkan. */
    object Cancelled : TransactionStatus() {
        override fun display(): String = "Dibatalkan"
    }
}