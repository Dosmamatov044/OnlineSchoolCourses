package com.example.onlineschoolcourses.helpers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

lateinit var auth: FirebaseAuth
lateinit var storageRef:StorageReference
lateinit var storageRefImages:StorageReference
lateinit var storageRefFiles:StorageReference
lateinit var storageRefUserImages:StorageReference
lateinit var storageRefUserDiplomas:StorageReference

lateinit var myRef: DatabaseReference