package com.umb.appudit.features.evaluation.data

import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.standard.data.entities.Standard

interface EvaluationDataSource {

    fun getData(id: String, callback: Callback)

    interface Callback {

        fun getDataSuccessful(standard: Standard)

        fun getDataError()

    }

}