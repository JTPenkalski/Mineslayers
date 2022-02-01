package com.mineslayers.event;

/**
 * Additional data for some events that may need it when observing mouse events.
 * @see MessageInvoker
 * @see MessageObserver
 */
public class MouseEventArgs extends EventArgs
{
    public static MouseEventArgs Empty = new MouseEventArgs(false);
    private boolean rightClick;

    public MouseEventArgs(boolean rightClick)
    {
        super();
        this.rightClick = rightClick;
    }

    public boolean isRightClick() { return rightClick; }
}