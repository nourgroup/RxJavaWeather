package com.ngplus.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Observable<Long> test = Observable.intervalRange(0,5,0,1, TimeUnit.SECONDS);
        ConnectableObservable<Long> cold = ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();
        cold.connect();
        cold.subscribe(i -> Log.i("MainActivity_rxjava","student 1 -> "+i.toString()));

        try{
            Thread.sleep(2000);
        }catch(Exception ex){
        }
        cold.subscribe(i -> Log.i("MainActivity_rxjava","Student 2 -> "+i.toString()));

    }
}