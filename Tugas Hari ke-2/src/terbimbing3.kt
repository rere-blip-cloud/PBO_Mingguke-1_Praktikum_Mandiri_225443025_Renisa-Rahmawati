class Mahasiswa(
    val nim: String,
    val nama: String,
    val jurusan: String,
    var ipk: Double
) {
    // Method untuk menampilkan data
    fun tampilkan() {
        println("=================================")
        println("          DATA MAHASISWA          ")
        println("=================================")
        println("NIM      : $nim")
        println("Nama     : $nama")
        println("Jurusan  : $jurusan")
        println("IPK      : %.2f".format(ipk))
        println("Predikat : ${predikat()}")
        println("=================================")
    }

    // Method untuk menentukan predikat (logika sesuai materi)
    fun predikat(): String {
        return when {
            ipk >= 3.5 -> "Cumlaude"
            ipk >= 3.0 -> "Sangat Memuaskan"
            ipk >= 2.5 -> "Memuaskan"
            else -> "Perlu Perbaikan"
        }
    }

    // Method tambahan: simulasi kenaikan IPK
    fun perbaikiNilai(kenaikan: Double) {
        if (kenaikan > 0) {
            ipk += kenaikan
            if (ipk > 4.0) ipk = 4.0 // Maksimal 4.0
            println("IPK $nama diperbaiki menjadi ${String.format("%.2f", ipk)}")
        } else {
            println("Kenaikan harus positif!")
        }
    }
}