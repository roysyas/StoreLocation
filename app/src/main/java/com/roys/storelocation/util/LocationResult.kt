package com.roys.storelocation.util

import android.location.Location

interface LocationResult {
    fun onSuccess(location: Location)
    fun onValid(message: String)
}