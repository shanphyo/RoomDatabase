package com.mic.tasknotwo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = arrayOf(Product::class),version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao
    companion object {
        private var INSTANCE: ProductDatabase? = null
        fun getDatabase(context: Context): ProductDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, ProductDatabase::class.java, "product_database")
                        .allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}