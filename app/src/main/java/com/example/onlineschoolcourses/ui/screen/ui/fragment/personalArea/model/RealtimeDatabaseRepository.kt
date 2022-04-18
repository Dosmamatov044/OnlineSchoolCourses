package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.model.CommonModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation.model.UserModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


interface RealtimeDatabaseRepository {

   fun fetchUserMaterials(childText:String) : Flow<Result<MutableList<PersonalAreaMyCourseModel>>>
   fun fetchUserInfo(childText:String) : Flow<Result<MutableList<UserModel>>>
   fun fetchFromTwoTableData(childText:String) : Flow<Result<MutableList<CommonModel>>>
}