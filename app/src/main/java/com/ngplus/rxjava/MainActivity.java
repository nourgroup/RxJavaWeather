package com.ngplus.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ngplus.rxjava.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding _binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
        /********Factory method create*********/
        _binding.display.setText("one");
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
        //listObservable.subscribe(ob);
        /** RXJava Threading operators | Schedulers, SubscribeOn and ObserveOn **/
        Log.i("tuto_rxjava","test");
        Observable.just(1,2,3,4)
                .doOnNext(e -> Log.i("tuto_rxjava","current Thread: "+Thread.currentThread().getName()+" send : "+e.toString()))
                .subscribeOn(Schedulers.computation())
                .map(a -> a*2)
                .observeOn(Schedulers.io())
                .subscribe(r -> Log.i("tuto_rxjava","current Thread: "+Thread.currentThread().getName()+" received : "+r.toString()));

    }

    void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}