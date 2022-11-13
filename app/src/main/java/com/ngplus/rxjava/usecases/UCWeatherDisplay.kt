package com.ngplus.rxjava.usecases


import com.ngplus.rxjava.models.ResponseWeatherAPI
import com.ngplus.rxjava.models.WeatherAPIModel
import com.ngplus.rxjava.repositories.DataRepository
import io.reactivex.rxjava3.core.Observable

class UCWeatherDisplay constructor(val mRepository: DataRepository) {

    fun getWeather(longitude : Double, latitude : Double) : Observable<ResponseWeatherAPI<WeatherAPIModel>>{
        return mRepository.getWeather(longitude, latitude)
    }
}