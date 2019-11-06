package com.umb.appudit.features.evaluation.ui

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.umb.appudit.R
import com.umb.appudit.features.evaluation.data.entities.Question
import com.umb.appudit.features.evaluation.ui.viewmodel.EvaluationViewModel
import java.util.ArrayList

class QuestionDialog(
    context: Context,
    textButton: String,
    evalViewModel: EvaluationViewModel
) {
    private var question: String? = null
    private var options: MutableList<String>? = null
    private var btnLabel = textButton
    private var selectedQuestion: Question?
    private var evaluationViewModel: EvaluationViewModel

    object ADD {
        val ADD = "ADD"
    }

    object EDIT {
        val EDIT = "EDIT"
    }

    init {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.question_dialog)

        initDialogComponents(dialog)

        evaluationViewModel = evalViewModel
        selectedQuestion = evalViewModel.getSelectedQuestion()

        dialog.show()
    }

    private fun initDialogComponents(dialog: Dialog) {
        val btnAccept = dialog.findViewById<Button>(R.id.btnAccept)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val etQuestion = dialog.findViewById<EditText>(R.id.etQuestion)
        val etOptionOne = dialog.findViewById<EditText>(R.id.etOptionOne)
        val etOptionTwo = dialog.findViewById<EditText>(R.id.etOptionTwo)
        val etOptionThree = dialog.findViewById<EditText>(R.id.etOptionThree)
        val etOptionFour = dialog.findViewById<EditText>(R.id.etOptionFour)
        val rgOptions = dialog.findViewById<RadioGroup>(R.id.rgOptionGroup)

        options = listOf(
            etOptionOne.text.toString(),
            etOptionTwo.text.toString(),
            etOptionThree.text.toString(),
            etOptionFour.text.toString()
        ) as ArrayList

        initBtnAccept(
            btnAccept,
            etQuestion.text.toString(),
            rgOptions,
            dialog.findViewById<RadioButton>(rgOptions?.checkedRadioButtonId!!),
            dialog
        )

        if (btnLabel == EDIT.EDIT) {
            etQuestion.setText(selectedQuestion?.body)
            etOptionOne.setText(selectedQuestion?.options?.get(0))
            etOptionTwo.setText(selectedQuestion?.options?.get(1))
            etOptionThree.setText(selectedQuestion?.options?.get(2))
            etOptionFour.setText(selectedQuestion?.options?.get(3))
        }

        btnCancel.setOnClickListener { dialog.dismiss() }
    }

    private fun initBtnAccept(
        btnAccept: Button,
        question: String,
        rgOptions: RadioGroup,
        rbSelected: RadioButton,
        dialog: Dialog
    ) {

        btnAccept.text = btnLabel
        btnAccept.setOnClickListener {
            if (btnLabel == ADD.ADD) {
                evaluationViewModel.addQuestion(question!!, options!!, rgOptions.indexOfChild(rbSelected))
            } else {
                evaluationViewModel.editQuestion(question!!, options!!, rgOptions.indexOfChild(rbSelected))
            }
            dialog.dismiss()
        }

    }
}