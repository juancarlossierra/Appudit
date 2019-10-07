package com.umb.appudit.features.standard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandardViewModel : ViewModel() {

    private val _StandardCode = MutableLiveData<String>()
    val standardCode: LiveData<String>
        get() = _StandardCode

    private val _StandardVersion = MutableLiveData<String>()
    val standardVersion: LiveData<String>
        get() = _StandardVersion

    private val _Definition = MutableLiveData<String>()
    val definition: LiveData<String>
        get() = _Definition

    private val _NoRecuedo = MutableLiveData<List<String>>()
    val list = ArrayList<String>()

    private val _StandardItems = MutableLiveData<List<String>>()
    val standardItems = ArrayList<String>()

    fun start() {

    }

    fun fetchSpinnerNoRecuerdo(): LiveData<List<String>> {
        _NoRecuedo.value = list
        return _NoRecuedo
    }

    fun fetchSpinnerStandard(): LiveData<List<String>> {
        _StandardItems.value = standardItems
        return _NoRecuedo
    }

}
