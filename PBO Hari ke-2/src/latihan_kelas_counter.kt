class Counter(
    val maxValue: Int,
    val minValue: Int = 0
) {
    var value: Int = minValue
        private set

    // Menambah nilai 1 jika belum mencapai maxValue
    fun increment() {
        if (value < maxValue) {
            value++
            println("Nilai berhasil bertambah menjadi: $value")
        } else {
            println("Gagal Increment: Nilai sudah mencapai batas maksimum ($maxValue)!")
        }
    }

    // Mengurangi nilai 1 jika belum mencapai minValue
    fun decrement() {
        if (value > minValue) {
            value--
            println("Nilai berhasil berkurang menjadi: $value")
        } else {
            println("Gagal Decrement: Nilai sudah mencapai batas minimum ($minValue)!")
        }
    }

    // Mengatur ulang nilai counter ke minValue
    fun reset() {
        value = minValue
        println("Counter berhasil di-reset ke nilai minimum ($minValue).")
    }

    // Memeriksa apakah nilai saat ini sama dengan maxValue
    fun isAtMax(): Boolean = value == maxValue

    // Memeriksa apakah nilai saat ini sama dengan minValue
    fun isAtMin(): Boolean = value == minValue

    // Menampilkan informasi nilai counter
    fun display() {
        println("----------------------------------------")
        println("Status Counter saat ini : $value")
        println("Batas Minimum (minValue): $minValue")
        println("Batas Maksimum (maxValue): $maxValue")
        println("----------------------------------------")
    }
}

fun main() {
    println("---COUNTER---")
    val counter = Counter(maxValue = 2, minValue = 0)

    // Tampilan Awal
    println("\nTampilan Awal:")
    counter.display()
    println("Apakah di batas minimum? ${counter.isAtMin()}") // Expected: true
    println("Apakah di batas maksimum? ${counter.isAtMax()}") // Expected: false

    // Decrement pada Batas Minimum
    println("\nDecrement pada Batas Minimum:")
    counter.decrement() // Harus gagal karena nilai masih 0

    // Increment sampai Batas Maksimum
    println("\nIncrement sampai Batas Maksimum:")
    counter.increment() // Nilai jadi 1
    counter.increment() // Nilai jadi 2
    counter.display()
    println("Apakah di batas minimum? ${counter.isAtMin()}") // Expected: false
    println("Apakah di batas maksimum? ${counter.isAtMax()}") // Expected: true

    // Increment saat Sudah Maksimum
    println("\nIncrement Saat Sudah Maksimum:")
    counter.increment() // Harus gagal karena nilai sudah 3

    // Reset
    println("\nReset:")
    counter.reset()
    counter.display()
    println("Apakah di batas minimum setelah reset? ${counter.isAtMin()}") // Expected: true
}