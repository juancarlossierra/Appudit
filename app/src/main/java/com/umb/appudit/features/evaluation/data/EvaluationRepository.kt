package com.umb.appudit.features.evaluation.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.standard.data.entities.Standard

class EvaluationRepository : EvaluationDataSource {

    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private val NODO = "standard"
    private val TAG = "standard"

    var knowledge: Knowledge? = null

    companion object {
        private var INSTANCE: EvaluationRepository? = null

        fun getInstance(context: Context): EvaluationRepository? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = EvaluationRepository()
                INSTANCE?.initializeFireBase(context)
            }
            return INSTANCE
        }
    }

    override fun getData(id: String, callback: EvaluationDataSource.Callback) {
        val standards = ArrayList<Standard>()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { a ->
                    a.getValue(Standard::class.java)?.let { standards.add(it) }
                }
                var standard: Standard? = null
                for (x: Int in 0..standards.size) {
                    if (standards[x].id == id) {
                        standard = standards[x]
                        break
                    }
                }
                callback.getDataSuccessful(standard!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.getDataError()
            }
        }
        databaseReference?.child(NODO)?.addValueEventListener(postListener)

    }

    private fun initializeFireBase(context: Context) {
        FirebaseApp.initializeApp(context)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.reference
    }
}