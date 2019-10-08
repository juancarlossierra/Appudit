package com.umb.appudit.features.Evaluation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.umb.appudit.features.Evaluation.data.EvaluationDataSource
import com.umb.appudit.features.Evaluation.data.EvaluationRepository

class EvaluationViewModel(aplication: Application) : AndroidViewModel(aplication) {

    var evaluationRepository: EvaluationDataSource? = null

    fun start() {
        evaluationRepository = EvaluationRepository.getInstance(getApplication())

    }

}
