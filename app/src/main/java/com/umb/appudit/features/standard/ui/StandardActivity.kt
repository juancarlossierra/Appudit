package com.umb.appudit.features.standard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umb.appudit.R
import com.umb.appudit.features.standard.ui.StandardFragment

class StandardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.standard_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StandardFragment.newInstance())
                .commitNow()
        }
    }

}
