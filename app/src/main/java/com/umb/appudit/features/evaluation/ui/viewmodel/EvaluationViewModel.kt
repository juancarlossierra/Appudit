package com.umb.appudit.features.evaluation.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.umb.appudit.features.evaluation.data.EvaluationDataSource
import com.umb.appudit.features.evaluation.data.EvaluationRepository
import com.umb.appudit.features.evaluation.data.entities.Criterion
import com.umb.appudit.features.evaluation.data.entities.EssentialKnowledge
import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.evaluation.data.entities.Question

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
                knowledge?.essentialKnowledges?.get(selectedItemPositionEsentials!!)?.criterion?.get(field!!)
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
        loadTestData()
        questionPosition = 0
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
        if (questionPosition!! < questions?.size!!-1) {
            questionPosition = questionPosition!! + 1
            val question = questions?.get(questionPosition!!)
            _Question.value = question?.body
            _OptionOne.value = question?.options?.get(0)
            _OptionTwo.value = question?.options?.get(1)
            _OptionThree.value = question?.options?.get(2)
            _OptionFour.value = question?.options?.get(3)
        }
    }

    fun loadTestData() {
        var question = Question()
        question.body = "question number one"
        var options = ArrayList<String>()
        options.add("correct option")
        options.add("option two")
        options.add("option three")
        options.add("option four")
        question.options = options
        question.answer = 0
        val questions = ArrayList<Question>()
        questions.add(question)

        question = Question()
        question.body = "question number two"
        options = ArrayList<String>()
        options.add("option one")
        options.add("correct option")
        options.add("option three")
        options.add("option four")
        question.options = options
        question.answer = 1
        questions.add(question)

        question = Question()
        question.body = "question number three"
        options = ArrayList<String>()
        options.add("option one")
        options.add("option two")
        options.add("correct option")
        options.add("option four")
        question.options = options
        question.answer = 2
        questions.add(question)

        val criterions = ArrayList<Criterion>()
        var criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 1"
        criterions.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 2"
        criterions.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 3"
        criterions.add(criterion)

        val criterions2 = ArrayList<Criterion>()
        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 4"
        criterions2.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 5"
        criterions2.add(criterion)

        criterion = Criterion()
        criterion.evidence = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 6"
        criterions2.add(criterion)

        var essentialKnowledge = EssentialKnowledge()
        essentialKnowledge.description = "Essential knowledge one"
        essentialKnowledge.criterion = criterions
        val essentialKnowledges = ArrayList<EssentialKnowledge>()
        essentialKnowledges.add(essentialKnowledge)
        essentialKnowledge = EssentialKnowledge()
        essentialKnowledge.description = "Essential knowledge two"
        essentialKnowledge.criterion = criterions2
        essentialKnowledges.add(essentialKnowledge)

        knowledge = Knowledge()
        knowledge!!.essentialKnowledges = essentialKnowledges
    }
}
