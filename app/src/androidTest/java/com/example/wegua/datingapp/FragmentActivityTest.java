package com.example.wegua.datingapp;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
public class FragmentActivityTest {

    @Rule
    public ActivityTestRule<FragmentActivity> activityTestRule
            = new ActivityTestRule<FragmentActivity>(FragmentActivity.class){
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


    @Test
    public void canGoToFragments() {
        try {
            Espresso.closeSoftKeyboard();
            Intents.init();

            onView(withText("Matches")).perform(click());
            onView(withId(R.id.textView50)).check(matches(withText((R.string.match_text))));

            TestUtils.rotateScreen(activityTestRule.getActivity());

            onView(withText("Setting")).perform(click());
            onView(withId(R.id.textView60)).check(matches(withText((R.string.setting_text))));
            TestUtils.rotateScreen(activityTestRule.getActivity());

            onView(withText("Profile")).perform(click());
            TestUtils.rotateScreen(activityTestRule.getActivity());
        } finally {
            Intents.release();
        }
    }
}