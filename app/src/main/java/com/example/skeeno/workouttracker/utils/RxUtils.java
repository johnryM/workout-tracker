package com.example.skeeno.workouttracker.utils;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by skeeno on 27/02/2017.
 */

public class RxUtils {
    public static <T>Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            T observed = func.call();
                            if(observed != null) {
                                subscriber.onNext(observed);
                            }
                            subscriber.onCompleted();
                        } catch(Exception ex){
                            subscriber.onError(ex);
                        }
                    }
                }
        );
    }
}
