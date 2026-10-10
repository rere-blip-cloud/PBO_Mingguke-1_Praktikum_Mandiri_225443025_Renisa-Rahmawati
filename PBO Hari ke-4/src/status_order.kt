sealed class OrderStatus {
    data class Success(val orderId: String) : OrderStatus()
    data class Failed(val reason: String, val errorCode: Int) : OrderStatus()
    data class Processing(val progress: Int) : OrderStatus()
    object Pending : OrderStatus()
}

fun handleOrderStatus(status: OrderStatus): String {
    return when (status) {
        is OrderStatus.Success ->
            "Pesanan Berhasil! [ID: ${status.orderId}]"

        is OrderStatus.Failed ->
            "Pesanan Gagal! [Kode Error: ${status.errorCode}] - Alasan: ${status.reason}"

        is OrderStatus.Processing ->
            "Pesanan Sedang Diproses... Progres: ${status.progress}%"

        OrderStatus.Pending ->
            "Pesanan Menunggu Konfirmasi (Pending)."
    }
}

fun main() {
    val statuses: List<OrderStatus> = listOf(
        OrderStatus.Success(orderId = "ORD-2026-001"),
        OrderStatus.Failed(reason = "Saldo Tidak Mencukupi", errorCode = 402),
        OrderStatus.Processing(75),
        OrderStatus.Pending
    )

    for (status in statuses) {
        println(handleOrderStatus(status))
    }
}