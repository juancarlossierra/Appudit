package com.umb.appudit.features.standard.data.entities

import com.umb.appudit.features.evaluation.data.entities.EssentialKnowledge

class Standard {

    var id: String? = null

    var name: String? = null

    var code: String? = null

    var version: String? = null

    var definition: String? = null

    var essentialKnowledge: EssentialKnowledge? = null

    override fun toString(): String {
        return name.toString()
    }
}