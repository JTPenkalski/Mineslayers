package com.mineslayers.event;

/**
 * Implement this interface to signify that a class is responsible for listening to messages others invoke.
 * @param <T> Type of object that invoked a message.
 * @param <U> Type of EventArgs data passed with a message.
 */
public interface MessageObserver<T, U extends EventArgs>
{
    /**
     * Called when an event this class is registered for is invoked.
     * @param sender The object the event originated from.
     * @param eventArgs The data passed with the event.
     */
    void onEvent(T sender, U eventArgs);
}