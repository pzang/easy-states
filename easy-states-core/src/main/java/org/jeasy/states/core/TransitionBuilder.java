/**
 * The MIT License
 *
 *  Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.jeasy.states.core;

import org.jeasy.states.api.EventHandler;
import org.jeasy.states.api.State;
import org.jeasy.states.api.Transition;

/**
 * FSM transition builder : this class should be used to build FSM transition instances:
 *
 * <ul>
 *     <li>Source and target states must be declared in FSM states set.</li>
 *     <li>Source and target states must be defined (not null)</li>
 *     <li>Event Type must be defined (not null)</li>
 *     <li>Event Handler is not mandatory</li>
 * </ul>
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class TransitionBuilder {

    /**
     * The transition instance to build.
     */
    private TransitionImpl transition;

    /**
     * Public constructor.
     */
    public TransitionBuilder() {
        transition = new TransitionImpl();
    }

    /**
     * Set the transition name.
     * @param name transition name
     * @return FSM transition builder
     */
    public TransitionBuilder name(final String name) {
        transition.setName(name);
        return this;
    }

    /**
     * Set transition source state.
     * @param sourceState transition source state
     * @return FSM transition builder
     */
    public TransitionBuilder sourceState(final State sourceState) {
        transition.setSourceState(sourceState);
        return this;
    }

    /**
     * Set transition target state.
     * @param targetState transition target state
     * @return FSM transition builder
     */
    public TransitionBuilder targetState(final State targetState) {
        transition.setTargetState(targetState);
        return this;
    }

    /**
     * Set transition event type.
     * @param eventType transition event type
     * @return FSM transition builder
     */
    public TransitionBuilder eventType(final Class eventType) {
        transition.setEventType(eventType);
        return this;
    }

    /**
     * Set transition event handler. Event handler is not mandatory.
     * @param eventHandler transition event handler
     * @return FSM transition builder
     */
    public TransitionBuilder eventHandler(final EventHandler eventHandler) {
        transition.setEventHandler(eventHandler);
        return this;
    }

    /**
     * Build a transition instance.
     * @return a transition instance.
     */
    public Transition build() {
        return transition;
    }

}
