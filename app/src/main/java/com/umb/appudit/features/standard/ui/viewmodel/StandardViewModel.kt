package com.umb.appudit.features.standard.ui.viewmodel

import android.app.Application
import android.util.Log
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
            val itemSelected = list[field!!]
            _StandardCode.value = itemSelected.code
            _StandardVersion.value = itemSelected.version
            _Definition.value = itemSelected.definition
        }

    private val _NoRecuedo = MutableLiveData<List<Standard>>()
    var list = ArrayList<Standard>()


    private val _StandardItems = MutableLiveData<List<String>>()
    val standardItems = ArrayList<String>()

    fun start() {
        repository = StandardRepository.getInstance(getApplication())
//        repository?.escribirDataNueva()
        val callback = object : StandardDataSource.GetDataCallback {
            override fun getDataSuceSuccessfully(data: ArrayList<Standard>) {
                list.clear()
                data.forEach { a ->
                    list.add(a)
                }
            }

            override fun getDataError() {
                Toast.makeText(getApplication(), "Error to get data", Toast.LENGTH_SHORT).show()
            }
        }
        Log.d("prueba", "inicializacion")
        repository?.getStandards(callback)
    }

    fun fetchSpinnerNoRecuerdo(): LiveData<List<Standard>> {
        _NoRecuedo.value = list
        return _NoRecuedo
    }

    fun fetchSpinnerStandard(): LiveData<List<String>> {
        _StandardItems.value = standardItems
        return _StandardItems
    }

    fun selectStandard(){

    }

}
