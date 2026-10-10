class persegiPanjang(var panjang: Double, var lebar: Double){

    //Menghitung luas (panjang * lebar)
    fun hitungLuas(): Double{
        return panjang * lebar
    }

    //Mengubah panjang dan lebar sekaligus
    fun ubahUkuran(panjangBaru: Double, lebarBaru: Double){
        panjang = panjangBaru
        lebar   = lebarBaru
    }

    //Memeriksa apakah bentuknya persegi atau bukan (panjang == lebar)
    fun isSquare(): Boolean {
        return panjang == lebar
    }
}

fun main(){
    //Membuat objek dengan panjang 10.0 dan lebar 5.0
    val persegiPanjang = persegiPanjang(5.0, 5.0)
    //Menampilkan ukuran dan luas awal
    println("===========Ukuran Awal===========")
    println("Panjang: ${persegiPanjang.panjang}")
    println("Lebar: ${persegiPanjang.lebar}")
    println("Luas Awal: ${persegiPanjang.hitungLuas()}")
    println("Bentuk: ${if (persegiPanjang.isSquare())"Berbentuk persegi" else "Berbentuk persegi panjang"}")
    println("---------------------------------")

    //Ubah Ukuran
    persegiPanjang.ubahUkuran(10.0, 10.0)

    //Menampilkan Ukuran dan Luas Baru
    println("======Ukuran Setelah Diubah======")
    println("Panjang Baru: ${persegiPanjang.panjang}")
    println("Lebar Baru: ${persegiPanjang.lebar}")
    println("Luas Baru: ${persegiPanjang.hitungLuas()}")
    println("Bentuk: ${if (persegiPanjang.isSquare())"Berbentuk persegi" else "Berbentuk persegi panjang"}")
    println("---------------------------------")
}