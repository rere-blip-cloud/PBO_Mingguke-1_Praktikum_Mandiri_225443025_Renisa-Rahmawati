class Buku(val judul: String, val pengarang: String, var tahunTerbit: Int) {
    fun infoBuku() {
        println("Judul        : $judul")
        println("Pengarang    : $pengarang")
        println("Tahun Terbit : $tahunTerbit")
        println("-----------------------------------")
    }
}

fun main() {
    // Membuat objek buku (fiksi, non-fiksi, komik)
    val bukuFiksi = Buku("GALAKSI", "Poppi Pertiwi", 2017)
    val bukuFiksi1 = Buku("86", "Asato Asato", 2018)
    val bukuNonFiksi = Buku("Filosofi Teras", "Henry Manampiring", 2019)
    val bukuNonFiksi1 = Buku("Sapiens", "Yuval Noah Harari", 2011)
    val komik = Buku("Black Clover", "Yukita Tabata", 2015)
    val komik1 = Buku("Naruto", "Masashi Kishimoto", 1999)

    // Menampilkan informasi semua buku
    println("==== DAFTAR BUKU ====")
    bukuFiksi.infoBuku()
    bukuFiksi1.infoBuku()
    bukuNonFiksi.infoBuku()
    bukuNonFiksi1.infoBuku()
    komik.infoBuku()
    komik1.infoBuku()
}