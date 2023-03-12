package com.ksamorodov.task.saber;

import com.ksamorodov.task.saber.demo.Demonstration;
import reactor.core.publisher.FluxSink;

public class Application {
    /**
     * Метод, запускающий демонстрацию работы паттерна Publish-Subscribe.
     *
     * @param args аргументы командной строки, задающие настройки демонстрации
     *             в следующем порядке: стратегия паблишера (например, "IGNORE", "ERROR", DROP)
     */
    public static void main(String[] args) {
        FluxSink.OverflowStrategy strategyKind = null;

        // Проверяем количество аргументов командной строки и устанавливаем значение strategyKind и batchSize в соответствии с этим
        if (args.length == 1) {
            strategyKind = FluxSink.OverflowStrategy.valueOf(args[0].toUpperCase()); // Приводим значение аргумента к верхнему регистру для более надежного сравнения
        } else {
            // Если количество аргументов не соответствует ожидаемому, выводим сообщение об ошибке и завершаем программу
            System.err.println("Usage: java Application [strategy]");
            System.exit(1);
        }

        // Запускаем демонстрацию с заданными параметрами
        Demonstration.start(strategyKind);
    }
}
