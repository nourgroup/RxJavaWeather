package com.ngplus.rxjava.webservices.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeatherDataDto(
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("weathercode")
    val weatherCodes: List<Int>,
    @SerializedName("pressure_msl")
    val pressures: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerializedName("relativehumidity_2m")
    val humidities: List<Double>
)

data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap() : Map<Int, List<WeatherData>>{
    return time.mapIndexed{ i,t ->
        val temperature = temperatures[i]
        val weatherCode = weatherCodes[i]
        val windSpeed = windSpeeds[i]
        val pressure = pressures[i]
        val humidity = humidities[i]
        IndexWeatherData(
            index = i,
            WeatherData(
                time = LocalDateTime.parse(t, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues{
        it.value.map { a->a.data }
    }//.also { println(it) }
}




