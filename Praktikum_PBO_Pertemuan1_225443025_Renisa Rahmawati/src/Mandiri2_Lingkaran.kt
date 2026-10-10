import kotlin.math.PI

class Lingkaran(var jariJari: Double) {
    //Menghitung luas lingkaran
    fun hitungLuas(): Double {
        return PI * jariJari * jariJari
    }

    //Menghitung keliling lingkaran
    fun hitungKeliling(): Double {
        return 2 * PI * jariJari
    }
}

fun main() {
    // Membuat 2 objek dengan jari-jari 7.0 dan 14.0
    val lingkaran1 = Lingkaran(7.0)
    val lingkaran2 = Lingkaran(14.0)

    // Menampilkan hasil untuk lingkaran pertama
    println("=== Lingkaran 1 ===")
    println("Jari-Jari  : " + String.format("%.2f", lingkaran1.jariJari))
    println("Luas       : " + String.format("%.2f", lingkaran1.hitungLuas()))
    println("Keliling   : " + String.format("%.2f", lingkaran1.hitungKeliling()))

    println()

    // Menampilkan hasil untuk lingkaran kedua
    println("=== Lingkaran 2 ===")
    println("Jari-Jari  : " + String.format("%.2f", lingkaran2.jariJari))
    println("Luas       : " + String.format("%.2f", lingkaran2.hitungLuas()))
    println("Keliling   : " + String.format("%.2f", lingkaran2.hitungKeliling()))
}