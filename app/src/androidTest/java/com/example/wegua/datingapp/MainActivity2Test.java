package com.example.wegua.datingapp;

import static org.junit.Assert.*;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class MainActivity2Test {

    @Rule
    public ActivityTestRule<MainActivity2> activityTestRule
            = new ActivityTestRule<MainActivity2>(MainActivity2.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(Constants.KEY_NAME, "Stephen Curry");
            testIntent.putExtra(Constants.KEY_OCCUPTION, "Basketball Player");
            testIntent.putExtra(Constants.KEY_DESCRIPTION, "He is famous basketball player");
            testIntent.putExtra(Constants.KEY_AGE, "30");
            return testIntent;
        }
    };

    @Test
    public void displayRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.nameMessage))
                .check(matches(withText("Stephen Curry")));
        onView(withId(R.id.ageMessage))
                .check(matches(withText("30")));
        onView(withId(R.id.descriptionMessage))
                .check(matches(withText("He is famous basketball player")));
        onView(withId(R.id.occuptionMessage))
                .check(matches(withText("Basketball Player")));
    }

}