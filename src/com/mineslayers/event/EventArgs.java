package com.mineslayers.event;

/**
 * Base class for all data arguments passed when the event system is invoked.
 * @see MessageInvoker
 * @see MessageObserver
 */
public class EventArgs
{
    public static EventArgs Empty = new EventArgs();

    public EventArgs() { }
}