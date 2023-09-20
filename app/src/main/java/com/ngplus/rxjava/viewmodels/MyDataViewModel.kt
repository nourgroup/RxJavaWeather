package com.ngplus.rxjava.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngplus.rxjava.models.ResponseWeatherAPI
import com.ngplus.rxjava.models.WeatherAPIModel
import com.ngplus.rxjava.repositories.DataRepository
import com.ngplus.rxjava.usecases.UCWeatherDisplay
import com.ngplus.rxjava.webservices.DataWeatherService
import com.ngplus.rxjava.webservices.WebService
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow

class MyDataViewModel : ViewModel() {

    var weatherState :MutableLiveData<ResponseWeatherAPI<WeatherAPIModel>> = MutableLiveData<ResponseWeatherAPI<WeatherAPIModel>>()

    private val mUCWeatherDisplay = UCWeatherDisplay(DataRepository(WebService.getInstance()))

    fun getWeather(longitude : Double, latitude : Double) {
        val observableWeather = mUCWeatherDisplay.getWeather(longitude, latitude)
        observableWeather
            .subscribeOn(Schedulers.io())
            .subscribe{
                    res -> weatherState.postValue(res)
            }
    }
}