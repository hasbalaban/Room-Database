package com.example.roomexam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//arrayOf içerisine entitiesleri ekliyoruz, birden fazla ise virgül koyarak diğer entitiesleri ekliyoruz.
@Database(entities = arrayOf(costumer::class), version = 1)
abstract class CostumerDatabase : RoomDatabase() {
    abstract fun costumerDao(): CostumerDao // nesne oluşturmamak için abstract oluşturuyoz.

    companion object {
        @Volatile // Volatile anahtar kelimesi Bu değişkenin her zaman her yerde aynı içeriğe sahip olmasını sağlar.
        private var instance: CostumerDatabase? = null // daha sonra inşa edeceğiz.
        private val lock = Any() // synchronized için

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance
                    ?: makeDatabase(context).also { // instance inşa edilmediyse MakeDatebase fonksiyonu ile yap ardından
                        // bunu instance at
                        instance = it
                    }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                CostumerDatabase::class.java,
                "magazaDatabase"

        ).build()

    }
}