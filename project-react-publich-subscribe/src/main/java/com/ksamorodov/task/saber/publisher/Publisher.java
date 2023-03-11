package com.ksamorodov.task.saber.publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.Flow;

/**
 * Класс Publisher является реализацией интерфейса Flow.Publisher, который определяет
 * методы для работы с подписчиками, отправкой данных и управления подписками.
// * @param <T> тип элемента, который будет отправляться подписчикам
 */
public class Publisher implements Flow.Publisher {
    private final Flux<String> flux;

    /**
     * Конструктор Publisher.
     * @param strategy Стратегия отправки данных подписчикам
     */
    public Publisher(FluxSink.OverflowStrategy strategy) {
        this.flux = Flux.create(emitter -> {
            SubscriptionImpl subscription = new SubscriptionImpl(emitter);
            emitter.onDispose(subscription::cancel);
            emitter.onRequest(n -> {
                // not implemented
            });
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emitter.next("Message: " + i);
            }
            emitter.onCancel(subscription::cancel);
            emitter.complete();
        }, strategy);
    }

    /**
     * Метод подписки на события паблишера.
     * @param subscriber подписчик, который должен получать данные
     */
    @Override
    public void subscribe(Flow.Subscriber subscriber) {
        flux.subscribe(subscriber::onNext, subscriber::onError, subscriber::onComplete);
    }

    /**
     * Внутренний класс, реализующий интерфейс Flow.Subscription.
     * Используется для управления подписками подписчиков.
     */
    private class SubscriptionImpl implements Flow.Subscription {

        private final FluxSink emitter;

        public SubscriptionImpl(FluxSink emitter) {
            this.emitter = emitter;
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
            emitter.complete();
        }
    }
}

