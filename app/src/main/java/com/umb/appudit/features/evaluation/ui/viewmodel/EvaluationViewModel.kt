package com.umb.appudit.features.evaluation.ui.viewmodel

import android.app.Application
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

    private val _CriterionItems = MutableLiveData<List<Criterion>>()
    fun fetchSpinnerCriterionItems(): LiveData<List<Criterion>> {
        _CriterionItems.value = knowledge?.essentialKnowledges?.get(0)?.criterion
        return _CriterionItems
    }

    //var selectedItemPosition = MutableLiveData<Int>()

    init {
        evaluationRepository = EvaluationRepository.getInstance(getApplication())
        loadTestData()
    }


    fun loadTestData() {
        val question = Question()
        question.body = "question number one"
        val options = ArrayList<String>()
        options.add("correct option")
        options.add("option two")
        options.add("option three")
        options.add("option four")
        question.options = options
        question.answer = 0
        val questions = ArrayList<Question>()
        questions.add(question)
        options[0] = "option one"
        options[1] = "correct option"
        question.options = options
        question.answer = 1
        questions.add(question)
        options[1] = "option two"
        options[2] = "correct option"
        question.options = options
        question.answer = 2
        questions.add(question)

        val criterions = ArrayList<Criterion>()
        val criterion = Criterion()
        criterion.evidencia = "Content of the evidence"
        criterion.keyActivity = "Content of the key activity"
        criterion.thoughtCategory = "Content of the thougth category"
        criterion.questions = questions
        criterion.description = "criterion 1"
        criterions.add(criterion)
        criterion.description = "criterion 2"
        criterions.add(criterion)
        criterion.description = "criterion 3"
        criterions.add(criterion)

        val essentialKnowledge = EssentialKnowledge()
        essentialKnowledge.description = "Essential knowledge one"
        essentialKnowledge.criterion = criterions
        val essentialKnowledges = ArrayList<EssentialKnowledge>()
        essentialKnowledges.add(essentialKnowledge)
        essentialKnowledge.description = "Essential knowledge two"
        essentialKnowledges.add(essentialKnowledge)

        knowledge = Knowledge()
        knowledge!!.essentialKnowledges = essentialKnowledges
    }
}
