package com.ksamorodov.task.saber.subscribers;

import java.util.concurrent.Flow;

public class Subscriber implements Flow.Subscriber<String> {
    private final String name;
    /**
     * Конструктор подписчика с заданием имени.
     * @param name имя подписчика
     */
    public Subscriber(String name) {
        this.name = name;
    }

    /**
     * Метод, вызываемый при подписке подписчика на издателя.
     * Устанавливает начальное значение количества запрашиваемых элементов.
     * @param subscription объект, предоставляющий подписчику методы для управления подпиской
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.printf("Subscriber %s is subscribed.%n\n", name);
        subscription.request(1);
    }

    /**
     * Метод, вызываемый при получении нового элемента от издателя.
     * @param item новый элемент, полученный от издателя
     */
    @Override
    public void onNext(String item) {
        System.out.printf("Subscriber %s received item: %s\n", name, item);
    }

    /**
     * Метод, вызываемый при возникновении ошибки при получении элементов от издателя.
     * @param throwable объект, представляющий возникшую ошибку
     */
    @Override
    public void onError(Throwable throwable) {
        System.out.printf("Subscriber %s received an error.\n", name);
    }

    /**
     * Метод, вызываемый при завершении работы издателя.
     */
    @Override
    public void onComplete() {
        System.out.printf("Subscriber %s is complete.\n", name);
    }
}
