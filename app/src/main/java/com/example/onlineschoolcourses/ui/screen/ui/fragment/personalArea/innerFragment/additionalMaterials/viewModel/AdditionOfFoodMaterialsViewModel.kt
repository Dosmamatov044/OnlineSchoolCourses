package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials.viewModel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.onlineschoolcourses.helpers.myRef
import com.example.onlineschoolcourses.helpers.storageRef
import com.example.onlineschoolcourses.helpers.storageRefFiles
import com.example.onlineschoolcourses.helpers.storageRefImages
import com.example.onlineschoolcourses.ui.register.repository.DataStoreRepository
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials.model.UserModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class AdditionOfFoodMaterialsViewModel @Inject constructor(
    application: Application,
    var dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {
    val price = MutableLiveData<Array<Int>>()
    private val database = Firebase.database
    private var fileKey:String?=null
    private var imageKey:String?=null
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
        val key: String = UUID.randomUUID().toString()
        mediatorLiveData.observeForever { child ->
         val storageReference = storageRef.child("${child}").child("trainingFile").child(fileUri.toString()).child(key)
            fileKey=key
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

        storageRef = Firebase.storage.reference
        val key: String = UUID.randomUUID().toString()
        mediatorLiveData.observeForever { child ->
          val storageReference=storageRef.child ("${child}").child("trainingImage").child(imageUri.toString()).child(key)
            imageKey=key
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
        mediatorLiveData.observeForever { key ->
            fileKey?.let {
                storageRefFiles = Firebase.storage.reference.child("${key}").child("trainingFile").child(fileUri.toString()).child(it)
            }
             imageKey?.let {
                 storageRefImages=Firebase.storage.reference.child("${key}").child("trainingImage").child(coursePhoto.toString()).child(it)
             }



        storageRefFiles.downloadUrl.addOnSuccessListener { fileUris ->
        storageRefImages.downloadUrl.addOnSuccessListener { imageUris ->


            val referenceFromUser = database.reference.child("users").child("-N-bCamAfGa8tH_MVs9-")

            referenceFromUser.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                   val models = snapshot.getValue(UserModels::class.java)


                    models?.let {
                        Log.d("mmm", it.career)

                    }
















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
                    user["timeStamp"]=ServerValue.TIMESTAMP
                    models?.let {
                        user["user_career"]=it.career
                        user["user_diplomaAndCertificateDescription"]=it.diplomaAndCertificateDescription
                        user["user_diplomaAndCertificateImages"]=it.diplomaAndCertificateImages
                        user["user_email"]=it.email
                        user["user_experience"]=it.experience
                        user["user_key"]=it.key
                        user["user_name"]=it.name
                        user["user_profession"]=it.profession
                        user["user_professionPosition"]=it.professionPosition
                        user["user_surname"]=it.surname
                        user["user_image"]=it.user_image

                    }


                    mediatorLiveData.observeForever { child ->
                        myRef.child(child).child(UUID.randomUUID().toString()).setValue(user).addOnCompleteListener {

                            Log.d("ololo", it.toString())
                        }.addOnFailureListener {
                            Log.d("ololo", it.message.toString())
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("mmmm", error.message)
                }


            })





        }
        }}   }
}



