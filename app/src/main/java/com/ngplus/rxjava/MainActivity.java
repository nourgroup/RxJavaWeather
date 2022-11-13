package com.ngplus.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.ngplus.rxjava.databinding.ActivityMainBinding;
import com.ngplus.rxjava.models.ResponseStatus;
import com.ngplus.rxjava.viewmodels.MyDataViewModel;


import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding _binding = null;
    MyDataViewModel mMyDataViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
        Log.i("tuto_rxjava", "before ViewModelProvider");
        mMyDataViewModel = new ViewModelProvider(this).get(MyDataViewModel.class);
        Log.i("tuto_rxjava", "after ViewModelProvider");
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
        /*****************Factory method create******************/
        Integer tab[] = new Integer[5];
        tab[0] = 1;tab[1] = 2;tab[2] = 3;tab[3] = 4;tab[4] = 5;
        Observable listObservable = Observable.fromArray(tab).repeat(2);
        //listObservable.subscribe(ob);
        /** RXJava Threading operators | Schedulers, SubscribeOn and ObserveOn **/
        /*Log.i("tuto_rxjava","test");
        Observable.just(1,2,3,4)
                .doOnNext(e -> Log.i("tuto_rxjava","current Thread: "+Thread.currentThread().getName()+" send : "+e.toString()))
                .subscribeOn(Schedulers.computation())
                .map(a -> a*2)
                .observeOn(Schedulers.io())
                .subscribe(r -> Log.i("tuto_rxjava","current Thread: "+Thread.currentThread().getName()+" received : "+r.toString()));
                */
        /**********************RXJava operators | map, flatMap, debounce, filter and more**********************/
        //37.8267,-122.4233
        mMyDataViewModel.getWeather(37.8267,-122.4233);
        mMyDataViewModel.getWeatherState().observe(this, (a) -> {
            Log.i("tuto_rxjava","MainActivity Response : "+a.status.toString());
        });
        Observable observableText = Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                _binding.display.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.length()!=0)
                            Log.i("","");

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }).observeOn(Schedulers.io()).map(new Function<Object, Object>()  {
            @Override
            public Object apply(Object o) throws Throwable {

                return null;
            }
        }).doOnNext(e -> Log.i("tuto_rxjava","upstream:"+e+"\nCurrent thread 2: "+Thread.currentThread().getName()))
            .debounce(3,TimeUnit.SECONDS)
            .distinctUntilChanged();
        observableText.subscribe(e -> Log.i("tuto_rxjava","downstream:"+e.toString()));
    }

    Observable callAPI(int ms){
        Observable observable = Observable.just("api was called");
        observable.subscribe(e -> {
            Log.i("", "");
        });
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return observable;
    }
}