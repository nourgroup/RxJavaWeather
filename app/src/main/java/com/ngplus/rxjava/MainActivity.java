package com.ngplus.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
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
        /********Factory method create*********/
        // onComplete is automatically invoked
        Observable<Integer> testInteger = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(int i=0;i<10;i++){
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });
        /********Factory method just*********/
        // onComplete is automatically invoked when we use just unlike the create()
        Observable<Integer> testInteger2 = Observable.just(0,1,2);
        Observer ob = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i("tuto_rxjava","onSubscribe");
            }

            @Override
            public void onNext(@androidx.annotation.NonNull Object o) {
                Log.i("tuto_rxjava","onNext"+ o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("tuto_rxjava","onError");
            }

            @Override
            public void onComplete() {
                Log.i("tuto_rxjava","onComplete");
            }
        };
        //testInteger.subscribe(ob);
        /********Factory method create*********/
        Integer tab[] = new Integer[5];
        tab[0] = 1;tab[1] = 2;tab[2] = 3;tab[3] = 4;tab[4] = 5;
        Observable listObservable = Observable.fromArray(tab).repeat(2);
        listObservable.subscribe(ob);

    }

    void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}