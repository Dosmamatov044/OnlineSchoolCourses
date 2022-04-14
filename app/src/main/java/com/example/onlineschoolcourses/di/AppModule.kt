package com.example.onlineschoolcourses.di

import android.content.Context
import androidx.room.Room
import com.example.onlineschoolcourses.room.SaveDataDataBase
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToDoDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, SaveDataDataBase::class.java,
        "MyRoomNames"
    ).build()

    @Singleton
    @Provides
    fun provideToDoDao(
        db: SaveDataDataBase
    ) = db.saveDataDao()






}

/*@InstallIn(ViewModelComponent::class)
@Module
abstract class MyModule{



    @Binds
    abstract fun bindSomeDependency(realtimeDatabaseRepository: RealtimeDatabaseRepositoryImpl):RealtimeDatabaseRepository
}
*/


