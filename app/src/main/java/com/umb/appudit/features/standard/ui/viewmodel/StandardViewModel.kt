package com.umb.appudit.features.standard.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.umb.appudit.features.standard.data.StandardDataSource
import com.umb.appudit.features.standard.data.StandardRepository
import com.umb.appudit.features.standard.data.entities.Standard

class StandardViewModel(aplication: Application) : AndroidViewModel(aplication) {

    private var repository: StandardDataSource? = null

    private val _StandardCode = MutableLiveData<String>()
    val standardCode: LiveData<String>
        get() = _StandardCode

    private val _StandardVersion = MutableLiveData<String>()
    val standardVersion: LiveData<String>
        get() = _StandardVersion

    private val _Definition = MutableLiveData<String>()
    val definition: LiveData<String>
        get() = _Definition

    var selectedItemPosition = MutableLiveData<Int>().value
        set(value) {
            field = value
            val itemSelected = standardItems[field!!]
            _StandardCode.value = itemSelected.code
            _StandardVersion.value = itemSelected.version
            _Definition.value = itemSelected.definition
        }

    private val _StandardItems = MutableLiveData<List<Standard>>()
    var standardItems = ArrayList<Standard>()


    fun start() {

        repository = StandardRepository.getInstance(getApplication())
        val callback = object : StandardDataSource.GetDataCallback {
            override fun getDataSuceSuccessfully(data: ArrayList<Standard>) {
                standardItems.clear()
                data.forEach { a ->
                    standardItems.add(a)
                }
            }

            override fun getDataError() {
                Toast.makeText(getApplication(), "Error to get data", Toast.LENGTH_SHORT).show()
            }
        }
        repository?.getStandards(callback)
    }

    fun fetchSpinnerStandard(): LiveData<List<Standard>> {
        _StandardItems.value = standardItems
        return _StandardItems
    }

    fun getSelectStandard():String{return standardItems[this.selectedItemPosition!!].id.toString() }
}
