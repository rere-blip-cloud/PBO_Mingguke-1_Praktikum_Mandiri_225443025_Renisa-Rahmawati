class Kalkulator {

    // Fungsi Tambah
    fun tambah(a: Double, b: Double): Double {
        return a + b
    }

    // Fungsi Kurang
    fun kurang(a: Double, b: Double): Double {
        return a - b
    }

    // Fungsi Kali
    fun kali(a: Double, b: Double): Double {
        return a * b
    }

    // Fungsi Bagi (dengan pencegahan pembagian 0)
    fun bagi(a: Double, b: Double): String {
        if (b == 0.0) {
            println("Peringatan: Tidak bisa membagi dengan angka 0!")
            return "Undefined"
        }
        return (a / b).toString()
    }

    // Fungsi untuk menampilkan hasil operasi
    fun tampilkanOperasi(a: Double, b: Double, operator: Char) {
        val hasil = when (operator) {
            '+' -> tambah(a, b)
            '-' -> kurang(a, b)
            '*' -> kali(a, b)
            '/' -> bagi(a, b)
            else -> 0.0
        }
        println("Hasil dari $a $operator $b = $hasil")
    }
}

fun main() {
    val k = Kalkulator()

    // Penggunaan
    k.tampilkanOperasi(10.0, 2.0, '+')
    k.tampilkanOperasi(10.0, 3.0, '-')
    k.tampilkanOperasi(10.0, 4.0, '*')
    k.tampilkanOperasi(10.0, 5.0, '/')
    k.tampilkanOperasi(10.0, 0.0, '/')

}
