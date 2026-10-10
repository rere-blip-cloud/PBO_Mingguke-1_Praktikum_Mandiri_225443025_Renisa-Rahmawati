class Mahasiswa(val nim: String, val nama: String, val jurusan: String, val ipk: Double) {
    //Menentukan predikat berdasarkan nilai IPK
    fun predikat(): String{
        return when {
            ipk >= 3.5 -> "Cumlaude"
            ipk >= 3.0 -> "Sangat Memuaskan"
            ipk >= 2.5 -> "Memuaskan"
            else -> "Perlu Perbaikan"
        }
    }

    //Menampilkan format data Mahasiswa
    fun tampilkan() {
        println("===================================")
        println("          Data Mahasiswa           ")
        println("===================================")
        println("NIM        : $nim")
        println("Nama       : $nama")
        println("Jurusan    : $jurusan")
        println("IPK        : $ipk")
        println("Predikat   : ${predikat()}")
    }
}

    fun main(){
        val daftarMahasiswa = mutableListOf<Mahasiswa>()
        val jumlahData = 3
        var counter = 1

        println("===INPUT DATA MAHASISWA===")

        //Menginput data sebanyak 3 kali
        while (counter <= jumlahData) {
            println("--------Data ke-$counter--------")
            print("Masukkan NIM: ")
            val nim = readln()

            print("Masukkan Nama: ")
            val nama = readln()

            print("Masukkan Jurusan: ")
            val jurusan = readln()

            print("Masukkan IPK: ")
            val ipk = readln().toDouble()

            val mhs = Mahasiswa(nim, nama, jurusan, ipk)
            daftarMahasiswa.add(mhs)

            counter++
        }
            for (mhs in daftarMahasiswa) {
                mhs.tampilkan()
                println()
            }
        }
