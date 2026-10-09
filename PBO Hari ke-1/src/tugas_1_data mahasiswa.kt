class Mahasiswa(
    val nim: String,
    val nama: String,
    var jurusan: String,
    var ipk: Double,

    val angkatan: Int = nim.takeLast(2).toIntOrNull() ?: 0
) {
    // Predikat kelulusan berdasarkan IPK
    fun predikat(): String {
        return when {
            ipk >= 3.5 -> "Cumlaude"
            ipk >= 3.0 -> "Sangat Memuaskan"
            ipk >= 2.5 -> "Memuaskan"
            else -> "Perlu Perbaikan"
        }
    }

    // Mengecek status kelulusan (IPK >= 2.0)
    fun isLulus(): Boolean {
        return ipk >= 2.0
    }

    // Update IPK dengan validasi 0.0 - 4.0
    fun updateIpk(ipkBaru: Double) {
        if (ipkBaru in 0.0..4.0) {
            ipk = ipkBaru
            println("IPK $nama berhasil diperbarui menjadi: $ipk")
        } else {
            println("Gagal update, Nilai IPK harus di antara 0.0 sampai 4.0!")
        }
    }

    // Menampilkan semua data mahasiswa
    fun tampilkan() {
        println("NIM      : $nim")
        println("Nama     : $nama")
        println("Jurusan  : $jurusan")
        println("Angkatan : 20$angkatan")
        println("IPK      : $ipk")
        println("Predikat : ${predikat()}")
        println("Lulus    : ${if (isLulus()) "Ya" else "Tidak"}")
        println("-----------------------------------")
    }
}

fun main() {
    // Membuat 5 objek mahasiswa dengan data berbeda
    val daftarMahasiswa = listOf(
        Mahasiswa("202100121", "Martin", "Informatika", 3.75),
        Mahasiswa("202200222", "Noise", "Sistem Informasi", 1.80),
        Mahasiswa("202000320", "Yuna", "Teknik Komputer", 3.40),
        Mahasiswa("202300423", "James", "Informatika", 3.60),
        Mahasiswa("202200522", "Budi", "Data Science", 2.80)
    )

    // Menampilkan data semua mahasiswa
    println("=== SEMUA DATA MAHASISWA ===")
    for (mhs in daftarMahasiswa) {
        mhs.tampilkan()
    }

    // Menampilkan daftar mahasiswa yang lulus (IPK >= 2.0)
    println("\n=== DAFTAR MAHASISWA LULUS (IPK >= 2.0) ===")
    for (mhs in daftarMahasiswa) {
        if (mhs.isLulus()) {
            println("- ${mhs.nama} (IPK: ${mhs.ipk})")
        }
    }

    // Menampilkan daftar mahasiswa dengan predikat Cumlaude (IPK >= 3.5)
    println("\n=== DAFTAR MAHASISWA CUMLAUDE (IPK >= 3.5) ===")
    for (mhs in daftarMahasiswa) {
        if (mhs.ipk >= 3.5) {
            println("- ${mhs.nama} (IPK: ${mhs.ipk})")
        }
    }

    // Demonstrasi update IPK untuk salah satu mahasiswa
    println("\n=== DEMONSTRASI UPDATE IPK ===")
    val mhsUpdate = daftarMahasiswa[1] // Ali (IPK awal 1.80)
    println("IPK Awal ${mhsUpdate.nama}: ${mhsUpdate.ipk}")

    // Coba update IPK valid
    mhsUpdate.updateIpk(3.20)
    mhsUpdate.tampilkan()
}