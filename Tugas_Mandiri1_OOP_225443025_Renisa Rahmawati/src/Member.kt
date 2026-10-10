/**
 * Kelas Member merepresentasikan anggota perpustakaan yang dapat meminjam item.
 *
 * @property id ID unik anggota.
 * @property name Nama anggota.
 * @property email Email anggota (di-enkapsulasi).
 * @property phone Nomor telepon anggota (di-enkapsulasi).
 */
class Member(
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String
) {
    private val transactions: MutableList<Transaction> = mutableListOf()

    /** Jumlah total transaksi peminjaman anggota. */
    val transactionCount: Int
        get() = transactions.size

    /** Total denda dari semua transaksi berstatus Overdue. */
    val totalFines: Double
        get() = transactions.sumOf { tx ->
            val status = tx.status
            if (status is TransactionStatus.Overdue) {
                status.daysLate * tx.item.calculateFinePerDay()
            } else {
                0.0
            }
        }

    /** Jumlah transaksi aktif yang saat ini masih berstatus Borrowed. */
    val activeBorrows: Int
        get() = transactions.count { it.status is TransactionStatus.Borrowed }

    // --- Getter untuk Properti Private ---

    /** Mengembalikan email anggota. */
    fun getEmail(): String = email

    /** Mengembalikan nomor telepon anggota. */
    fun getPhone(): String = phone

    // --- Metode Utama ---

    /**
     * Meminjam item perpustakaan jika item tersedia dan pinjaman aktif < 3.
     */
    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable) {
            println("Error: Item '${item.title}' sedang tidak tersedia.")
            return null
        }

        if (activeBorrows >= 3) {
            println("Error: Anggota $name telah mencapai batas maksimal peminjaman (3 item).")
            return null
        }

        item.borrow()
        val transactionId = "TRX-${System.currentTimeMillis()}"
        val newTransaction = Transaction(transactionId, item, this)
        transactions.add(newTransaction)

        println("Peminjaman berhasil! ID Transaksi: $transactionId")
        return newTransaction
    }

    /**
     * Mengembalikan item yang sedang dipinjam dan menghitung dendanya.
     */
    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val activeTx = transactions.find { it.item.id == item.id && it.status is TransactionStatus.Borrowed }

        if (activeTx == null) {
            println("Error: Transaksi aktif untuk item '${item.title}' tidak ditemukan.")
            return 0.0
        }

        return activeTx.returnItem(daysLate)
    }

    /**
     * Mengembalikan daftar transaksi anggota secara immutable.
     */
    fun getTransactions(): List<Transaction> {
        return transactions.toList()
    }

    /**
     * Menampilkan informasi profil anggota.
     */
    fun displayInfo() {
        println("ID Anggota   : $id")
        println("Nama         : $name")
        println("Email        : $email")
        println("Telepon      : $phone")
        println("Total Pinjam : $transactionCount")
        println("Pinjam Aktif : $activeBorrows")
        println("Total Denda  : Rp$totalFines")
    }

    /**
     * Menampilkan seluruh riwayat transaksi anggota secara terstruktur.
     */
    fun displayTransactions() {
        println("=== RIWAYAT TRANSAKSI ANGGOTA: $name ($id) ===")
        if (transactions.isEmpty()) {
            println("Belum ada riwayat transaksi.")
        } else {
            transactions.forEach { tx ->
                tx.displayTransaction()
                println("----------------------------------------")
            }
        }
    }
}