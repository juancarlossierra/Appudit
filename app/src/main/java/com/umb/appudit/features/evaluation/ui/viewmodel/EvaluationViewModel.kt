package com.umb.appudit.features.evaluation.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.umb.appudit.features.evaluation.data.EvaluationDataSource
import com.umb.appudit.features.evaluation.data.EvaluationRepository
import com.umb.appudit.features.evaluation.data.entities.Criterion
import com.umb.appudit.features.evaluation.data.entities.EssentialKnowledge
import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.evaluation.data.entities.Question
import com.umb.appudit.features.evaluation.ui.QuestionDialog
import com.umb.appudit.features.evaluation.ui.QuestionDialog2

class EvaluationViewModel(aplication: Application) : AndroidViewModel(aplication) {

    var evaluationRepository: EvaluationDataSource? = null

    var knowledge: Knowledge? = null

    private val _Evidence = MutableLiveData<String>()
    val evidence: LiveData<String>
        get() = _Evidence

    private val _KeyActivity = MutableLiveData<String>()
    val keyActivity: LiveData<String>
        get() = _KeyActivity

    private val _ThoughtCategory = MutableLiveData<String>()
    val thoughtCategory: LiveData<String>
        get() = _ThoughtCategory

    private val _Question = MutableLiveData<String>()
    val question: LiveData<String>
        get() = _Question

    private val _OptionOne = MutableLiveData<String>()
    val optionOne: LiveData<String>
        get() = _OptionOne

    private val _OptionTwo = MutableLiveData<String>()
    val optionTwo: LiveData<String>
        get() = _OptionTwo

    private val _OptionThree = MutableLiveData<String>()
    val OptionThree: LiveData<String>
        get() = _OptionThree

    private val _OptionFour = MutableLiveData<String>()
    val optionFour: LiveData<String>
        get() = _OptionFour

    private val _EssentialKnowledgeItems = MutableLiveData<List<EssentialKnowledge>>()
    fun fetchSpinnerEssentialKnowledge(): LiveData<List<EssentialKnowledge>> {
        _EssentialKnowledgeItems.value = knowledge?.essentialKnowledges
        return _EssentialKnowledgeItems
    }

    var selectedItemPositionEsentials = MutableLiveData<Int>().value
        set(value) {
            field = value
            val selectedItem = knowledge?.essentialKnowledges?.get(field!!)
            _CriterionItems.value = selectedItem?.criterion
        }

    private val _CriterionItems = MutableLiveData<List<Criterion>>()
    fun fetchSpinnerCriterionItems(): LiveData<List<Criterion>> {
        _CriterionItems.value = knowledge?.essentialKnowledges?.get(0)?.criterion
        return _CriterionItems
    }

    var selectedItemPositionCritetions = MutableLiveData<Int>().value
        set(value) {
            field = value
            val selectedItem =
                knowledge?.essentialKnowledges?.get(selectedItemPositionEsentials!!)
                    ?.criterion?.get(field!!)
            _Evidence.value = selectedItem?.evidence
            _KeyActivity.value = selectedItem?.keyActivity
            _ThoughtCategory.value = selectedItem?.thoughtCategory
            questions = selectedItem?.questions
            questionPosition = 0
            val question = questions?.get(questionPosition!!)
            _Question.value = question?.body
            _OptionOne.value = question?.options?.get(0)
            _OptionTwo.value = question?.options?.get(1)
            _OptionThree.value = question?.options?.get(2)
            _OptionFour.value = question?.options?.get(3)
        }

    private var questionPosition: Int? = null

    private var questions: List<Question>? = null

    init {
        evaluationRepository = EvaluationRepository.getInstance(getApplication())
        questionPosition = 0
        val callback = object : EvaluationDataSource.Callback {
            override fun getDataSuccessful(knowledgef: Knowledge) {
                knowledge = knowledgef
            }

            override fun getDataError() {
                Toast.makeText(getApplication(), "Error al cargar los datos", Toast.LENGTH_LONG)
                    .show()
            }

        }
        evaluationRepository?.getData("2", callback)
    }

    fun previousQuestion() {
        if (questionPosition!! > 0) {
            questionPosition = questionPosition!! - 1
            val question = questions?.get(questionPosition!!)
            _Question.value = question?.body
            _OptionOne.value = question?.options?.get(0)
            _OptionTwo.value = question?.options?.get(1)
            _OptionThree.value = question?.options?.get(2)
            _OptionFour.value = question?.options?.get(3)
        }
    }

    fun nextQuestion() {
        if (questionPosition!! < questions?.size!! - 1) {
            questionPosition = questionPosition!! + 1
            val question = questions?.get(questionPosition!!)
            _Question.value = question?.body
            _OptionOne.value = question?.options?.get(0)
            _OptionTwo.value = question?.options?.get(1)
            _OptionThree.value = question?.options?.get(2)
            _OptionFour.value = question?.options?.get(3)
        }
    }

    fun deleteQuestion() {
        evaluationRepository?.deleteQuestion()
    }

}