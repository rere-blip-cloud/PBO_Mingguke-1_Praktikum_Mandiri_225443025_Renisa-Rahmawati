sealed class OrderStatus {
    abstract fun display(): String

    fun isFinal(): Boolean {
        return this is Completed || this is Cancelled
    }

    object Waiting : OrderStatus() {
        override fun display(): String = "Menunggu Pengemudi"
    }

    object OnGoing : OrderStatus() {
        override fun display(): String = "Perjalanan Sedang Berlangsung"
    }

    object Completed : OrderStatus() {
        override fun display(): String = "Pesanan Selesai"
    }

    data class Cancelled(val reason: String) : OrderStatus() {
        override fun display(): String = "Pesanan Dibatalkan (Alasan: $reason)"
    }
}