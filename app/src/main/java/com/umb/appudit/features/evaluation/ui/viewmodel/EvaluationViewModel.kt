package com.umb.appudit.features.evaluation.ui.viewmodel
    

import android.app.Application
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
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
import com.umb.appudit.features.evaluation.ui.EvaluationActivity
import com.umb.appudit.features.standard.data.entities.Standard



class EvaluationViewModel(aplication: Application) : AndroidViewModel(aplication) {

    var evaluationRepository: EvaluationDataSource? = null

    var knowledge: Knowledge? = null

    var essentialKnowledges: List<EssentialKnowledge>? = null

    var standard: Standard? = null

    private var view: EvaluationActivity? = null

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
        _EssentialKnowledgeItems.value = essentialKnowledges
        return _EssentialKnowledgeItems
    }

    var selectedItemPositionEsentials = MutableLiveData<Int>().value
        set(value) {
            field = value
            val selectedItem = essentialKnowledges?.get(field!!)
            _CriterionItems.value = selectedItem?.criterion
        }

    private val _CriterionItems = MutableLiveData<List<Criterion>>()
    fun fetchSpinnerCriterionItems(): LiveData<List<Criterion>> {
        _CriterionItems.value = essentialKnowledges?.get(0)?.criterion
        return _CriterionItems
    }

    var selectedItemPositionCritetions = MutableLiveData<Int>().value
        set(value) {
            field = value
            val selectedItem =
                essentialKnowledges?.get(selectedItemPositionEsentials!!)
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

    val _OptionsItems = MutableLiveData<List<String>>()
    fun fetchSpinnerOptionsItems(): LiveData<List<String>> {
        _OptionsItems.value = arrayListOf("Knowledge", "Product", "Performance")
        return _OptionsItems
    }

    var selectedItemOptcion = MutableLiveData<Int>().value
        set(value) {
            field = value
            when (field) {
                0 -> {
                    essentialKnowledges = standard?.knowledge?.essentialKnowledges
                    _EssentialKnowledgeItems.value = essentialKnowledges
                }
                1 -> {
                    essentialKnowledges = standard?.product?.essentialKnowledges
                    _EssentialKnowledgeItems.value = essentialKnowledges }
                2 -> {
                    essentialKnowledges = standard?.performance?.essentialKnowledges
                    _EssentialKnowledgeItems.value = essentialKnowledges
                }
            }

        }

    private var questionPosition: Int? = null

    private var questions: List<Question>? = null

    init {
        evaluationRepository = EvaluationRepository.getInstance(getApplication())
        questionPosition = 0
        val callback = object : EvaluationDataSource.Callback {
            override fun getDataSuccessful(standard: Standard) {
                this@EvaluationViewModel.standard = standard
                this@EvaluationViewModel.knowledge = standard.knowledge
                essentialKnowledges = knowledge?.essentialKnowledges
                view?.loadSpinners()
            }

            override fun getDataError() {
                Toast.makeText(getApplication(), "Error al cargar los datos", Toast.LENGTH_LONG)
                    .show()
            }
        }
        evaluationRepository?.getData(EvaluationActivity.id.id, callback)
    }

    fun previousQuestion() {
        val question: Question?
        if (questionPosition!! > 0) {
            questionPosition = questionPosition!! - 1
            question = questions?.get(questionPosition!!)
        } else {
            question = questions?.get(0)
        }
        _Question.value = question?.body
        _OptionOne.value = question?.options?.get(0)
        _OptionTwo.value = question?.options?.get(1)
        _OptionThree.value = question?.options?.get(2)
        _OptionFour.value = question?.options?.get(3)
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
        essentialKnowledges?.get(selectedItemPositionEsentials!!)?.criterion?.get(
            selectedItemPositionCritetions!!
        )?.questions?.removeAt(questionPosition!!)
        previousQuestion()
    }

    fun addQuestion(body: String, options: List<String>, answer: Int) {
        val questions =
            essentialKnowledges?.get(selectedItemPositionEsentials!!)?.criterion?.get(
                selectedItemPositionCritetions!!
            )?.questions
        val question = Question()
        question.body = body
        question.options = options
        question.answer = answer
        questions?.add(question)
    }

    fun editQuestion(body: String, options: List<String>, answer: Int) {
        val question = Question()
        question.body = body
        question.options = options
        question.answer = answer
        essentialKnowledges?.get(selectedItemPositionEsentials!!)?.criterion?.get(
            selectedItemPositionCritetions!!
        )?.questions?.set(questionPosition!!, question)
        _Question.value = question.body
        _OptionOne.value = question.options?.get(0)
        _OptionTwo.value = question.options?.get(1)
        _OptionThree.value = question.options?.get(2)
        _OptionFour.value = question.options?.get(3)
    }

    fun setView(view: EvaluationActivity) {
        this@EvaluationViewModel.view = view
    }

    fun getSelectedQuestion(): Question? {
        return essentialKnowledges?.get(selectedItemPositionEsentials!!)?.criterion?.get(
            selectedItemPositionCritetions!!
        )?.questions?.get(questionPosition!!)
    }

    fun onSplitTypeChanged(radioGroup: RadioGroup, id: Int) {
        Log.i("prueba", "prueba dato " + radioGroup.indexOfChild(radioGroup.findViewById(id)))
        var question = essentialKnowledges?.get(selectedItemPositionEsentials!!)?.criterion?.get(
            selectedItemPositionCritetions!!
        )?.questions?.get(questionPosition!!)
        val indx = radioGroup.indexOfChild(radioGroup.findViewById(id))
        if (question?.answer == indx) {
            view?.showMessage("correct answer")
        } else {
            view?.showMessage("wrong answer")
        }
    }
}