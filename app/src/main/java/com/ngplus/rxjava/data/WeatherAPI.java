package com.ngplus.rxjava.data;

import androidx.annotation.Nullable;

public class WeatherAPI {
    @Nullable
    public String timezone;
    @Nullable
    public Currently currently;

    public WeatherAPI(){

    }

    public WeatherAPI(@Nullable String timezone, @Nullable Currently currently) {
        this.timezone = timezone;
        this.currently = currently;
    }
}
