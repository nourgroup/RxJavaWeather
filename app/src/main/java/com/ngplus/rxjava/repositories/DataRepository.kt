package com.ngplus.rxjava.repositories

import android.util.Log
import com.ngplus.rxjava.data.Currently
import com.ngplus.rxjava.data.WeatherAPI
import com.ngplus.rxjava.models.ResponseStatus
import com.ngplus.rxjava.models.ResponseWeatherAPI
import com.ngplus.rxjava.models.WeatherAPIModel
import com.ngplus.rxjava.webservices.DataWeatherService
import com.ngplus.rxjava.webservices.WebService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository constructor(var mDataWeatherService : DataWeatherService) {
    var responseWeather: ResponseWeatherAPI<WeatherAPIModel> = ResponseWeatherAPI(WeatherAPIModel())
    val TAG = "tuto_rxjava"
    init {
        responseWeather.status = ResponseStatus.FAILURE
        responseWeather.weatherResponse = WeatherAPIModel()
    }

    fun getWeather(
        longitude: Double,
        latitude: Double
    ): Observable<ResponseWeatherAPI<WeatherAPIModel>> {
        Log.i("tuto_rxjava", "${javaClass.name} getWeather()")
        //Observable<WeatherAPI>
        val able = mDataWeatherService.getWeather(longitude, latitude)
        val er = object : Observer<WeatherAPI> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "onSubscribe: ")
            }
            override fun onNext(s: WeatherAPI) {
                responseWeather.status = ResponseStatus.SUCCESS
                responseWeather.weatherResponse.currently.temperature = s.currently?.temperature
            }
            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
        able.subscribeOn(Schedulers.io())
            .subscribe(er)

        return Observable.just(responseWeather)
    }
}
