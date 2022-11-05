package com.ngplus.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Observable<Long> test = Observable.intervalRange(0,5,0,1, TimeUnit.SECONDS);
        /*ConnectableObservable<Long> cold = ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();
        cold.connect();
        cold.subscribe(i -> Log.i("MainActivity_rxjava","student 1 -> "+i.toString()));

        try{
            Thread.sleep(2000);
        }catch(Exception ex){
        }
        cold.subscribe(i -> Log.i("MainActivity_rxjava","Student 2 -> "+i.toString()));*/
        /**** Observable : PublishSubject ****/
        AsyncSubject<String> mySubject = AsyncSubject.create();
        mySubject.subscribe(i -> Log.i("MainActivity_rxjava","student 1 -> "+i));

        mySubject.onNext("A");
        sleep(1000);
        mySubject.onNext("B");
        sleep(1000);
        mySubject.onNext("C");
        sleep(1000);
        mySubject.onNext("D");
        sleep(1000);
        mySubject.subscribe(i -> Log.i("MainActivity_rxjava","student 2 -> "+i));
        mySubject.onNext("E");
        sleep(1000);
        mySubject.onNext("F");
        sleep(1000);
        mySubject.onComplete();

    }

    void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}