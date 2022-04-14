package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials.viewModel

import android.app.Application
import android.app.ProgressDialog
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.onlineschoolcourses.helpers.myRef
import com.example.onlineschoolcourses.helpers.storageRef
import com.example.onlineschoolcourses.helpers.storageRefFiles
import com.example.onlineschoolcourses.helpers.storageRefImages
import com.example.onlineschoolcourses.ui.register.repository.DataStoreRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AdditionOfFoodMaterialsViewModel @Inject constructor(
    application: Application,
    var dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {
    val price = MutableLiveData<Array<Int>>()
    private val database = Firebase.database
    private val priceFlow = flow {
        var array = emptyArray<Int>()

        for (i in 0..500) {

            array += i
            emit(array)
        }

    }.flowOn(Dispatchers.IO)

    private val readKey = dataStoreRepository.readKey.asLiveData()
    private val mediatorLiveData = MediatorLiveData<String>()

    init {
        dataStoreRepository = DataStoreRepository(getApplication())
        collectFlow()

        mediatorLiveData.addSource(readKey, Observer {
            mediatorLiveData.value = it

        })


    }


    private fun collectFlow() {
        viewModelScope.launch {
            priceFlow.collect {
                price.value = it

            }
        }

    }


    fun storageRefFileAdd(fileUri: Uri?) {

        storageRef = Firebase.storage.reference
        mediatorLiveData.observeForever { child ->
         val storageReference = storageRef.child("${child}").child("trainingFile").child(fileUri.toString())
            if (fileUri != null) {
                storageReference.putFile(fileUri).addOnSuccessListener {
                    Log.d("статус", "успех")
                }.addOnProgressListener {
                    val progressPercent = (100.00 * it.bytesTransferred / it.totalByteCount)
                    Log.d("precent", progressPercent.toString())

                }.addOnFailureListener {
                    Log.d("precent", it.message.toString())
                }
            }
        }
    }

    fun storageRefImageAdd(imageUri: Uri?) {
        val progressDialog = ProgressDialog(getApplication())
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        // progressDialog.show()
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        storageRef = Firebase.storage.reference
        mediatorLiveData.observeForever { child ->
          val storageReference=storageRef.child ("${child}").child("trainingImage").child(imageUri.toString())
            if (imageUri != null) {
                storageReference.putFile(imageUri).addOnSuccessListener {
                    Log.d("статус", "успех")
                }.addOnProgressListener {
                    val progressPercent = (100.00 * it.bytesTransferred / it.totalByteCount)
                    Log.d("precent", progressPercent.toString())

                }.addOnFailureListener {
                    Log.d("precent", it.message.toString())
                }
            }
        }
    }

    fun realtimeDataBaseAdd(
        coursePhoto: Uri?,
        courseNames: String,
        description: String,
        professionSpinner: String?,
        priceSpinner: String?,
        youtubeUrl: String?,
        fileUri: Uri?,
        homework: String,
        test: String
    ) {
        myRef = database.reference.child("usersMaterials")
        mediatorLiveData.observeForever { child ->
            storageRefFiles = Firebase.storage.reference.child("${child}").child("trainingFile").child(fileUri.toString())
                 storageRefImages=Firebase.storage.reference.child("${child}").child("trainingImage").child(coursePhoto.toString())
        }

        storageRefFiles.downloadUrl.addOnSuccessListener { fileUris ->
        storageRefImages.downloadUrl.addOnSuccessListener { imageUris ->


            val user = HashMap<String, Any>()
            user["course_image"] = imageUris.toString()
            user["courseNames"] = courseNames
            user["description"] = description
            user["profession"] = professionSpinner.toString()
            user["price"] = priceSpinner.toString()
            user["youtubeUrl"] = youtubeUrl.toString()

            user["file"] = fileUris.toString()

            user["homeWork"] = homework
            user["test"] = test

            mediatorLiveData.observeForever { child ->
                myRef.child(child).setValue(user).addOnCompleteListener {
                    Log.d("ololo", it.toString())
                }.addOnFailureListener {
                    Log.d("ololo", it.message.toString())
                }
            }

        }
    }}
}



