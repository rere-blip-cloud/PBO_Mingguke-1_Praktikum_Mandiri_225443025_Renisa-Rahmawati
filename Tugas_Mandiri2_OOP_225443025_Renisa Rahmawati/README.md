# Sistem Transportasi Online (Go-Transport)

## Identitas Mahasiswa

| Keterangan  | Isi                            |
| ----------- | ------------------------------ |
| Nama        | `RENISA RAHMAWATI`           |
| NIM         | `225443025`                    |
| Kelas       | `2AEC1`                  |
| Mata Kuliah | Pemrograman Berorientasi Objek |

## Deskripsi Singkat Program

Program berbasis **Kotlin** untuk mensimulasikan layanan transportasi online. Sistem mengelola kendaraan (Mobil, Motor, Truk), driver, customer, pesanan, dan pembayaran. Tarif dihitung berbeda untuk tiap jenis kendaraan, pembayaran bisa lewat Kartu Kredit, QRIS, atau Tunai (dengan biaya layanan masing-masing), dan pendapatan dilaporkan dari order yang selesai.

Konsep yang didemonstrasikan: pewarisan, polimorfisme, enkapsulasi (`private` pada `Order` dan `Payment`), interface (`PaymentMethod`), sealed class (`OrderStatus`, `PaymentResult`), serta smart casting (`is`, `as?`).

## Struktur Program

```
├── Vehicle.kt          # Kelas induk kendaraan (open class)
├── Car.kt              # Turunan Vehicle: Mobil
├── Motorcycle.kt       # Turunan Vehicle: Motor
├── Truck.kt            # Turunan Vehicle: Truk
├── Driver.kt           # Driver dan kendaraannya
├── Customer.kt         # Customer, saldo, top-up
├── Order.kt            # Pesanan perjalanan
├── OrderStatus.kt      # Sealed class status order
├── Payment.kt          # Pembayaran untuk sebuah order
├── PaymentMethod.kt    # Interface + CreditCard, QRIS, Cash
├── PaymentResult.kt    # Sealed class hasil pembayaran
├── TransportSystem.kt  # Pengelola utama sistem
├── Main_1.kt           # Demo dasar: Vehicle, Driver, Customer, tarif, top-up
├── Main_2.kt           # Demo order & pembayaran, plus enkapsulasi
├── Main_3.kt           # Demo pewarisan: Car, Motorcycle, Truck
├── Main_4.kt           # Demo polimorfisme, sealed class, smart & safe casting
└── Main_5.kt           # Demo skenario lengkap lewat TransportSystem
```

| Kelas | Peran |
| ----- | ----- |
| `Vehicle` → `Car`, `Motorcycle`, `Truck` | Hierarki kendaraan, `calculateFare()` di-override tiap jenis |
| `PaymentMethod` → `CreditCard`, `QRIS`, `Cash` | Interface metode bayar dengan `getFee()` |
| `OrderStatus` | `Waiting`, `OnGoing`, `Completed`, `Cancelled(reason)` |
| `PaymentResult` | `Success`, `Failed`, `Pending` |
| `TransportSystem` | Menyimpan data, membuat order, memproses pembayaran, laporan pendapatan |

## Cara Menjalankan

Prasyarat: JDK dan Kotlin compiler (`kotlinc`), atau IntelliJ IDEA.

**IntelliJ IDEA:** buka folder proyek, buka salah satu file `Main_X.kt`, lalu klik tombol Run di samping `fun main()`.

**Command line:** karena tiap `Main_X.kt` punya `main()` sendiri, compile hanya **satu** Main per eksekusi bersama file kelas lainnya. Contoh untuk skenario lengkap (`Main_5.kt`):

```bash
kotlinc Vehicle.kt Car.kt Motorcycle.kt Truck.kt Driver.kt Customer.kt \
        Order.kt OrderStatus.kt Payment.kt PaymentMethod.kt PaymentResult.kt \
        TransportSystem.kt Main_5.kt -include-runtime -d transport.jar
java -jar transport.jar
```

Ganti `Main_5.kt` dengan Main lain untuk demo berbeda (sesuaikan dengan nama file kamu).
