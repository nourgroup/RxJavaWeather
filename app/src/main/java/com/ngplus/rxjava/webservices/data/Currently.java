package com.ngplus.rxjava.webservices.data;

import androidx.annotation.Nullable;

public class Currently {
    @Nullable
    public String temperature;

    public Currently(){

    }

    public Currently(@Nullable String temperature) {
        this.temperature = temperature;
    }
}
