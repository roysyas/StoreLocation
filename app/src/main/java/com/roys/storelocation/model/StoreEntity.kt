package com.roys.storelocation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "STORE_TABLE")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    val ID: Long?,
    val NAME: String? = null,
    val STORELAT : Double,
    val STORELON : Double,
    val USERLAT : Double,
    val USERLON : Double,
    val IMAGE: String? = null
)