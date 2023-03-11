package com.ksamorodov.task.saber.demo;

import com.ksamorodov.task.saber.enums.StrategyKind;
import com.ksamorodov.task.saber.publisher.Publisher;
import com.ksamorodov.task.saber.strategy.PublishingStrategy;
import com.ksamorodov.task.saber.strategy.impl.BatchingStrategy;
import com.ksamorodov.task.saber.strategy.impl.BroadcastStrategy;
import com.ksamorodov.task.saber.strategy.impl.RoundRobinStrategy;
import com.ksamorodov.task.saber.subscribers.Subscriber;

import java.util.concurrent.Flow;

public class Demonstration {
    public static void start(StrategyKind strategyKind, int batchSize) {
        PublishingStrategy<String> strategy = null;

        switch (strategyKind) {
            case BROADCAST -> strategy = new BroadcastStrategy<>();
            case BATCHING -> strategy = new BatchingStrategy<>(batchSize);
            case ROUND_ROBIN -> strategy = new RoundRobinStrategy<>();
        }

        Publisher<String> publisher = new Publisher<>(strategy);

        // создаем двух подписчиков
        Flow.Subscriber<String> subscriber1 = new Subscriber("Kirill");

        Flow.Subscriber<String> subscriber2 = new Subscriber("Eliza");

        // подписываем подписчиков на издателя
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        // публикуем элементы
        publisher.publish("iPhone");
        publisher.publish("MacBook");
    }
}
