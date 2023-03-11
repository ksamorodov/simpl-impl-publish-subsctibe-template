package com.ksamorodov.task.saber.subscribers;

import java.util.concurrent.Flow;

public class Subscriber implements Flow.Subscriber<String> {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.printf("Subscriber %s is subscribed.%n\n", name);
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.printf("Subscriber %s received item: %s\n", name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("Subscriber %s received an error.\n", name);
    }

    @Override
    public void onComplete() {
        System.out.printf("Subscriber %s is complete.\n", name);
    }
}
