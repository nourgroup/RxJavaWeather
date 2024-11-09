package com.ngplus.rxjava.webservices

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.ngplus.rxjava.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object WebService {
    //https://api.darksky.net/forecast/e244cdbd70b7eda06965b78ed19bad1d/37.8267,-122.4233
    // ${BuildConfig.DARKSKY_KEY}
    // migration
    //v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl
    val BASE_URL = "https://api.open-meteo.com/"
    lateinit var service: DataWeatherService


    fun getInstance() : DataWeatherService{
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor( OkHttpProfilerInterceptor() )
        }
        val client = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(DataWeatherService::class.java)
        return service
    }

}