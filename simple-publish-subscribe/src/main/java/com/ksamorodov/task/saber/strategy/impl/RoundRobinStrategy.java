package com.ksamorodov.task.saber.strategy.impl;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.List;
import java.util.concurrent.Flow;

public class RoundRobinStrategy<T> implements PublishingStrategy<T> {

    private int currentIndex = 0;

    @Override
    public void publish(List<Flow.Subscriber<? super T>> subscribers, T item) {
        if (subscribers.isEmpty()) {
            return;
        }

        Flow.Subscriber<? super T> subscriber = subscribers.get(currentIndex);

        // Publish item to current subscriber
        subscriber.onNext(item);

        // Update current index
        currentIndex = (currentIndex + 1) % subscribers.size();
    }

}

