package com.example.zeki.customespressotest.idlingresources;

import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;

/**
 * Created by zeki on 28/11/2016.
 */

public class DummyBackgroundWork {

    CountingIdlingResource idlingResource;

    public DummyBackgroundWork(CountingIdlingResource idlingResource){
        this.idlingResource = idlingResource;
    }

    public void runWorkOnABackgroundThread(){
        idlingResource.increment();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000); // background work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                idlingResource.decrement();
            }
        }).run();
    }
}
