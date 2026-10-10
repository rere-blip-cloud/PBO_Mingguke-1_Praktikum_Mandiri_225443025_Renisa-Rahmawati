class Payment(
    val order: Order,
    var method: PaymentMethod
) {
    private var _amount: Double = order.getTotalFare()
    private var _isPaid: Boolean = false

    fun getAmount(): Double {
        return _amount
    }

    fun isPaid(): Boolean {
        return _isPaid
    }

    fun processPayment(): PaymentResult {
        val totalFee = method.getFee(_amount)
        val grandTotal = _amount + totalFee
        val result = method.processPayment(grandTotal)

        if (result is PaymentResult.Success) {
            _isPaid = true
        }
        return result
    }

    fun displayPayment() {
        println("ID Order     : ${order.id}")
        println("Total Tagihan: Rp$_amount")
        println("Metode       : ${method.name}")
        println("Biaya Layanan: Rp${method.getFee(_amount)}")
        println("Status Lunas : $_isPaid")
    }
}