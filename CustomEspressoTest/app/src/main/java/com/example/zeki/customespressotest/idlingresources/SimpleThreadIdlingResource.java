package com.example.zeki.customespressotest.idlingresources;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zeki on 25/11/2016.
 */

public class SimpleThreadIdlingResource implements IdlingResource {

    @Nullable private volatile ResourceCallback resourceCallback;
    private AtomicBoolean idlingState = new AtomicBoolean(true);

    public SimpleThreadIdlingResource() {
    }

    @Override
    public String getName() {
        return SimpleThreadIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        return this.idlingState.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

    public void setIdleState(boolean idlingState){
        this.idlingState.set(idlingState);
        if (idlingState && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}
