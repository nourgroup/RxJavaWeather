package com.ngplus.rxjava.webservices


import com.ngplus.rxjava.webservices.data.WeatherAPI
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface DataWeatherService {
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    fun getWeather(
        @Query("longitude") longitude: Double?,
        @Query("latitude") latitude: Double?
    ): Observable<WeatherAPI>
}