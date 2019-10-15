package com.umb.appudit.features.evaluation.data

import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.standard.data.entities.Standard

interface EvaluationDataSource {

    fun getData(id: String, callback: Callback)

    fun addQuestion(body: String, asnwers: ArrayList<String>)

    fun editQuestion()

    fun deleteQuestion()

    interface Callback {

        fun getDataSuccessful(standard: Standard)

        fun getDataError()

    }

}