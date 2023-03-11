package com.ksamorodov.task.saber;

import com.ksamorodov.task.saber.publisher.Publisher;
import com.ksamorodov.task.saber.strategy.PublishingStrategy;
import com.ksamorodov.task.saber.strategy.impl.BroadcastStrategy;
import com.ksamorodov.task.saber.subscribers.Subscriber1;
import com.ksamorodov.task.saber.subscribers.Subscriber2;

import java.util.concurrent.Flow;

public class Application {
    public static void main(String[] args) {

        PublishingStrategy<String> broadcastStrategy = new BroadcastStrategy<>();
        Publisher<String> publisher = new Publisher<>(broadcastStrategy);

        // создаем двух подписчиков
        Flow.Subscriber<String> subscriber1 = new Subscriber1();

        Flow.Subscriber<String> subscriber2 = new Subscriber2();

        // подписываем подписчиков на издателя
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        // публикуем элементы
        publisher.publish("item 1");
        publisher.publish("item 2");

    }
}
