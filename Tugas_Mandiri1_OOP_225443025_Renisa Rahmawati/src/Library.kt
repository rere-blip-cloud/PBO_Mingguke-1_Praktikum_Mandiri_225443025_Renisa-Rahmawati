/**
 * Kelas Library mengelola seluruh operasi perpustakaan seperti item, anggota, dan transaksi.
 *
 * @property name Nama perpustakaan.
 */
class Library(val name: String) {
    private val items: MutableList<Item> = mutableListOf()
    private val members: MutableList<Member> = mutableListOf()
    private val transactions: MutableList<Transaction> = mutableListOf()

    // --- Computed Properties ---

    /** Jumlah total seluruh item di perpustakaan. */
    val totalItems: Int
        get() = items.size

    /** Jumlah item yang saat ini tersedia untuk dipinjam. */
    val availableItems: Int
        get() = items.count { it.isAvailable }

    /** Jumlah total anggota perpustakaan yang terdaftar. */
    val totalMembers: Int
        get() = members.size

    /** Jumlah total transaksi yang telah tercatat. */
    val totalTransactions: Int
        get() = transactions.size

    // --- Metode Manajemen Item ---

    /**
     * Menambahkan satu item ke perpustakaan.
     */
    fun addItem(item: Item) {
        items.add(item)
        println("Item '${item.title}' (${item.id}) berhasil ditambahkan ke perpustakaan.")
    }

    /**
     * Menambahkan banyak item sekaligus ke perpustakaan.
     */
    fun addItems(vararg newItems: Item) {
        for (item in newItems) {
            addItem(item)
        }
    }

    /**
     * Mencari item berdasarkan ID.
     */
    fun findItem(id: String): Item? {
        return items.find { it.id.equals(id, ignoreCase = true) }
    }

    /**
     * Mencari item berdasarkan judul atau ID yang mengandung keyword (case-insensitive).
     */
    fun searchItems(keyword: String): List<Item> {
        return items.filter {
            it.title.contains(keyword, ignoreCase = true) || it.id.contains(keyword, ignoreCase = true)
        }
    }

    // --- Metode Manajemen Anggota ---

    /**
     * Mendaftarkan anggota baru jika ID belum digunakan.
     */
    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        if (findMember(id) != null) {
            println("Error: Anggota dengan ID '$id' sudah terdaftar.")
            return false
        }
        val member = Member(id, name, email, phone)
        members.add(member)
        println("Anggota '$name' ($id) berhasil terdaftar.")
        return true
    }

    /**
     * Mencari anggota berdasarkan ID.
     */
    fun findMember(id: String): Member? {
        return members.find { it.id.equals(id, ignoreCase = true) }
    }

    // --- Metode Operasi Perpustakaan ---

    /**
     * Memproses peminjaman item oleh anggota berdasarkan ID.
     */
    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null || item == null) {
            println("Error: Anggota atau Item tidak ditemukan.")
            return null
        }

        val transaction = member.borrowItem(item)
        if (transaction != null) {
            transactions.add(transaction)
        }
        return transaction
    }

    /**
     * Memproses pengembalian item oleh anggota berdasarkan ID dan mengembalikan dendanya.
     */
    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null || item == null) {
            println("Error: Anggota atau Item tidak ditemukan.")
            return 0.0
        }

        return member.returnItem(item, daysLate)
    }

    // --- Metode Laporan dan Tampilan ---

    /**
     * Menampilkan semua item di perpustakaan beserta ringkasan ketersediaannya.
     */
    fun displayAllItems() {
        println("=== DAFTAR SEMUA ITEM PERPUSTAKAAN ===")
        println("Total Item: $totalItems | Tersedia: $availableItems")
        println("----------------------------------------")
        items.forEach { item ->
            item.displayInfo()
            println("----------------------------------------")
        }
    }

    /**
     * Menampilkan daftar item yang saat ini tersedia dalam format ringkas.
     */
    fun displayAvailableItems() {
        println("=== DAFTAR ITEM TERSEDIA ===")
        val availableList = items.filter { it.isAvailable }
        if (availableList.isEmpty()) {
            println("Tidak ada item yang tersedia saat ini.")
        } else {
            availableList.forEach { item ->
                println("- [${item.getItemType()}] ${item.id} | ${item.title} (${item.year})")
            }
        }
    }

    /**
     * Menampilkan informasi seluruh anggota terdaftar.
     */
    fun displayAllMembers() {
        println("=== DAFTAR ANGGOTA PERPUSTAKAAN ===")
        members.forEach { member ->
            member.displayInfo()
            println("----------------------------------------")
        }
    }

    /**
     * Menampilkan seluruh transaksi yang tercatat di perpustakaan.
     */
    fun displayAllTransactions() {
        println("=== DAFTAR TRANSAKSI PERPUSTAKAAN ===")
        if (transactions.isEmpty()) {
            println("Belum ada transaksi tercatat.")
        } else {
            transactions.forEach { tx ->
                tx.displayTransaction()
                println("----------------------------------------")
            }
        }
    }

    /**
     * Menampilkan laporan ringkasan statistik perpustakaan.
     */
    fun displayReport() {
        val totalDendaSistem = members.sumOf { it.totalFines }
        val dipinjamCount = items.count { !it.isAvailable }

        println("=== LAPORAN RINGKASAN PERPUSTAKAAN ===")
        println("Nama Perpustakaan : $name")
        println("Total Item        : $totalItems")
        println("Item Tersedia     : $availableItems")
        println("Item Dipinjam     : $dipinjamCount")
        println("Total Anggota     : $totalMembers")
        println("Total Transaksi   : $totalTransactions")
        println("Total Denda       : Rp$totalDendaSistem")
    }
}