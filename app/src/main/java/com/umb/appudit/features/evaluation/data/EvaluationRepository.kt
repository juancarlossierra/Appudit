package com.umb.appudit.features.evaluation.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.umb.appudit.features.evaluation.data.entities.Criterion
import com.umb.appudit.features.evaluation.data.entities.EssentialKnowledge
import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.evaluation.data.entities.Question

class EvaluationRepository : EvaluationDataSource {

    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

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
        knowledge = loadTestData()
        callback.getDataSuccessful(knowledge!!)
    }

    override fun addQuestion(body: String, asnwers: ArrayList<String>) {

    }

    override fun editQuestion() {

    }

    override fun deleteQuestion() {

    }

    private fun initializeFireBase(context: Context) {
        FirebaseApp.initializeApp(context)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.reference
    }

    fun loadTestData(): Knowledge {
        var question = Question()
        question.body = "question number one"
        var options = ArrayList<String>()
        options.add("correct option")
        options.add("option two")
        options.add("option three")
        options.add("option four")
        question.options = options
        question.answer = 0
        val questions = ArrayList<Question>()
        questions.add(question)

        question = Question()
        question.body = "question number two"
        options = ArrayList<String>()
        options.add("option one")
        options.add("correct option")
        options.add("option three")
        options.add("option four")
        question.options = options
        question.answer = 1
        questions.add(question)

        question = Question()
        question.body = "question number three"
        options = ArrayList<String>()
        options.add("option one")
        options.add("option two")
        options.add("correct option")
        options.add("option four")
        question.options = options
        question.answer = 2
        questions.add(question)

        val criterions = ArrayList<Criterion>()
        var criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 1"
        criterions.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 2"
        criterions.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 3"
        criterions.add(criterion)

        val criterions2 = ArrayList<Criterion>()
        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 4"
        criterions2.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 5"
        criterions2.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 6"
        criterions2.add(criterion)

        var essentialKnowledge = EssentialKnowledge()
        essentialKnowledge.description = "Essential knowledge one"
        essentialKnowledge.criterion = criterions
        val essentialKnowledges = ArrayList<EssentialKnowledge>()
        essentialKnowledges.add(essentialKnowledge)
        essentialKnowledge = EssentialKnowledge()
        essentialKnowledge.description = "Essential knowledge two"
        essentialKnowledge.criterion = criterions2
        essentialKnowledges.add(essentialKnowledge)

        val knowledge = Knowledge()
        knowledge!!.essentialKnowledges = essentialKnowledges
        return knowledge
    }

}