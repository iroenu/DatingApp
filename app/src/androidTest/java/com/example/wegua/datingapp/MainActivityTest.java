package com.example.wegua.datingapp;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.Espresso;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void canGoToMainActivity2() {

        onView(withId(R.id.name)).perform(typeText("Stephen Curry"));
        onView(withId(R.id.age)).perform(typeText("30"));
        onView(withId(R.id.occuption)).perform(typeText("Basketball Player"));
        onView(withId(R.id.description)).perform(typeText("He is famous basketball player"));
        try {
            Intents.init();
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.signupButton)).perform(click());
            intended(hasComponent(MainActivity2.class.getName()));
            intended(hasExtra(Constants.KEY_NAME, "Stephen Curry"));
            intended(hasExtra(Constants.KEY_AGE, "30"));
            intended(hasExtra(Constants.KEY_OCCUPTION, "Basketball Player"));
            intended(hasExtra(Constants.KEY_DESCRIPTION, "He is famous basketball player"));
        } finally {
            Intents.release();
        }
    }

}