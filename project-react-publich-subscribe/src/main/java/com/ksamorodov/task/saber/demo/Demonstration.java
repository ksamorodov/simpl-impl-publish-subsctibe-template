package com.ksamorodov.task.saber.demo;

import com.ksamorodov.task.saber.publisher.Publisher;
import com.ksamorodov.task.saber.subscribers.Subscriber;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.Flow;

/**
 * Класс Demonstration содержит один статический метод start,
 * который запускает демонстрационный пример работы с издателем Publisher
 * и подписчиками Subscriber.
 */
public class Demonstration {
    /**
     * Метод start запускает демонстрацию работы паблишера с определенной стратегией и размером пакета.
     * @param strategy - выбранная стратегия паблишера
     */
    public static void start(FluxSink.OverflowStrategy strategy) {

        // Создаем паблишера с выбранной стратегией
        Publisher publisher = new Publisher(strategy);

        // создаем подписчиков
        Flow.Subscriber<String> subscriber1 = new Subscriber("Kirill");
        Flow.Subscriber<String> subscriber2 = new Subscriber("Eliza");
        Flow.Subscriber<String> subscriber3 = new Subscriber("Harry");

        // подписываем подписчиков на издателя
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

//        // публикуем элементы
//        publisher.publish("iPhone");
//        publisher.publish("MacBook");
    }
}
