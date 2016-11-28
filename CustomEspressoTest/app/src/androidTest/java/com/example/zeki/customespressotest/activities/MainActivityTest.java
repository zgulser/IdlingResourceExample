package com.example.zeki.customespressotest.activities;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.zeki.customespressotest.MainActivity;
import com.example.zeki.customespressotest.R;
import com.example.zeki.customespressotest.idlingresources.SimpleThreadIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by zeki on 25/11/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    //private CountingIdlingResource idlingResource;
    private SimpleThreadIdlingResource idlingResource;

    @Before
    public void registerIdlingResource(){
        //idlingResource = (CountingIdlingResource) activityRule.getActivity().getCountingIdlingResource("BackbaseCountingidlingResource");
        idlingResource = (SimpleThreadIdlingResource) activityRule.getActivity().getIdlingResource();
        Log.i("Test", ">> This registered: " + Espresso.registerIdlingResources(idlingResource));
    }

    @Test
    public void testButtonClick(){
        Log.i("Test", ">> IdlingResourceTest: Right before first click.Waiting...");
        onView(withId(R.id.buttonThis)).perform(click());
        Log.i("Test", ">> IdlingResourceTest: After first click.");
        onView(withId(R.id.buttonThat)).perform(click());
        Log.i("Test", ">> IdlingResourceTest: After second click.");
    }

    @After
    public void unregisterIdlingResource(){
        if(idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
