package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation.updateInformation

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.onlineschoolcourses.helpers.replaceFragmentScreen
import com.example.onlineschoolcourses.helpers.storageRefUserDiplomas
import com.example.onlineschoolcourses.helpers.storageRefUserImages
import com.example.onlineschoolcourses.sealed.State
import com.example.onlineschoolcourses.ui.register.repository.DataStoreRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.HomeFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.PersonalAreaFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation.model.UserModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.RealtimeDatabaseRepositoryImpl
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class UpdateInformationAboutUserViewModel @Inject constructor(
    application: Application,
    var dataStoreRepository: DataStoreRepository, var _realtimeDatabaseRepository: RealtimeDatabaseRepositoryImpl
) : AndroidViewModel(application) {
    private val _userInfoData = MutableSharedFlow<MutableList<UserModel>>()
    val userInfoData=_userInfoData.asSharedFlow()
    private val database = Firebase.database
    init {
        dataStoreRepository = DataStoreRepository(getApplication())

    viewModelScope.launch {

        dataStoreRepository.readKey.collect{

            fetchUserInfo(it)
        }
    }
    }


    val readKey= dataStoreRepository.readKey.asLiveData()






    @ExperimentalCoroutinesApi
    fun fetchUserInfo(key:String) {
        viewModelScope.launch {

            _realtimeDatabaseRepository.fetchUserInfo(key).collect {
                when {
                    it.isSuccess -> {
                        it.getOrNull()?.let { it1 ->


                            _userInfoData.emit(it1) }



                    }
                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun initFireBase(name:String,surname:String,
        career: String,
        experience: String,
        description: String,
        spinnerText: String?,
        diplomaAndCertificateUri: Uri?,
        userUri: Uri?,
        key:String
    ) {


        if (career.isNotEmpty() && experience.isNotEmpty() && description.isNotEmpty()&&name.isNotEmpty()&&surname.isNotEmpty()) {
            storageRefUserImages = Firebase.storage.reference.child("${key}").child(userUri.toString())
            storageRefUserDiplomas = Firebase.storage.reference.child("${key}").child(diplomaAndCertificateUri.toString())




            //storageRef = Firebase.storage.getReference("userData$key")

            storageRefUserImages.downloadUrl.addOnSuccessListener { userImgUri ->
                storageRefUserDiplomas.downloadUrl.addOnSuccessListener { userDiplomasUri ->
                    val user= HashMap<String, Any>()
                    user["name"] = name
                    user["surname"] = surname
                    user["profession"] = spinnerText.toString()
                    user["user_image"] = userImgUri.toString()
                    user["career"] = career
                    user["experience"] = experience
                    user["diplomaAndCertificateDescription"] = description
                    user["diplomaAndCertificateImages"] = userDiplomasUri.toString()

                    val   myRefs = database.reference.child("users")
                    myRefs.child(key).updateChildren(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            replaceFragmentScreen(PersonalAreaFragment())
                            Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG)
                                .show()

                        } else {

                            Toast.makeText(
                                getApplication(),
                                it.exception?.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }.addOnFailureListener {
                        Log.d("ololo", it.message.toString())
                    }


                }}



        }else{
            Toast.makeText(getApplication(),"Заполните поля", Toast.LENGTH_LONG).show()
        }}




    @OptIn(InternalCoroutinesApi::class)
    fun uploadStorageDataDiplomasImages(
        key: String,
        diplomaAndCertificateUri: Uri,
    ) {

        viewModelScope.launch {
            _realtimeDatabaseRepository.uploadStorageData(diplomaAndCertificateUri,key).collect { state ->
                when (state) {
                    is State.Loading -> {
                        Toast.makeText(getApplication(),"Загрузка", Toast.LENGTH_SHORT).show()
                    }

                    is State.Success -> {
                        Toast.makeText(getApplication(),"Загружен", Toast.LENGTH_SHORT).show()



                    }

                    is State.Failed -> {
                        Toast.makeText(getApplication(),"Ошибка", Toast.LENGTH_SHORT).show()
                    }
                }

            }


        }



    }

    @OptIn(InternalCoroutinesApi::class)
    fun uploadStorageDataUserImages(
        key: String,
        userUri: Uri,
    ) {
        viewModelScope.launch {


            _realtimeDatabaseRepository.uploadStorageDataUserImages(userUri, key).collect { state ->
                when (state) {
                    is State.Loading -> {
                        Toast.makeText(getApplication(), "Загрузка", Toast.LENGTH_SHORT).show()

                    }

                    is State.Success -> {

                    }

                    is State.Failed -> {
                        Toast.makeText(getApplication(), "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }


}