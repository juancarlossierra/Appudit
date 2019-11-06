package com.umb.appudit.features.standard.data.entities


import com.umb.appudit.features.evaluation.data.entities.Knowledge
import com.umb.appudit.features.evaluation.data.entities.Performance
import com.umb.appudit.features.evaluation.data.entities.Product

class Standard {

    var id: String? = null

    var name: String? = null

    var code: String? = null

    var version: String? = null

    var definition: String? = null

    var knowledge: Knowledge? = null

    var product: Product? = null

    var performance: Performance? = null

    override fun toString(): String {
        return name.toString()
    }
}