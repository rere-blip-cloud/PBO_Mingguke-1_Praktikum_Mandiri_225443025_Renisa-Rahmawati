# Sistem Manajemen Perpustakaan Digital

Tugas Mandiri - Pemrograman Berorientasi Objek (PBO)
Program Studi D4 Teknologi Rekayasa Informatika Industri

---

## 1. Identitas Mahasiswa

| Keterangan    | Isi                         |
| ------------- | --------------------------- |
| Nama          | `[RENISA RAHMAWATI]`        |
| NIM           | `[225443025]`                 |
| Kelas         | `[2AEC1]`               |
| Program Studi | D4 Teknologi Rekayasa Informatika Industri |
| Mata Kuliah   | Pemrograman Berorientasi Objek |
| Dosen         | M Harry K Saputra           |

---

## 2. Deskripsi Singkat Program

Program ini adalah **Sistem Manajemen Perpustakaan Digital** yang dibuat dengan bahasa **Kotlin** dan hanya memakai Kotlin Standard Library. Program mengelola:

- **Item perpustakaan** berupa Buku, Jurnal, dan DVD
- **Anggota** perpustakaan beserta data pribadinya
- **Transaksi** peminjaman dan pengembalian item
- **Status transaksi** (Dipinjam, Dikembalikan, Terlambat, Dibatalkan)
- **Denda keterlambatan** yang besarnya berbeda tiap jenis item

Aturan utama sistem:

| Jenis Item | Denda per Hari | Maks. Peminjaman |
| ---------- | -------------- | ---------------- |
| Buku       | Rp2.000        | 14 hari          |
| Jurnal     | Rp3.000        | 7 hari           |
| DVD        | Rp5.000        | 3 hari           |

Setiap anggota hanya boleh memiliki maksimal **3 peminjaman aktif** pada satu waktu.

Konsep OOP dan fitur Kotlin yang didemonstrasikan di `main()`:

- **Enkapsulasi**: `isAvailable` memakai `private set`, `email` dan `phone` memakai `private val`
- **Pewarisan**: `Book`, `Journal`, dan `DVD` mewarisi `Item`
- **Polimorfisme**: `List<Item>` berisi berbagai subclass, `calculateFinePerDay()` memberi hasil berbeda tiap jenis
- **Abstraksi**: `Item` adalah `abstract class` dengan metode abstrak
- **Sealed Class**: `TransactionStatus` dengan `when` yang exhaustive
- **Smart Casting & Null Safety**: operator `is`, `as?`, dan tipe nullable (`Item?`, `Member?`, `Transaction?`)

---

## 3. Cara Menjalankan Program

### Prasyarat

- JDK 8 atau lebih baru
- Kotlin compiler (`kotlinc`), atau IntelliJ IDEA

### Menggunakan IntelliJ IDEA

1. Buka IntelliJ IDEA, pilih **File > Open**, lalu pilih folder proyek ini.
2. Pastikan seluruh file `.kt` berada di dalam folder `src/` dan dikenali sebagai source root.
3. Buka file `src/Main.kt`.
4. Klik ikon **Run** (segitiga hijau) di samping `fun main()`, atau tekan `Shift + F10`.
5. Output program akan tampil di jendela **Run** pada bagian bawah.


## 4. Struktur Folder

```
Tugas_Mandiri_OOP_NIM_Nama/
├── src/
│   ├── Main.kt
│   ├── Item.kt
│   ├── Book.kt
│   ├── Journal.kt
│   ├── DVD.kt
│   ├── TransactionStatus.kt
│   ├── Transaction.kt
│   ├── Member.kt
│   └── Library.kt
├── docs/
│   └── Laporan_Tugas_Mandiri.pdf
└── README.md
```

### 5.3 Ringkasan Tiap Kelas

| Kelas | File | Peran |
| ----- | ---- | ----- |
| `Item` | `Item.kt` | Kelas induk abstrak. Menyimpan `id`, `title`, `year`, dan status `isAvailable` (`private set`). Menyediakan `borrow()`, `returnItem()`, dan `displayInfo()` (`open`). |
| `Book` | `Book.kt` | Turunan `Item`. Tambahan: `author`, `pages`, `genre`. Denda Rp2.000/hari, maks. 14 hari. |
| `Journal` | `Journal.kt` | Turunan `Item`. Tambahan: `publisher`, `volume`, `issueNumber`. Denda Rp3.000/hari, maks. 7 hari. |
| `DVD` | `DVD.kt` | Turunan `Item`. Tambahan: `director`, `duration`, `genre`. Denda Rp5.000/hari, maks. 3 hari. |
| `TransactionStatus` | `TransactionStatus.kt` | Sealed class berisi status `Borrowed`, `Returned`, `Overdue(daysLate)`, dan `Cancelled`. Memiliki `display()` (abstrak) dan `isFinal()`. |
| `Transaction` | `Transaction.kt` | Menghubungkan item, anggota, tanggal pinjam, dan status. Menangani `returnItem()`, `cancel()`, dan `displayTransaction()`. |
| `Member` | `Member.kt` | Anggota perpustakaan. `email` dan `phone` bersifat `private val` (diakses lewat `getEmail()` / `getPhone()`). Membatasi maksimal 3 peminjaman aktif dan menghitung `totalFines`. |
| `Library` | `Library.kt` | Kelas pengelola utama: menyimpan daftar item, anggota, dan transaksi; menyediakan pencarian, registrasi, peminjaman, pengembalian, serta laporan. |
| `Main` | `Main.kt` | Fungsi `main()` yang mendemonstrasikan seluruh skenario wajib. |

---

