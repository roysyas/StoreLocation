package com.roys.storelocation.model

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [StoreEntity::class],
    version = 6,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.Migration2to3::class),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5, spec = AppDatabase.Migration4to5::class),
        AutoMigration(from = 5, to = 6, spec = AppDatabase.Migration5to6::class)
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun storeDao() : StoreDao

    @RenameColumn(tableName = "STORE_TABLE", fromColumnName = "EMAIL", toColumnName = "STORE_EMAIL")
    class Migration2to3: AutoMigrationSpec

    @DeleteColumn(tableName = "STORE_TABLE", columnName = "data_delete")
    class Migration4to5: AutoMigrationSpec

    @RenameTable(fromTableName = "STORE_TABLE", toTableName = "STORE_DATA_TABLE")
    class Migration5to6: AutoMigrationSpec

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