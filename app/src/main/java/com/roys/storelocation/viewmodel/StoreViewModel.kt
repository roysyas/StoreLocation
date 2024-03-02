package com.roys.storelocation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roys.storelocation.R
import com.roys.storelocation.model.Event
import com.roys.storelocation.model.StoreEntity
import com.roys.storelocation.model.StoreRepository
import com.roys.storelocation.util.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val repository: StoreRepository): ViewModel() {

    private val statusMessage = MutableLiveData<Event<StringValue>>()
    val message : LiveData<Event<StringValue>>
        get() = statusMessage

    val stores = repository.stores

    fun getStore(id:Long):LiveData<StoreEntity>{
        return repository.store(id)
    }

    fun save(storeEntity: StoreEntity){
        if(storeEntity.NAME?.isEmpty() == true){
            statusMessage.value = Event(StringValue.StringResource(R.string.name_validation))
        }else{
            statusMessage.value = Event(StringValue.StringResource(R.string.success))
            insert(storeEntity)
        }
    }
    private fun insert(storeEntity: StoreEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(storeEntity)
    }

    fun delete(storeEntity: StoreEntity) = viewModelScope.launch {
        repository.delete(storeEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}