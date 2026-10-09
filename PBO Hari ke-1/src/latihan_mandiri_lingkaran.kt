import kotlin.math.PI

class Lingkaran(var jariJari: Double) {

    // Mengembalikan luas lingkaran (π × r²)
    fun luas(): Double {
        return PI * jariJari * jariJari
    }

    // Mengembalikan keliling lingkaran (2 × π × r)
    fun keliling(): Double {
        return 2 * PI * jariJari
    }

    // Menampilkan jari-jari, luas, dan keliling
    fun tampilkan() {
        println("=== Informasi Lingkaran ===")
        println("Jari-jari : $jariJari")
        println("Luas      : ${luas()}")
        println("Keliling  : ${keliling()}")
    }
}

fun main() {
    val lingkaran = Lingkaran(6.0)
    lingkaran.tampilkan()
}

