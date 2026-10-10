import kotlin.math.PI

// Superclass: Shape
open class Shape(val name: String) {
    open fun luas(): Double = 0.0

    fun tampilkanInfo() {
        println("Bentuk: $name")
        println("Luas  : ${luas()}")
        println("-------------------")
    }
}

// Subclass: Rectangle
class Rectangle(val panjang: Double,
                val lebar: Double) : Shape("Persegi Panjang") {
    override fun luas(): Double = panjang * lebar
}

// Subclass: Circle
class Circle(val jariJari: Double) : Shape("Lingkaran") {
    override fun luas(): Double = PI * jariJari * jariJari
}

// Subclass: Triangle
class Triangle(val alas: Double,
               val tinggi: Double) : Shape("Segitiga") {
    override fun luas(): Double = 0.5 * alas * tinggi
}

// Fungsi Utama
fun main() {
    val persegiPanjang = Rectangle(10.0, 6.0)
    val lingkaran = Circle(7.0)
    val segitiga = Triangle(6.0, 8.0)

    println("======BENTUK======")

    persegiPanjang.tampilkanInfo()
    lingkaran.tampilkanInfo()
    segitiga.tampilkanInfo()
}

