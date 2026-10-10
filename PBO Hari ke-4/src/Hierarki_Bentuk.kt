import kotlin.math.PI

// Kelas Shape
open class Shape {
    open fun area(): Double = 0.0
    open fun perimeter(): Double = 0.0
    open fun name(): String = "Shape"
}

// Circle
class Circle(val radius: Double) : Shape() {
    override fun area(): Double = PI * radius * radius
    override fun perimeter(): Double = 2 * PI * radius
    override fun name(): String = "Circle"
}

// Rectangle
open class Rectangle(val width: Double, val height: Double) : Shape() {
    override fun area(): Double = width * height
    override fun perimeter(): Double = 2 * (width + height)
    override fun name(): String = "Rectangle"
}

// Square
class Square(val side: Double) : Rectangle(side, side) {
    override fun name(): String = "Square"
}

// main function
fun main() {
    val shapes: List<Shape> = listOf(
        Circle(7.0),
        Rectangle(5.0, 20.0),
        Square(5.0)
    )

    for (shape in shapes) {
        val detailSpesifik = when (shape) {
            is Circle -> "Radius: ${shape.radius}"
            is Square -> "Sisi: ${shape.side}"
            is Rectangle -> "Lebar: ${shape.width} | Panjang: ${shape.height}"
            else -> "Bentuk Tidak Diketahui"
        }

        println("Nama Bentuk : ${shape.name()}")
        println("Detail      : $detailSpesifik")
        println("Luas (Area) : ${shape.area()}")
        println("Perimeter   : ${shape.perimeter()}")
        println("--------------------------------------------------")
    }
}