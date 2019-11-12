package com.umb.appudit.features.evaluation.data.entities


class EssentialKnowledge {

    
    var description: String? = null

    var criterion: List<Criterion>? = null

    override fun toString(): String {
        return description.toString()
    }
}