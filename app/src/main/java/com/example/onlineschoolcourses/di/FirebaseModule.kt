package com.example.onlineschoolcourses.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase():FirebaseDatabase{

       return FirebaseDatabase.getInstance()
    }
   @UsersReference
    @Provides
    @Singleton
    fun provideFirebaseDatabaseReference(firebaseDatabase: FirebaseDatabase):DatabaseReference{

        return firebaseDatabase.reference.child("usersMaterials")
    }




    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsersReference


}