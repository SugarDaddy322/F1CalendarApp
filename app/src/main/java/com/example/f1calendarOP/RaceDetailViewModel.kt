package com.example.f1calendarOP

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RaceDetailViewModel(private val raceModel : Race) : ViewModel() {

    private val raceInfoMutable = MutableLiveData<ArrayList<RaceDetailModel>>()

    fun getDetailInfo() {
        val raceFunctions = RaceFunctions()
        val raceDetailModels = raceFunctions.getDetailApiData(raceModel)
        raceInfoMutable.value = raceDetailModels
    }
    fun getLiveData() : LiveData<ArrayList<RaceDetailModel>> {
        return raceInfoMutable
    }

}