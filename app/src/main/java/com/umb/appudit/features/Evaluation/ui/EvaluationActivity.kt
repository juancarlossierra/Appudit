package com.umb.appudit.features.Evaluation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.umb.appudit.R
import com.umb.appudit.features.Evaluation.ui.viewmodel.EvaluationViewModel

class EvaluationActivity : AppCompatActivity() {

    private lateinit var viewModel: EvaluationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluation_activity)

        viewModel = ViewModelProviders.of(this).get(EvaluationViewModel::class.java)

        val dato = intent.getStringExtra("selectedStandard")
        Log.d("prueba",dato+" "+viewModel.prueba())


    }

}
