class Customer(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    var balance: Double = 0.0
){
    fun displayInfo(){
        println("ID Customer    : $id")
        println("Nama Customer  : $name")
        println("Phone Number   : $phone")
        println("Email          : $email")
        println("Saldo Customer : Rp$balance")
    }
    fun  topUp(amount: Double) {
        balance += amount
        println("Top-up sebesar $amount berhasil.")
        println("Saldo saat ini: Rp$balance")
    }

    fun canPay(amount: Double): Boolean {
        return balance >= amount
    }
}