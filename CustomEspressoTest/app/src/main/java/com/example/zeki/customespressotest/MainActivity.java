package com.example.zeki.customespressotest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zeki.customespressotest.idlingresources.DummyBackgroundWork;
import com.example.zeki.customespressotest.idlingresources.DummyForegroundWork;
import com.example.zeki.customespressotest.idlingresources.SimpleThreadIdlingResource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textview;
    private static int counter = 0;
    DummyBackgroundWork aBackgroundWork;
    DummyForegroundWork aForegroundWork;

    // The Idling Resource which will be null in production.
    @Nullable  private SimpleThreadIdlingResource simpleIdlingResource;
    @Nullable private CountingIdlingResource countingIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.textView);
    }

    private void runThisButtonClick(){
        if(aForegroundWork == null){
            //aBackgroundWork = new DummyBackgroundWork(countingIdlingResource);
            aForegroundWork = new DummyForegroundWork(simpleIdlingResource);

        }

        aForegroundWork.runWorkOnABackgroundThread();
    }

    private void runThatButtonClick(){
       textview.setText("HARAAAAEEE");
    }

    /**
     * Only called from test, creates and returns a new {@link }.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleThreadIdlingResource();
        }
        return simpleIdlingResource;
    }

    /**
     * Only called from test, creates and returns a new {@link }.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getCountingIdlingResource(String resourceName) {
        if (countingIdlingResource == null) {
            countingIdlingResource = new CountingIdlingResource(resourceName);
        }
        return countingIdlingResource;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonThis){
            runThisButtonClick();
        } else if(view.getId() == R.id.buttonThat){
            runThatButtonClick();
        }
    }
}
