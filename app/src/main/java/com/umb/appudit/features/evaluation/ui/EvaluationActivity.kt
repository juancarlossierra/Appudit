package com.umb.appudit.features.evaluation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.umb.appudit.R
import com.umb.appudit.databinding.EvaluationActivityBinding
import com.umb.appudit.features.evaluation.ui.viewmodel.EvaluationViewModel

class EvaluationActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: EvaluationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = EvaluationActivityBinding.inflate(layoutInflater)
        setContentView(viewDataBinding.root)

        viewDataBinding.viewmodel = ViewModelProviders.of(this).get(EvaluationViewModel::class.java)

        viewDataBinding.viewmodel!!.fetchSpinnerEssentialKnowledge().observe(this, Observer { spinnerData ->
            val spinnerAdapter =
                ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerData)
            viewDataBinding.spinner2.adapter = spinnerAdapter
        })
        viewDataBinding.viewmodel!!.fetchSpinnerCriterionItems().observe(this, Observer { spinnerData ->
            val spinnerAdapter =
                ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerData)
            viewDataBinding.spinner3.adapter = spinnerAdapter
        })

        //val dato = intent.getStringExtra("selectedStandard")


    }

}
