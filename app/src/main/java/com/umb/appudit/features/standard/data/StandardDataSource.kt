package com.umb.appudit.features.standard.data


import com.umb.appudit.features.standard.data.entities.Standard



interface StandardDataSource {

    fun getStandards(callback: GetDataCallback)

    interface GetDataCallback{

        fun getDataSuceSuccessfully(data: ArrayList<Standard>)

        fun getDataError() } 
        }