package com.example.onlineschoolcourses.di

import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepositoryImpl
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesRealtimeDatabaseRepository(@FirebaseModule.UsersReference databaseReference: DatabaseReference): RealtimeDatabaseRepository {

        return RealtimeDatabaseRepositoryImpl(databaseReference)
    }


}