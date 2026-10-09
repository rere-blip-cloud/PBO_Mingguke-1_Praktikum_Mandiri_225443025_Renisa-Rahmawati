// Kelas Room (Kamar)
class Room(
    val roomNumber: String,
    val type: String,
    val pricePerNight: Double
) {
    var isOccupied: Boolean = false
        private set

    var guestName: String? = null
        private set

    var checkInDate: String? = null
        private set

    var checkOutDate: String? = null
        private set

    fun checkIn(guest: String,
                checkIn: String,
                checkOut: String): Boolean {
        return if (!isOccupied) {
            isOccupied = true
            guestName = guest
            checkInDate = checkIn
            checkOutDate = checkOut
            println("Berhasil check-in kamar $roomNumber untuk tamu $guest.")
            true
        } else {
            println("Gagal: Kamar $roomNumber sedang terisi oleh $guestName.")
            false
        }
    }

    fun checkOut(): Boolean {
        return if (isOccupied) {
            val prevGuest = guestName
            isOccupied = false
            guestName = null
            checkInDate = null
            checkOutDate = null
            println("Berhasil check-out kamar $roomNumber dari tamu $prevGuest.")
            true
        } else {
            println("Gagal: Kamar $roomNumber tidak sedang terisi.")
            false
        }
    }

    fun isAvailable(): Boolean = !isOccupied

    fun displayInfo(): String {
        val status = if (isOccupied) "Terisi ($guestName | $checkInDate s/d $checkOutDate)" else "Tersedia"
        return """
          * Nomor Kamar    : $roomNumber
            Tipe Kamar     : $type
            Harga per Malam: Rp $pricePerNight
            Status         : $status
        """.trimIndent()
    }
}

// Kelas Hotel
class Hotel(val name: String) {
    private val rooms: MutableList<Room> = mutableListOf()
    private var totalRevenue: Double = 0.0

    fun addRoom(room: Room) {
        rooms.add(room)
    }

    fun findRoom(roomNumber: String): Room? {
        return rooms.find { it.roomNumber == roomNumber }
    }

    fun checkIn(roomNumber: String,
                guest: String,
                checkIn: String,
                checkOut: String): Boolean {
        val room = findRoom(roomNumber)
        return room?.checkIn(guest, checkIn, checkOut) ?: run {
            println("Kamar dengan nomor $roomNumber tidak ditemukan.")
            false
        }
    }

    fun checkOut(roomNumber: String): Boolean {
        val room = findRoom(roomNumber)
        return if (room != null) {
            val price = room.pricePerNight
            if (room.checkOut()) {
                totalRevenue += price
                true
            } else {
                false
            }
        } else {
            println("Kamar dengan nomor $roomNumber tidak ditemukan.")
            false
        }
    }

    fun getAvailableRooms(): List<Room> = rooms.filter { it.isAvailable() }

    fun getOccupiedRooms(): List<Room> = rooms.filter { it.isOccupied }

    fun displayAllRooms() {
        println("======Daftar Kamar di $name======")
        if (rooms.isEmpty()) {
            println("Belum ada data kamar.")
        } else {
            rooms.forEach { println(it.displayInfo()) }
        }
    }

    fun displayAvailableRooms() {
        println("\n=======DAFTAR KAMAR TERSEDIA========")
        val available = getAvailableRooms()
        if (available.isEmpty()) {
            println("Tidak ada kamar yang tersedia.")
        } else {
            available.forEach { println(it.displayInfo()) }
        }
    }

    fun displayOccupiedRooms() {
        println("\n=======DAFTAR KAMAR TERISI=======")
        val occupied = getOccupiedRooms()
        if (occupied.isEmpty()) {
            println("Tidak ada kamar yang sedang terisi.")
        } else {
            occupied.forEach { println(it.displayInfo()) }
        }
    }

    fun getTotalRevenue(): Double = totalRevenue
}

// Fungsi Main
fun main() {
    //Membuat objek Hotel dengan nama "Hotel Kampus"
    val hotel = Hotel("Hotel Kampus")

    hotel.addRoom(Room("209", "Standard", 300000.0))
    hotel.addRoom(Room("210", "Standard", 300000.0))
    hotel.addRoom(Room("301", "Deluxe", 500000.0))
    hotel.addRoom(Room("302", "Deluxe", 500000.0))
    hotel.addRoom(Room("401", "Suite", 1000000.0))
    hotel.addRoom(Room("402", "Suite", 1000000.0))
    hotel.addRoom(Room("403", "Suite", 1000000.0))

    //Menampilkan semua kamar
    hotel.displayAllRooms()

    //Check-in
    println("\n--- CHECK-IN ---")
    hotel.checkIn("209", "Martin Edwards", "2026-08-15", "2026-08-17")
    hotel.checkIn("301", "Sean", "2026-08-16", "2026-08-18")
    hotel.checkIn("401", "Zayyan", "2026-08-15", "2026-08-20")
    hotel.checkIn("403", "Daniel Caesar", "2026-08-15", "2026-08-16") // Uji gagal: kamar sedang terisi

    //Menampilkan kamar tersedia dan terisi
    hotel.displayAvailableRooms()
    hotel.displayOccupiedRooms()

    //Check-out
    println("\n--- CHECK-OUT ---")
    hotel.checkOut("209")
    hotel.checkOut("301")
    hotel.checkOut("403")

    println()

    //Menampilkan status akhir dan total pendapatan
    hotel.displayAllRooms()
    println("==========================================")
    println("Total Pendapatan Hotel: Rp ${hotel.getTotalRevenue()}")
    println("==========================================")
}
