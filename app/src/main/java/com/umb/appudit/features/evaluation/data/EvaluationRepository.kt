package com.umb.appudit.features.evaluation.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.umb.appudit.features.evaluation.data.entities.Criterion
import com.umb.appudit.features.evaluation.data.entities.EssentialKnowledge
import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.evaluation.data.entities.Question
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

    fun loadTestData(): Knowledge {
        val questions = ArrayList<Question>()

        questions.add(createTestQuestion(
            "question number one",
            listOf("correct option", "option two", "option three", "option four"),
            0))

        questions.add(createTestQuestion(
            "question number two",
            listOf("option one", "correct option", "option three", "option four"),
            1))

        questions.add(createTestQuestion(
            "question number three",
            listOf("option one", "option two", "correct option", "option four"),
            2))

        val criteria = ArrayList<Criterion>()

        criteria.add(createTestCriterion(1, questions))
        criteria.add(createTestCriterion(2, questions))
        criteria.add(createTestCriterion(3, questions))

        val criteria2 = ArrayList<Criterion>()
        criteria2.add(createTestCriterion(4, questions))
        criteria2.add(createTestCriterion(5, questions))
        criteria2.add(createTestCriterion(6, questions))


        val essentialKnowledge = ArrayList<EssentialKnowledge>()
        essentialKnowledge.add(createTestEssentialKnowledge("Essential knowledge one", criteria))
        essentialKnowledge.add(createTestEssentialKnowledge("Essential knowledge two", criteria2))

        val knowledge = Knowledge()
        knowledge!!.essentialKnowledges = essentialKnowledge
        return knowledge
    }

    private fun createTestQuestion(body: String, options: List<String>, answerIndex: Int): Question {
        val question = Question()
        question.body = body
        question.options = options
        question.answer = answerIndex
        return question
    }

    private fun createTestCriterion(index: Int, questions: List<Question>): Criterion {
        var criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thought category"
        criterion.questions = questions as ArrayList
        criterion.description = "criterion $index"
        return criterion
    }

    private fun createTestEssentialKnowledge(description: String, criteria: List<Criterion>): EssentialKnowledge {
        var essentialKnowledge = EssentialKnowledge()
        essentialKnowledge.description = description
        essentialKnowledge.criterion = criteria
        return essentialKnowledge
    }
}