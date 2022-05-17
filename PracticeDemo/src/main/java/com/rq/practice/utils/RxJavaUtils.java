package com.rq.practice.utils;

import android.support.annotation.NonNull;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * RxJava工具
 *
 * @author rock you
 * @version [1.0.0 2018.8.3]
 */
public class RxJavaUtils {

    public static <T> Flowable<T> createBaseFlowable(FlowableOnSubscribe<T> source,
                                                     Scheduler onScheduler,
                                                     Scheduler onObserve,
                                                     BackpressureStrategy mode){
        return Flowable.create(source, mode)
                .subscribeOn(onScheduler)
                .observeOn(onObserve);
    }

    public static <T> void createFlowable(FlowableOnSubscribe<T> source,
                                                 BackpressureStrategy mode,
                                                 Scheduler onScheduler,
                                                 Scheduler onObserve,
                                                 Subscriber<T> subscriber){
        createBaseFlowable(source, onScheduler, onObserve, mode)
                .subscribe(subscriber);
    }

    public static <T> void createFlowable(FlowableOnSubscribe<T> source,
                                          BackpressureStrategy mode,
                                          Scheduler onScheduler,
                                          Subscriber<T> subscriber){
        createBaseFlowable(source, onScheduler, AndroidSchedulers.mainThread(), mode)
                .subscribe(subscriber);
    }

    public static <T> void createFlowable(FlowableOnSubscribe<T> source,
                                          BackpressureStrategy mode,
                                          Scheduler onScheduler,
                                          SimpleSubscriber<T> subscriber){
        createBaseFlowable(source, onScheduler, AndroidSchedulers.mainThread(), mode)
                .subscribe(subscriber);
    }

    public static <T> Observable<T> createBaseObservable(ObservableOnSubscribe<T> source,
                                                         Scheduler onScheduler,
                                                         Scheduler onObserve){
        return Observable.create(source)
                .subscribeOn(onScheduler)
                .observeOn(onObserve);
    }

    public static <T> void createObservable(ObservableOnSubscribe<T> source,
                                            Scheduler onScheduler,
                                            Scheduler onObserve,
                                            Observer<T> observer){
        createBaseObservable(source, onScheduler, onObserve)
                .subscribe(observer);
    }

    public static <T> void createObservable(ObservableOnSubscribe<T> source,
                                            Scheduler onScheduler,
                                            Observer<T> observer){
        createBaseObservable(source, onScheduler, AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static <T> void createObservable(ObservableOnSubscribe<T> source,
                                            Scheduler onScheduler,
                                            SimpleObserver<T> observer){
        createBaseObservable(source, onScheduler, AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    static class SimpleObserver<T> implements Observer<T> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull T t) {

        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    static class SimpleSubscriber<T> implements Subscriber<T>{

        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(T t) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    }
}
