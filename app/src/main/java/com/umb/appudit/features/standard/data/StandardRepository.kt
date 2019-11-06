package com.umb.appudit.features.standard.data



import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.umb.appudit.features.standard.data.entities.Standard
import java.util.UUID
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
            override fun onDataChange(dataSnapshot: DataSnapshot){
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

    override fun escribirDataNueva() {
    
        var standard = Standard()
        standard.id = UUID.randomUUID().toString()
        standard.name = "estandar 1"
        standard.code = "12365486442313"
        standard.version = "2.1.8"
        standard.definition =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In cursus ante bibendum ipsum blandit, non fringilla turpis ullamcorper. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras tempor suscipit arcu, ac bibendum odio. Etiam feugiat scelerisque lacinia. Integer eget ipsum imperdiet, placerat mauris quis, mollis enim. Duis at nisi lacus. Maecenas interdum volutpat leo sit amet eleifend. Integer risus nulla, consequat nec rutrum vel, accumsan vel libero. Mauris varius magna faucibus commodo posuere. Morbi malesuada"
        databaseReference?.child(NODO)?.child(standard.id!!)?.setValue(standard)
        standard = Standard()
        standard.id = UUID.randomUUID().toString()
        standard.name = "estandar 2"
        standard.code = "542456456486456"
        standard.version = "2.2.3"
        standard.definition =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In cursus ante bibendum ipsum blandit, non fringilla turpis ullamcorper. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras tempor suscipit arcu, ac bibendum odio. Etiam feugiat scelerisque lacinia. Integer eget ipsum imperdiet, placerat mauris quis, mollis enim. Duis at nisi lacus. Maecenas interdum volutpat leo sit amet eleifend. Integer risus nulla, consequat nec rutrum vel, accumsan vel libero. Mauris varius magna faucibus commodo posuere. Morbi malesuada"
        databaseReference?.child(NODO)?.child(standard.id!!)?.setValue(standard)
        standard = Standard()
        standard.id = UUID.randomUUID().toString()
        standard.name = "estandar 3"
        standard.code = "4532131286445312"
        standard.version = "1.5.9"
        standard.definition =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In cursus ante bibendum ipsum blandit, non fringilla turpis ullamcorper. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras tempor suscipit arcu, ac bibendum odio. Etiam feugiat scelerisque lacinia. Integer eget ipsum imperdiet, placerat mauris quis, mollis enim. Duis at nisi lacus. Maecenas interdum volutpat leo sit amet eleifend. Integer risus nulla, consequat nec rutrum vel, accumsan vel libero. Mauris varius magna faucibus commodo posuere. Morbi malesuada"
        databaseReference?.child(NODO)?.child(standard.id!!)?.setValue(standard)   }
}