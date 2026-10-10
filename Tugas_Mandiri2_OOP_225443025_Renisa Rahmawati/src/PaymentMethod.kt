interface PaymentMethod {
    val name: String
    fun processPayment(amount: Double): PaymentResult
    fun getFee(amount: Double): Double {
        return 0.0
    }
}

class CreditCard(val cardNumber: String) : PaymentMethod {
    override val name: String = "Kartu Kredit"

    override fun getFee(amount: Double): Double {
        return amount * 0.02
    }

    override fun processPayment(amount: Double): PaymentResult {
        return if (cardNumber.length >= 16) {
            PaymentResult.Success("TX-CC-${System.currentTimeMillis()}", "2026-10-09 21:00")
        } else {
            PaymentResult.Failed("Nomor Kartu Kredit tidak valid (minimal 16 digit)", 401)
        }
    }
}

class QRIS(val qrCode: String) : PaymentMethod {
    override val name: String = "QRIS"

    override fun getFee(amount: Double): Double {
        return amount * 0.005
    }

    override fun processPayment(amount: Double): PaymentResult {
        return if (qrCode.length >= 10) {
            PaymentResult.Success("TX-QRIS-${System.currentTimeMillis()}", "2026-10-09 21:00")
        } else {
            PaymentResult.Failed("Kode QRIS tidak valid (minimal 10 karakter)", 402)
        }
    }
}

class Cash : PaymentMethod {
    override val name: String = "Tunai"

    override fun getFee(amount: Double): Double {
        return 0.0
    }

    override fun processPayment(amount: Double): PaymentResult {
        return PaymentResult.Success("TX-CASH-${System.currentTimeMillis()}", "2026-10-09 21:00")
    }
}