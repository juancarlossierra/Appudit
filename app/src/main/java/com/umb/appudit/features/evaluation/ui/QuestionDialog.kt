package com.umb.appudit.features.evaluation.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.umb.appudit.R
import java.util.ArrayList

class QuestionDialog(context: Context, textButton: String) {

    private var question: String? = null
    private var options: MutableList<String>? = null

    init {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.question_dialog)

        val etQuestion = dialog.findViewById<EditText>(R.id.etQuestion)
        val etOptionOne = dialog.findViewById<EditText>(R.id.etOptionOne)
        val etOptionTwo = dialog.findViewById<EditText>(R.id.etOptionTwo)
        val etOptionThree = dialog.findViewById<EditText>(R.id.etOptionThree)
        val etOptionFour = dialog.findViewById<EditText>(R.id.etOptionFour)

        val btnAccept = dialog.findViewById<Button>(R.id.btnAccept)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)

        btnAccept.setText(textButton)
        btnAccept.setOnClickListener {
            question = etQuestion.text.toString()
            options = ArrayList<String>()
            options?.add(etOptionOne.text.toString())
            options?.add(etOptionTwo.text.toString())
            options?.add(etOptionThree.text.toString())
            options?.add(etOptionFour.text.toString())

        }
        btnCancel.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

}