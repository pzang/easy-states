package org.jeasy.states.api;

/**
 * A context interface that persist through the lifecycle of a state machine.
 * Since we only do one transition at a time, the context data inherently thread safe.
 *
 * @param <T> context data type.
 */
public interface Context<T> {
    T getData();

    void setData(T data);
}
