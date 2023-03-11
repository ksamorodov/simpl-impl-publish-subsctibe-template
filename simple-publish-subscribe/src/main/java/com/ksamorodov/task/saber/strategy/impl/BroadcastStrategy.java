package com.ksamorodov.task.saber.strategy.impl;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;

public class BroadcastStrategy<T> implements PublishingStrategy<T> {

    @Override
    public void publish(List<Subscriber<? super T>> subscribers, T item) {
        for (Subscriber<? super T> subscriber : subscribers) {
            subscriber.onNext(item);
        }
    }

}
