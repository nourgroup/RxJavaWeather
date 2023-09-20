package com.ngplus.rxjava.webservices


import com.ngplus.rxjava.data.WeatherAPI
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface DataWeatherService {
    @GET("{longitude},{latitude}")
    fun getWeather(
        @Path("longitude") longitude: Double?,
        @Path("latitude") latitude: Double?
    ): Observable<WeatherAPI>
}