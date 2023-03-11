package com.ksamorodov.task.saber.enums;

/**
 * Перечисление, определяющее возможные стратегии паблишера.
 */
public enum StrategyKind {

    /**
     * Стратегия BROADCAST позволяет рассылать каждое сообщение каждому подписчику.
     */
    BROADCAST,

    /**
     * Стратегия BATCHING позволяет рассылать сообщения в пакетах определенного размера
     */
    BATCHING,

    /**
     * Стратегия ROUND_ROBIN позволяет рассылать сообщения подписчикам по кругу.
     */
    ROUND_ROBIN
}
