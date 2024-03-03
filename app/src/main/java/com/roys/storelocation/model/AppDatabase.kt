package com.roys.storelocation.model

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [StoreEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2)]
    //NEXT migration [AutoMigration(from = 1, to = 2), AutoMigration(from = 2, to = 3)]
    //OR just [AutoMigration(from = 2, to = 3)]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun storeDao() : StoreDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance: AppDatabase? = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "store_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}