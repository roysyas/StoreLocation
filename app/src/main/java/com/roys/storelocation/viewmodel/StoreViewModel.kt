package com.roys.storelocation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roys.storelocation.model.StoreEntity
import com.roys.storelocation.model.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val repository: StoreRepository): ViewModel() {

    val stores = repository.stores

    fun getStore(id:Long):LiveData<StoreEntity>{
        return repository.store(id)
    }

    fun insert(storeEntity: StoreEntity) = viewModelScope.launch {
        repository.insert(storeEntity)
    }

    fun delete(storeEntity: StoreEntity) = viewModelScope.launch {
        repository.delete(storeEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}