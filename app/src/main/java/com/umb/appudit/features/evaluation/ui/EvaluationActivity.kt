package com.umb.appudit.features.evaluation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.umb.appudit.R
import com.umb.appudit.databinding.EvaluationActivityBinding
import com.umb.appudit.features.evaluation.ui.viewmodel.EvaluationViewModel

class EvaluationActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: EvaluationActivityBinding
    private var viewmodel: EvaluationViewModel? = null

    object id {
        var id = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = EvaluationActivityBinding.inflate(layoutInflater)
        setContentView(viewDataBinding.root)

        val dato = intent.getStringExtra("selectedStandard")
        id.id = dato
        viewDataBinding.viewmodel = ViewModelProviders.of(this).get(EvaluationViewModel::class.java)
        viewDataBinding.lifecycleOwner = this

        viewmodel = viewDataBinding.viewmodel
        viewmodel?.setView(this)
    }

    fun loadSpinners() {
        viewDataBinding.viewmodel!!.fetchSpinnerEssentialKnowledge()
            .observe(this, Observer { spinnerData ->
                val spinnerAdapter =
                    ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_item,
                        spinnerData
                    )
                viewDataBinding.spinner2.adapter = spinnerAdapter
            })
        viewDataBinding.viewmodel!!.fetchSpinnerCriterionItems()
            .observe(this, Observer { spinnerData ->
                val spinnerAdapter =
                    ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_item,
                        spinnerData
                    )
                viewDataBinding.spinner3.adapter = spinnerAdapter
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_question -> addQuestion()
            R.id.edit_question -> editQuestion()
            R.id.delete_question -> deleteQuestion()
        }
        if (item.itemId == R.id.add_question) {

        }
        return true
    }

    fun addQuestion() {
        QuestionDialog(this, QuestionDialog.ADD.ADD, viewDataBinding.viewmodel!!)
    }

    fun editQuestion() {
        QuestionDialog(this, QuestionDialog.EDIT.EDIT, viewDataBinding.viewmodel!!)
    }

    fun deleteQuestion() {
        viewDataBinding.viewmodel?.deleteQuestion()
    }
}
