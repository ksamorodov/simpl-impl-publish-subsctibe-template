package com.ksamorodov.task.saber.strategy.impl;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class BatchingStrategy<T> implements PublishingStrategy<T> {

    private final int batchSize;
    private List<T> items = new ArrayList<>();

    public BatchingStrategy(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public void publish(List<Flow.Subscriber<? super T>> subscribers, T item) {
        // Add item to list of items
        items.add(item);

        // If batch size is reached, publish items to all subscribers
        if (items.size() == batchSize) {
            for (Flow.Subscriber<? super T> subscriber : subscribers) {
                for (T itemToPublish : items) {
                    subscriber.onNext(itemToPublish);
                }
            }

            // Clear list of items
            items.clear();
        }
    }

    // Publish any remaining items when publisher is done
    public void publishRemaining(List<Flow.Subscriber<? super T>> subscribers) {
        for (Flow.Subscriber<? super T> subscriber : subscribers) {
            for (T itemToPublish : items) {
                subscriber.onNext(itemToPublish);
            }

            // Signal completion to subscriber
            subscriber.onComplete();
        }
    }

}
