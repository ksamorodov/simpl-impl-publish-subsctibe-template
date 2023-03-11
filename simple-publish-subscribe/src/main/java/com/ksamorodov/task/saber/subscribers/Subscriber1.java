package com.ksamorodov.task.saber.subscribers;

import java.util.concurrent.Flow;

public class Subscriber1 implements Flow.Subscriber<String> {
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Subscriber 1 is subscribed.");
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.println("Subscriber 1 received item: " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Subscriber 1 received an error.");
    }

    @Override
    public void onComplete() {
        System.out.println("Subscriber 1 is complete.");
    }
}
