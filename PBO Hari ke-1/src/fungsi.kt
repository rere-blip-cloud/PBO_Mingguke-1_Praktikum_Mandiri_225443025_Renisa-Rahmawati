fun main(){
    class Mahasiswa(val nim: String, val nama: String, var umur: Int) {
        fun tampilkan() {
            println("NIM: $nim, Nama: $nama, Umur: $umur")
        }

        fun bertambahUmur() {
            umur++
        }
    }
    val mhs = Mahasiswa("12345", "Budi Santoso", 20)
    mhs.tampilkan()
    mhs.bertambahUmur()
    println("Umur sekarang: ${mhs.umur}")
}
