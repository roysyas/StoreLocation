package com.roys.storelocation.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoreDao {

    @Query("SELECT * FROM STORE_TABLE ORDER BY ID DESC")
    fun getAllStore(): LiveData<List<StoreEntity>>

    @Query("SELECT * FROM STORE_TABLE WHERE ID = :id")
    fun getStore(id: Long): LiveData<StoreEntity>

    @Insert
    suspend fun insert(storeEntity: StoreEntity)

    @Delete
    suspend fun delete(storeEntity: StoreEntity)

    @Query("DELETE FROM STORE_TABLE")
    suspend fun deleteAll()
}