/*
*
 * Kelas Mahasiswa merepresentasikan data mahasiswa
 *
 * @property nim Nomor Induk Mahasiswa (tidak bisa diubah setelah dibuat)
 * @property nama Nama lengkap mahasiswa (tidak bisa diubah setelah dibuat)
 * @property jurusan Jurusan mahasiswa (bisa diubah)
 * @property ipk Indeks Prestasi Kumulatif (bisa diubah)
class Mahasiswa(
    val nim: String,
    val nama: String,
    var jurusan: String,
    var ipk: Double
) {
    // Blok inisialisasi - dijalankan saat objek dibuat
    init {
        println("✅ Mahasiswa $nama dengan NIM $nim berhasil didaftarkan!")

        // Validasi IPK
        if (ipk < 0.0 || ipk > 4.0) {
            println("⚠️ PERINGATAN: IPK $ipk tidak valid! IPK harus antara 0.0 - 4.0")
        }
    }

*
     * Menampilkan seluruh data mahasiswa


    fun tampilkan() {
        println("=" .repeat(50))
        println("📋 DATA MAHASISWA")
        println("=" .repeat(50))
        println("NIM     : $nim")
        println("Nama    : $nama")
        println("Jurusan : $jurusan")
        println("IPK     : $ipk")
        println("Predikat: ${hitungPredikat()}")
        println("=" .repeat(50))
    }

*
     * Menghitung predikat kelulusan berdasarkan IPK
     *
     * @return String predikat kelulusan


    fun hitungPredikat(): String {
        return when {
            ipk >= 3.5 -> "🏆 Cumlaude (Dengan Pujian)"
            ipk >= 3.0 -> "⭐ Sangat Memuaskan"
            ipk >= 2.5 -> "✅ Memuaskan"
            ipk >= 2.0 -> "📖 Cukup"
            else -> "📚 Perlu Perbaikan"
        }
    }

*
     * Memperbarui IPK mahasiswa
     *
     * @param ipkBaru Nilai IPK baru (harus antara 0.0 - 4.0)
     * @return true jika berhasil, false jika gagal


    fun updateIpk(ipkBaru: Double): Boolean {
        return if (ipkBaru in 0.0..4.0) {
            ipk = ipkBaru
            println("✅ IPK $nama berhasil diperbarui menjadi $ipkBaru")
            true
        } else {
            println("❌ Gagal: IPK $ipkBaru tidak valid (harus 0.0 - 4.0)")
            false
        }
    }

*
     * Mengecek apakah mahasiswa lulus (IPK >= 2.0)


    fun isLulus(): Boolean {
        return ipk >= 2.0
    }
}

*
 * Fungsi utama program


fun main() {
    println("=" .repeat(50))
    println("🎓 SISTEM MANAJEMEN MAHASISWA")
    println("=" .repeat(50))
    println()

    // Membuat beberapa objek mahasiswa
    val mhs1 = Mahasiswa("TI2024001", "Budi Santoso", "Teknik Informatika", 3.75)
    val mhs2 = Mahasiswa("TI2024002", "Siti Rahayu", "Sistem Informasi", 3.20)
    val mhs3 = Mahasiswa("TI2024003", "Ahmad Fauzi", "Teknik Komputer", 1.80)

    println()

    // Menampilkan data semua mahasiswa
    mhs1.tampilkan()
    println()
    mhs2.tampilkan()
    println()
    mhs3.tampilkan()
    println()

    // Demonstrasi update data
    println("=" .repeat(50))
    println("🔄 DEMONSTRASI UPDATE DATA")
    println("=" .repeat(50))

    // Update IPK mhs3 (yang sebelumnya 1.80)
    println("Status kelulusan ${mhs3.nama}: ${if (mhs3.isLulus()) "✅ LULUS" else "❌ TIDAK LULUS"}")
    mhs3.updateIpk(2.50)
    println("Status kelulusan ${mhs3.nama} (baru): ${if (mhs3.isLulus()) "✅ LULUS" else "❌ TIDAK LULUS"}")
    mhs3.tampilkan()

    println()
    println("=" .repeat(50))
    println("🏁 PROGRAM SELESAI")
    println("=" .repeat(50))
}
*/
