package com.ngplus.rxjava.repositories

import android.util.Log
import com.ngplus.rxjava.data.WeatherAPI
import com.ngplus.rxjava.models.ResponseStatus
import com.ngplus.rxjava.models.ResponseWeatherAPI
import com.ngplus.rxjava.models.WeatherAPIModel
import com.ngplus.rxjava.webservices.DataWeatherService
import com.ngplus.rxjava.webservices.WebService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository constructor(var mDataWeatherService : DataWeatherService) {
    var responseWeather : ResponseWeatherAPI<WeatherAPIModel> = ResponseWeatherAPI(WeatherAPIModel())

    init {
        responseWeather.status = ResponseStatus.FAILURE
        responseWeather.weatherResponse = WeatherAPIModel()
    }

    fun getWeather(longitude : Double, latitude : Double) : Observable<ResponseWeatherAPI<WeatherAPIModel>> {
        Log.i("tuto_rxjava", "${javaClass.name} getWeather()")
        val data = mDataWeatherService.getWeather(longitude,latitude)

        data.enqueue(object : Callback<WeatherAPI>{
            override fun onFailure(call: Call<WeatherAPI>, t: Throwable) {
                //responseWeather.status = ResponseStatus.FAILURE
                //responseWeather.weatherResponse = WeatherAPIModel()
                Log.i("tuto_rxjava", "error ${ t.message }")
            }

            override fun onResponse(call: Call<WeatherAPI>, response: Response<WeatherAPI>) {
                /*if(response.isSuccessful){
                    responseWeather.status = ResponseStatus.SUCCESS
                    responseWeather.weatherResponse.timezone = response.body()!!.timezone
                    responseWeather.weatherResponse.currently = response.body()!!.currently
                }*/
                Log.i("tuto_rxjava", "success !!")
            }
        })
        Log.i("tuto_rxjava", "${javaClass.name} response : ${responseWeather.status}")
        //var response  : Observable<ResponseWeatherAPI<WeatherAPIModel>> = Observable.just(responseWeather)

        return Observable.just(responseWeather)
    }
}