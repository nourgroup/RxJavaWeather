package com.ngplus.rxjava.models;

import com.ngplus.rxjava.data.Currently;

import androidx.annotation.Nullable;

public class ResponseWeatherAPI<T> {
    public ResponseStatus status;
    public T weatherResponse;

    public ResponseWeatherAPI(T a){
        this.weatherResponse = a;
        this.status = ResponseStatus.FAILURE;
    }
}
