package com.ngplus.rxjava.models;

public class ResponseWeatherAPI<T> {
    public ResponseStatus status;
    public T weatherResponse;

    public ResponseWeatherAPI(T a){
        this.weatherResponse = a;
        this.status = ResponseStatus.FAILURE;
    }
}
