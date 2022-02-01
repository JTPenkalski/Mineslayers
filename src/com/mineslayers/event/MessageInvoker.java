package com.mineslayers.event;

/**
 * Implement this interface to signify that a class is responsible for broadcasting messages others may wish to respond
 * to.
 * @param <T> Type of object that invokes messages.
 * @param <U> Type of EventArgs data to pass with the message.
 */
public interface MessageInvoker<T, U extends EventArgs>
{
    /**
     * Adds a class to a list of observers to notify when invoke is called.
     * @param observer The class to register.
     */
    void addObserver(MessageObserver<T, U> observer);

    /**
     * Notifies all observers that an event has occurred.
     * @param sender The initiator of the event.
     * @param eventArgs Any data to pass with the event.
     */
    void invoke(T sender, U eventArgs);

    /**
     * Removes a class from a list of observers to notify when invoke is called.
     * @param observer The class to deregister.
     */
    void removeObserver(MessageObserver<T, U> observer);
}