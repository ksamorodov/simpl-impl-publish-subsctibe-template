package com.ksamorodov.task.saber.publisher;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

/**
 * Класс Publisher является реализацией интерфейса Flow.Publisher, который определяет
 * методы для работы с подписчиками, отправкой данных и управления подписками.
 * @param <T> тип элемента, который будет отправляться подписчикам
 */
public class Publisher<T> implements Flow.Publisher<T> {
    // Список подписчиков
    private List<Flow.Subscriber<? super T>> subscribers = new ArrayList<>();

    // Стратегия отправки данных подписчикам
    private PublishingStrategy<T> strategy;

    /**
     * Конструктор Publisher.
     * @param strategy Стратегия отправки данных подписчикам
     */
    public Publisher(PublishingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * Метод подписки на события паблишера.
     * @param subscriber подписчик, который должен получать данные
     */
    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        subscribers.add(subscriber);
        subscriber.onSubscribe(new SubscriptionImpl(subscriber));
    }

    /**
     * Внутренний класс, реализующий интерфейс Flow.Subscription.
     * Используется для управления подписками подписчиков.
     */
    private class SubscriptionImpl implements Flow.Subscription {

        private Flow.Subscriber<? super T> subscriber;

        public SubscriptionImpl(Flow.Subscriber<? super T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            // not implemented
        }

        /**
         * Метод отмены подписки на события паблишера.
         */
        @Override
        public void cancel() {
            subscribers.remove(subscriber);
        }

    }

    /**
     * Метод отправки данных подписчикам с использованием заданной стратегии.
     * @param item данные, которые нужно отправить подписчикам
     */
    public void publish(T item) {
        strategy.publish(subscribers, item);
    }

}

