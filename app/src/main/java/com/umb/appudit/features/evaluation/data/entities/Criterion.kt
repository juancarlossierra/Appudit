package com.umb.appudit.features.evaluation.data.entities



class Criterion {

    var description: String? = null

    var evidence: String? = null

    var keyActivity: String? = null

    var thoughtCategory: String? = null

    var questions: MutableList<Question>? = null

    override fun toString(): String {
        return description.toString()
    }
}