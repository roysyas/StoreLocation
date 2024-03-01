package com.roys.storelocation.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class StoreRepository @Inject constructor(private val dao: StoreDao) {

    val stores = dao.getAllStore()

    fun store(id: Long):LiveData<StoreEntity>{
        return dao.getStore(id)
    }

    suspend fun insert(storeEntity: StoreEntity){
        dao.insert(storeEntity)
    }

    suspend fun delete(storeEntity: StoreEntity){
        dao.delete(storeEntity)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}