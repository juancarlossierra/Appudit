package com.umb.appudit.features.evaluation.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EvaluationRepository : EvaluationDataSource {

    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

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

    override fun getData(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addQuestion(body: String, asnwers: ArrayList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initializeFireBase(context: Context) {
        FirebaseApp.initializeApp(context)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.reference
    }

}