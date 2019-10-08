package com.umb.appudit.features.Evaluation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.umb.appudit.R

class EvaluationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evaluation_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EvaluationFragment.newInstance())
                .commitNow()
        }
        val dato = intent.getStringExtra("selectedStandard")
        Log.d("prueba",dato)
    }

}
