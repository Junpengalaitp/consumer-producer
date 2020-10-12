package com.junpenghe;

/**
 * @author Junpeng He
 */
public interface EnPoolStrategy<T> {

    /**
     * check the task is valid for processing
     *
     * @return True if the ThreadPool should create a new thread and execute
     */
    default boolean eligibleToExecute(T t) {
        return true;
    }
}
