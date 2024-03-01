package com.roys.storelocation.di

import android.app.Application
import com.roys.storelocation.model.AppDatabase
import com.roys.storelocation.model.StoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getAppDb(context: Application): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun getAppDao(appdb: AppDatabase): StoreDao {
        return appdb.storeDao()
    }
}