package org.jeasy.states.core;

import org.assertj.core.api.Assertions;
import org.jeasy.states.api.AbstractEvent;
import org.jeasy.states.api.Context;
import org.jeasy.states.api.Event;
import org.jeasy.states.api.EventHandler;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.FiniteStateMachineException;
import org.jeasy.states.api.State;
import org.jeasy.states.api.StatefulEventHandler;
import org.jeasy.states.api.Transition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class StatefulEventHandlerTest {

    static class SideEffectEvent extends AbstractEvent {}
    static class FSMContext<T> implements Context<T> {
        T data;

        @Override
        public T getData() {
            return this.data;
        }

        @Override
        public void setData(T data) {
            this.data = data;
        }
    }

    State s1, s2;
    EventHandler<SideEffectEvent> handler;
    FiniteStateMachine fsm;

    String sideEffect = "side effect happened";

    @Before
    public void setup() {
        s1 = new State("s1");
        s2 = new State("s2");
        handler = new StatefulEventHandler<SideEffectEvent, State, State, FSMContext<String>, String>() {
            @Override
            public void handleEvent(SideEffectEvent event, State sourceState, State targetState, FSMContext<String> ctx) throws Exception {
                ctx.setData(sideEffect);
            }

            @Override
            public void handleEvent(SideEffectEvent event) throws Exception {
            }
        };
        Set<State> states = new HashSet<>();
        states.add(s1);
        states.add(s2);
        Context<String> stringContext = new FSMContext<>();
        Transition transition = new TransitionBuilder()
                .sourceState(s1)
                .targetState(s2)
                .eventType(SideEffectEvent.class)
                .eventHandler(handler)
                .build();
        fsm = new FiniteStateMachineBuilder(states, s1)
                .registerTransition(transition)
                .setInitialContext(stringContext)
                .build();
    }

    @Test
    public void testSideEffectTransition() throws FiniteStateMachineException {
        Event event = new SideEffectEvent();
        Assertions.assertThat(fsm.getContext().getData()) .isNull();
        fsm.fire(event);
        Assertions.assertThat(fsm.getContext().getData()).isEqualTo(sideEffect);
        Assertions.assertThat(fsm.getCurrentState()).isEqualTo(s2);
    }
}
