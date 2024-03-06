package io.github.amayaframework.events;

/**
 * An enumeration of the three main policies for calling the function associated with the event.
 */
public enum FirePolicy {

    /**
     * A policy that ensures that the event call is completed after the trigger is called.
     */
    BLOCKING,

    /**
     * A policy that instructs the trigger to start executing the event in parallel.
     */
    PARALLEL,

    /**
     * A policy that grants the trigger the right to choose how to invoke the event.
     */
    ANY
}
