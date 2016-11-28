package com.example.zeki.customespressotest.idlingresources;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;

/**
 * Created by zeki on 25/11/2016.
 */

public class DummyForegroundWork {

    @Nullable private SimpleThreadIdlingResource idlingResource;
    Handler UIhandler;

    public DummyForegroundWork(@Nullable  SimpleThreadIdlingResource simpleThreadIdlingResource){
        idlingResource = simpleThreadIdlingResource;
        this.UIhandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                return false;
            }
        });
    }

    public void runWorkOnABackgroundThread(){
        idlingResource.setIdleState(false);
        UIhandler.postDelayed(new DummyRunnable(), 3000);
    }

    private class DummyRunnable implements Runnable{

        @Override
        public void run() {
            idlingResource.setIdleState(true);
        }
    }
}
