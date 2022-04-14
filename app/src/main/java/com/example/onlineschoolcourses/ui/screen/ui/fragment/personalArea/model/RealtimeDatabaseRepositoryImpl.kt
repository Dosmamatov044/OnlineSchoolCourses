package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model

import android.app.Application
import android.net.Uri
import android.util.Log
import com.example.onlineschoolcourses.di.FirebaseModule
import com.example.onlineschoolcourses.helpers.storageRef
import com.example.onlineschoolcourses.sealed.State
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation.model.UserModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RealtimeDatabaseRepositoryImpl @Inject constructor(@FirebaseModule.UsersReference
   val databaseReference: DatabaseReference
) : RealtimeDatabaseRepository {





  /*  @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchNotifications() = callbackFlow<Result<MutableList<PersonalAreaMyCourseModel>>> {

        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<PersonalAreaMyCourseModel>()

                val ti = object : GenericTypeIndicator<HashMap<String?, Any?>?>() {}

                val users: Map<String?, Any?>? = snapshot.getValue(ti)


                //iterate through each user, ignoring their UID
                if (users != null) {


                    for ((_, value) in users) {

                        //Get user map
                        val singleUser = value as Map<*, *>

                        list.add(
                            PersonalAreaMyCourseModel(
                                singleUser["course_image"] as String,
                                singleUser["courseNames"] as String,
                                singleUser["profession"].toString(),
                                singleUser["price"].toString().toInt()
                            )

                        )

                    }

                    this@callbackFlow.trySendBlocking(Result.success(list))
                }


            }
        }


        databaseReference.addValueEventListener(postListener)
        awaitClose {

               databaseReference.removeEventListener(postListener)
        }
    }.flowOn(Dispatchers.IO)
*/

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchNotifications(childText:String) = callbackFlow<Result<MutableList<PersonalAreaMyCourseModel>>> {
        val getDataFromUsersMaterials =
            FirebaseDatabase.getInstance().reference.child("usersMaterials").child(childText)
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<PersonalAreaMyCourseModel>()

                list.clear()

                val msg = snapshot.getValue(PersonalAreaMyCourseModel::class.java)

                if (msg != null) {
                        list.add(msg)
                    }
                this@callbackFlow.trySendBlocking(Result.success(list))
        }
        }
        getDataFromUsersMaterials.addValueEventListener(postListener)
        awaitClose {

            getDataFromUsersMaterials.removeEventListener(postListener)
        }
    }.flowOn(Dispatchers.IO)




    override fun fetchUserInfo(childText: String)=callbackFlow<Result<MutableList<UserModel>>> {
        val getDataFromUsersMaterials =
            FirebaseDatabase.getInstance().reference.child("users").child(childText)
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<UserModel>()

                list.clear()

                val msg = snapshot.getValue(UserModel::class.java)

                if (msg != null) {
                    list.add(msg)
                }
                this@callbackFlow.trySendBlocking(Result.success(list))
            }
        }
        getDataFromUsersMaterials.addValueEventListener(postListener)
        awaitClose {

            getDataFromUsersMaterials.removeEventListener(postListener)
        }
    }.flowOn(Dispatchers.IO)







    @OptIn(ExperimentalCoroutinesApi::class)
    fun uploadStorageData(diplomaAndCertificateUri: Uri, key: String) = callbackFlow<State<String>> {
      val  storageRef = Firebase.storage.reference

            val storageReference = storageRef.child("${key}").child(diplomaAndCertificateUri.toString())


       storageReference.putFile(diplomaAndCertificateUri).addOnSuccessListener {
            trySendBlocking(State.success("Успех"))
        }.addOnProgressListener {
            val progressPercent=(100.00*it.bytesTransferred/it.totalByteCount)
            trySendBlocking(State.loading())

        }.addOnFailureListener{
            trySendBlocking(State.failed(it.message.toString()))
        }






        awaitClose {


        }

    }.flowOn(Dispatchers.IO)


    @OptIn(ExperimentalCoroutinesApi::class)
    fun uploadStorageDataUserImages(userUri: Uri, key: String) = callbackFlow<State<String>> {
        val  storageRef = Firebase.storage.reference

        val storageReference = storageRef.child("${key}").child(userUri.toString())


        storageReference.putFile(userUri).addOnSuccessListener {
            trySendBlocking(State.success("Успех"))
        }.addOnProgressListener {
            val progressPercent=(100.00*it.bytesTransferred/it.totalByteCount)
            trySendBlocking(State.loading())

        }.addOnFailureListener{
            trySendBlocking(State.failed(it.message.toString()))
        }





        awaitClose {


        }

    }.flowOn(Dispatchers.IO)



   /* fun addPost(post: Post) = flow<State<DocumentReference>> {

        // Emit loading state
        emit(State.loading())

        val postRef = mPostsCollection.add(post).await()

        // Emit success state with post reference
        emit(State.success(postRef))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

*/



}