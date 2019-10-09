package com.umb.appudit.features.Evaluation.data

interface EvaluationDataSource {

    fun getData(id: String)

    fun addQuestion(body: String, asnwers: ArrayList<String>)

}