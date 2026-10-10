// Ini adalah BLUEPRINT / CETAKAN (Class)
class Kue(
    val nama: String,          // Properti read-only (bahan dasar)
    var rasa: String,          // Properti mutable (bahan bisa diganti?)
    var beratGram: Int         // Properti mutable
) {
    // Method / Perilaku
    fun cetakResep() {
        println("--- Resep Kue $nama ---")
        println("Rasa: $rasa")
        println("Berat: $beratGram gram")
        println("Nikmati kue Anda!")
    }

    fun ubahRasa(rasaBaru: String) {
        println("Mengubah rasa dari $rasa menjadi $rasaBaru")
        rasa = rasaBaru
    }

    fun tambahBerat(gramTambah: Int) {
        beratGram += gramTambah
        println("Berat sekarang: $beratGram gram")
    }
}