package org.jeasy.states.api;

/**
 * This handler handles event with side effects to the state and context.
 *
 * @param <E> Event type.
 * @param <S> Source State.
 * @param <T> Target State.
 * @param <C> Context associated with current FSM.
 */
public interface StatefulEventHandler<E extends Event, S extends State, T extends State, C extends Context> extends EventHandler<E> {
    void handleEvent(E event, S sourceState, T targetState, C ctx) throws Exception;
}
