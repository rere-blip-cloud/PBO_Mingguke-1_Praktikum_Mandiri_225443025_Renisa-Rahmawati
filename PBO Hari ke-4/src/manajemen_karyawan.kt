sealed class EmployeeStatus {
    object Active : EmployeeStatus()
    data class OnLeave(val reason: String) : EmployeeStatus()
    data class Terminated(val date: String) : EmployeeStatus()
}

fun EmployeeStatus.display(): String {
    return when (this) {
        is EmployeeStatus.Active -> "Aktif"
        is EmployeeStatus.OnLeave -> "Cuti (Alasan: ${this.reason})"
        is EmployeeStatus.Terminated -> "Berhenti (Tanggal: ${this.date})"
    }
}

open class Employee(
    val id: String,
    val name: String,
    val baseSalary: Double,
    var status: EmployeeStatus = EmployeeStatus.Active
) {
    open fun calculateSalary(): Double = baseSalary

    open fun calculateBonus(): Double = 0.0

    open fun getRole(): String = "Employee"

    open fun displayInfo(): String {
        return """
            ID          : $id
            Nama        : $name
            Peran       : ${getRole()}
            Status      : ${status.display()}
            Gaji        : Rp ${"%.0f".format(calculateSalary())}
            Bonus       : Rp ${"%.0f".format(calculateBonus())}
        """.trimIndent()
    }
}

class FullTimeEmployee(
    id: String,
    name: String,
    baseSalary: Double,
    val allowance: Double,
    val annualBonus: Double,
    status: EmployeeStatus = EmployeeStatus.Active
) : Employee(id, name, baseSalary, status) {

    override fun calculateSalary(): Double = baseSalary + allowance

    override fun calculateBonus(): Double = annualBonus / 12

    override fun getRole(): String = "Full-Time Employee"

    override fun displayInfo(): String {
        return super.displayInfo() + "\n" + """
            [Rincian Full-Time]
            Tunjangan   : Rp ${"%.0f".format(allowance)}
        """.trimIndent()
    }
}

class PartTimeEmployee(
    id: String,
    name: String,
    val hourlyRate: Double,
    val hoursWorked: Int,
    status: EmployeeStatus = EmployeeStatus.Active
) : Employee(id, name, 0.0, status) {

    override fun calculateSalary(): Double = hourlyRate * hoursWorked

    override fun calculateBonus(): Double = 0.0

    override fun getRole(): String = "Part-Time Employee"

    override fun displayInfo(): String {
        return super.displayInfo() + "\n" + """
            [Rincian Part-Time]
            Upah/Jam    : Rp ${"%.0f".format(hourlyRate)}
            Jam Kerja   : $hoursWorked jam
        """.trimIndent()
    }
}

class ContractEmployee(
    id: String,
    name: String,
    baseSalary: Double,
    val contractDuration: Int,
    val projectBonus: Double,
    status: EmployeeStatus = EmployeeStatus.Active
) : Employee(id, name, baseSalary, status) {

    override fun calculateSalary(): Double = baseSalary

    override fun calculateBonus(): Double = projectBonus / contractDuration

    override fun getRole(): String = "Contract Employee"

    override fun displayInfo(): String {
        return super.displayInfo() + "\n" + """
            [Rincian Kontrak]
            Durasi      : $contractDuration bulan
            Bonus Proyek: Rp ${"%.0f".format(projectBonus)}
        """.trimIndent()
    }
}

class Company(val name: String) {
    private val employees: MutableList<Employee> = mutableListOf()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }

    fun findEmployee(id: String): Employee? {
        return employees.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getTotalSalary(): Double {
        return employees.sumOf { it.calculateSalary() }
    }

    fun getTotalBonus(): Double {
        return employees.sumOf { it.calculateBonus() }
    }

    fun getEmployeesByRole(role: String): List<Employee> {
        return employees.filter { it.getRole().equals(role, ignoreCase = true) }
    }

    fun displayAllEmployees() {
        println("=======Daftar Karyawan di $name=======")
        if (employees.isEmpty()) {
            println("Belum ada data karyawan.")
        } else {
            employees.forEach { emp ->
                when (emp) {
                    is FullTimeEmployee -> { /* Smart casting jika diperlukan */ }
                    is PartTimeEmployee -> { /* Smart casting jika diperlukan */ }
                    is ContractEmployee -> { /* Smart casting jika diperlukan */ }
                }

                println(emp.displayInfo())
                println("--------------------------------------------------")
            }
        }
    }

    fun displaySalaryReport() {
        println("\n==========Laporan Gaji dan Bonus==========")
        val totalGaji = getTotalSalary()
        val totalBonus = getTotalBonus()

        println("Total Pengeluaran Gaji  : Rp ${"%.0f".format(totalGaji)}")
        println("Total Pengeluaran Bonus : Rp ${"%.0f".format(totalBonus)}")
        println("TOTAL ANGGARAN          : Rp ${"%.0f".format(totalGaji + totalBonus)}")
        println("==========================================")
    }
}

fun main() {
    val company = Company("PT Teknologi Maju")

    val e1: Employee = FullTimeEmployee("FT01", "Martin Edwards", 8000000.0, 1500000.0, 12000000.0, EmployeeStatus.Active)
    val e2: Employee = FullTimeEmployee("FT02", "Syahrini", 9500000.0, 2000000.0, 18000000.0, EmployeeStatus.OnLeave("Cuti Melahirkan"))

    val e3: Employee = PartTimeEmployee("PT01", "James", 50000.0, 80, EmployeeStatus.Active)
    val e4: Employee = PartTimeEmployee("PT02", "Sean", 60000.0, 100, EmployeeStatus.Active)

    val e5: Employee = ContractEmployee("CT01", "Yuna", 6000000.0, 6, 12000000.0, EmployeeStatus.Active)
    val e6: Employee = ContractEmployee("CT02", "Moka", 6500000.0, 12, 24000000.0, EmployeeStatus.Terminated("2026-09-30"))

    company.addEmployee(e1)
    company.addEmployee(e2)
    company.addEmployee(e3)
    company.addEmployee(e4)
    company.addEmployee(e5)
    company.addEmployee(e6)

    company.displayAllEmployees()
    company.displaySalaryReport()
}