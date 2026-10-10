class Driver(
    val id: String,
    val name: String,
    val phone: String,
    val vehicle: Vehicle,
    val isActive: Boolean = true
){
    fun displayInfo(){
        println("ID Driver      : $id")
        println("Nama Driver    : $name")
        println("Phone Driver   : $phone")
        println("Status Active  : $isActive")
        println("----Informasi Kendaraan Driver---")
        vehicle.displayInfo()

    }

    fun acceptOrder():  Boolean {
        return isActive && vehicle.isAvailable
    }
}