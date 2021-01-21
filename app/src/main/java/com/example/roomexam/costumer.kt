package com.example.roomexam

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class costumer(
   @ColumnInfo(name="name") // veritabanındaki kolon adı
   var name: String,        //  kod içerisinde kullanacağımız isim, ikisi farklı olabilir - sutun adı name olacak.

   @ColumnInfo(name="lastName")
   var lastName:String,

   @ColumnInfo(name="birthplace")
   var birthplace:String,

){
   @PrimaryKey(autoGenerate = true) // costurmerID'nin otomatik artması için true yaptık.
   var costurmerID : Int = 0        // id değeri 0'dan başlayacak ve artacak.
}