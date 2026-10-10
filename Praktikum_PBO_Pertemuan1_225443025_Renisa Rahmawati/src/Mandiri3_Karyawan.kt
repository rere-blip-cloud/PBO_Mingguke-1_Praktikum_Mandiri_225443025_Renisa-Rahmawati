class Karyawan(val nama: String, var gajiPokok: Double) {

    // Menampilkan informasi nama dan gaji pokok
    fun tampilkanGaji() {
        val gajiFormat = String.format("Rp %,d", gajiPokok.toInt())
        println("Nama : $nama")
        println("Gaji : $gajiFormat")
    }

    // Menaikkan gaji pokok sebesar persen (%)
    fun naikGaji(persen: Double) {
        val kenaikan = gajiPokok * (persen / 100.0)
        gajiPokok += kenaikan
        val nominalNaik = String.format("Rp %,d", kenaikan.toInt())
    }
}

fun main() {
    //Menampilkan gaji karyawan
    println("========MANAJEMEN GAJI KARYAWAN========")

    //Membuat 1 objek karyawan dengan gaji awal 5.000.000
    val karyawan1 = Karyawan("Martin", 5000000.0)

    //Menampilkan gaji awal
    println("[ Gaji Awal ]")
    karyawan1.tampilkanGaji()

    //Menaikkan gaji sebesar 15%
    karyawan1.naikGaji(15.0)
    println("---------------------------------------")

    //Menampilkan gaji akhir
    println("[ Gaji Akhir ]")
    karyawan1.tampilkanGaji()
    println("---------------------------------------")
}