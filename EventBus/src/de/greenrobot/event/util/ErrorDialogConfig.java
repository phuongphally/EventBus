package de.greenrobot.event.util;

import android.content.res.Resources;
import android.util.Log;
import de.greenrobot.event.EventBus;

public class ErrorDialogConfig {
    final Resources resources;
    final int defaultTitleId;
    final int defaultErrorMsgId;
    final ExceptionToResourceMapping mapping;

    int defaultDialogIconId;
    Class<?> defaultEventTypeOnDialogClosed;

    public ErrorDialogConfig(Resources resources, int defaultTitleId, int defaultMsgId) {
        this.resources = resources;
        this.defaultTitleId = defaultTitleId;
        this.defaultErrorMsgId = defaultMsgId;
        mapping = new ExceptionToResourceMapping();
    }

    public ErrorDialogConfig addMapping(Class<? extends Throwable> clazz, int msgId) {
        mapping.addMapping(clazz, msgId);
        return this;
    }

    public int getMessageIdForThrowable(final Throwable throwable) {
        Integer resId = mapping.mapThrowable(throwable);
        if (resId != null) {
            return resId;
        } else {
            Log.d(EventBus.TAG, "No specific message ressource ID found for " + throwable);
            return defaultErrorMsgId;
        }
    }

    public void setDefaultDialogIconId(int defaultDialogIconId) {
        this.defaultDialogIconId = defaultDialogIconId;
    }

    public void setDefaultEventTypeOnDialogClosed(Class<?> defaultEventTypeOnDialogClosed) {
        this.defaultEventTypeOnDialogClosed = defaultEventTypeOnDialogClosed;
    }
    
}