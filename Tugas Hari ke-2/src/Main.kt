// 1. Mendeklarasikan konstanta compile-time (CONST VAL)
const val APP_VERSION = "1.0.0" // Harus di top-level

fun main() {
    // 2. Menggunakan var (Mutable)
    var namaDepan: String = "Budi"
    var namaBelakang = "Santoso" // Type inference (String)
    println("Halo, $namaDepan $namaBelakang!")

    // Mengubah nilai var (BOLEH)
    namaDepan = "Andi"
    println("Nama setelah diubah: $namaDepan $namaBelakang")

    // 3. Menggunakan val (Read-Only / Immutable reference)
    val nim: String = "1234567890"
    println("NIM: $nim")
    // nim = "0987654321" // ERROR! Baris ini akan error karena val tidak bisa di-reassign

    // 4. Membuktikan val pada objek mutable (isi bisa berubah, referensi tetap)
    val daftarNilai = mutableListOf(85, 90, 78)
    println("Daftar nilai awal: $daftarNilai")
    daftarNilai.add(95) // Menambah elemen (BOLEH, karena isi objek berubah)
    println("Daftar nilai setelah tambah: $daftarNilai")

    // 5. Mengakses konstanta compile-time
    println("Aplikasi versi: $APP_VERSION")

    // 6. Perbedaan dengan konstanta di Java (static final) - const val hanya untuk tipe primitif/String
    // const val PI = 3.14 // Boleh (Double)
    // const val USER_NAME = "admin" // Boleh (String)
}

fun main() {
    // ... (kode sebelumnya tetap ada)

    println("\n--- DEMO CLASS KUE ---")
    // Membuat Objek (Instansiasi) - TANPA KEYWORD 'new'
    val kueUltah = Kue("Ultah", "Coklat", 500)
    val kueLebaran = Kue("Nastar", "Nanas", 250)

    // Memanggil method pada objek
    kueUltah.cetakResep()
    kueLebaran.cetakResep()

    // Mengubah properti objek
    kueUltah.ubahRasa("Stroberi")
    kueUltah.tambahBerat(100)

    // Mengakses properti langsung
    println("Nama kue ultah sekarang: ${kueUltah.nama}") // val tidak bisa diubah
    // kueUltah.nama = "Kue Ulang Tahun" // ERROR!
}

fun main() {
    // ... (kode sebelumnya tetap ada)

    println("\n--- DATA MAHASISWA ---")
    // Membuat 3 objek Mahasiswa (sesuai instruksi materi)
    val mhs1 = Mahasiswa("TI001", "Anisa Rahma", "Teknik Informatika", 3.75)
    val mhs2 = Mahasiswa("TI002", "Budi Pratama", "Teknik Informatika", 2.80)
    val mhs3 = Mahasiswa("TI003", "Citra Dewi", "Sistem Informasi", 3.20)

    // Tampilkan data masing-masing
    mhs1.tampilkan()
    mhs2.tampilkan()
    mhs3.tampilkan()

    // Uji coba method perbaiki nilai
    println("\n--- SIMULASI PERBAIKAN NILAI ---")
    mhs2.perbaikiNilai(0.5) // IPK Budi naik 0.5 -> 3.30
    mhs2.tampilkan() // Predikat berubah jadi "Sangat Memuaskan"
}