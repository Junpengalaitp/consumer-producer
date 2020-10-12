package com.junpenghe;


/**
 * Normally, the pressure is from producer to consumer, back pressure does the opposite,
 * consumer gives pressure to producer, if the consumer is not ready, it will notify the producer
 * to not produce.
 *
 * @author Junpeng.He
 */

public class BackPressure {
    public boolean consumerAvailable(ThreadPool threadPool) {
        return threadPool.available();
    }
}
