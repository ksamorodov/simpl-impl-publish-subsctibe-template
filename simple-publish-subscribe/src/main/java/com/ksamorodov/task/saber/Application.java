package com.ksamorodov.task.saber;

import com.ksamorodov.task.saber.publisher.Publisher;
import com.ksamorodov.task.saber.strategy.PublishingStrategy;
import com.ksamorodov.task.saber.strategy.impl.BroadcastStrategy;

import java.util.concurrent.Flow;

public class Application {
    public static void main(String[] args) {

        PublishingStrategy<String> broadcastStrategy = new BroadcastStrategy<>();
        Publisher<String> publisher = new Publisher<>(broadcastStrategy);

        // создаем двух подписчиков
        Flow.Subscriber<String> subscriber1 = new Flow.Subscriber<String>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("Subscriber 2 is subscribed.");
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("Subscriber 2 received item: " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Subscriber 2 received an error.");
            }

            @Override
            public void onComplete() {
                System.out.println("Subscriber 2 is complete.");
            }
        };

        Flow.Subscriber<String> subscriber2 = new Flow.Subscriber<String>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("Subscriber 2 is subscribed.");
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("Subscriber 2 received item: " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Subscriber 2 received an error.");
            }

            @Override
            public void onComplete() {
                System.out.println("Subscriber 2 is complete.");
            }
        };

        // подписываем подписчиков на издателя
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        // публикуем элементы
        publisher.publish("item 1");
        publisher.publish("item 2");

    }
}
