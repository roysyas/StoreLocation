package com.roys.storelocation.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.core.location.LocationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.roys.storelocation.R
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class AppTools {
    fun distance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val R = 6371 // Radius of the Earth in kilometers
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c // Distance in kilometers
    }

    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val locationRequest by lazy {
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, INTERVAL).apply {
            setWaitForAccurateLocation(true)
        }.build()
    }

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context, locationResult: com.roys.storelocation.util.LocationResult) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                p0.locations.let { locations ->
                    if (locations.isNotEmpty()) {
                        val location: Location = locations.last()
                        if(isValidLocation(location)){
                            locationResult.onSuccess(location)
                        }else{
                            locationResult.onValid(context.getString(R.string.fakegps_message))
                        }

                    }
                }

            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    fun stopLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    fun isValidLocation(location: Location?): Boolean = when {
        location == null -> false
        LocationCompat.isMock(location) -> false
        else -> true
    }

    companion object{
        const val INTERVAL:Long = 5000
    }
}