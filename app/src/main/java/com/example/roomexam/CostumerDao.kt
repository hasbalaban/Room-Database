package com.example.roomexam

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CostumerDao {

    @Insert // veri ekleme için oluşturduğumuz fonksiyon - insert Room kütüphanesinden gelmektedir.
    suspend fun addCostumer(costurmer: costumer) // suspend yapmamızın neden arkaplanda çalışmasını sağlamak. duraklatılabilir


    // sorgu yapmak için oluşturduğumuz fonksiyon - insert Room kütüphanesinden gelmektedir.
    @Query("SELECT * FROM costumer")
    suspend fun getAllCostumers(): List<costumer> //fonksiyon costumer türünden bir liste döndürecektir.


    @Query("select * from costumer where costurmerID = :costurmer_ID")  // where anahtarı ile costurmerID'si costurmer_ID olan veriyi istedik.
    suspend fun getOneCostumer(costurmer_ID: Int): costumer //fonksiyon costumer türünden idsini verdiğimiz veriyi döndürecektir.

}