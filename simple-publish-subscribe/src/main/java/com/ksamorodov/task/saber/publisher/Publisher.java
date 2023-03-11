package com.ksamorodov.task.saber.publisher;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class Publisher<T> implements Flow.Publisher<T> {

    private List<Flow.Subscriber<? super T>> subscribers = new ArrayList<>();
    private PublishingStrategy<T> strategy;

    public Publisher(PublishingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        subscribers.add(subscriber);
        subscriber.onSubscribe(new SubscriptionImpl(subscriber));
    }

    private class SubscriptionImpl implements Flow.Subscription {

        private Flow.Subscriber<? super T> subscriber;

        public SubscriptionImpl(Flow.Subscriber<? super T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            // not implemented
        }

        @Override
        public void cancel() {
            subscribers.remove(subscriber);
        }

    }

    public void publish(T item) {
        strategy.publish(subscribers, item);
    }

}

