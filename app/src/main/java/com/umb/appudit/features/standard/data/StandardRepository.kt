package com.umb.appudit.features.standard.data

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.umb.appudit.features.standard.data.entities.Standard
import java.util.*
import kotlin.collections.ArrayList

class StandardRepository : StandardDataSource {

    private val NODO = "standard"
    private val TAG = "standard"
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

    companion object {
        private var INSTANCE: StandardRepository? = null

        fun getInstance(context: Context): StandardRepository? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = StandardRepository()
                INSTANCE?.initializeFireBase(context)
            }
            return INSTANCE
        }
    }

    private fun initializeFireBase(context: Context) {
        FirebaseApp.initializeApp(context)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.reference
    }

    override fun getStandards(callback: StandardDataSource.GetDataCallback) {
        val standards = ArrayList<Standard>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {a ->
                    a.getValue(Standard::class.java)?.let { standards.add(it) }
                }
                callback.getDataSuceSuccessfully(standards)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                callback.getDataError()
            }
        }
        databaseReference?.child(NODO)?.addValueEventListener(postListener)
    }

}