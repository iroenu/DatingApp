package com.example.wegua.datingapp;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static com.example.wegua.datingapp.TestUtils.waitFor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

import android.support.test.espresso.contrib.RecyclerViewActions;

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

            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(1));
            
            TestUtils.rotateScreen(activityTestRule.getActivity());
            TestUtils.rotateScreen(activityTestRule.getActivity());

            onView(withText("Setting")).perform(click());

            onView(withId(R.id.name)).perform(typeText("AWP"));

            onView(isRoot()).perform(waitFor(1000));
            onView(withId(R.id.hour)).perform(click());
            onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
            Espresso.closeSoftKeyboard();

            onView(isRoot()).perform(waitFor(1000));
            onView(withId(R.id.minute)).perform(click());
            onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());

            onView(isRoot()).perform(waitFor(1000));
            onView(withId(R.id.mile)).perform(typeText("50"));
            Espresso.closeSoftKeyboard();

            onView(isRoot()).perform(waitFor(1000));
            onView(withId(R.id.gender)).perform(click());
            onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
            Espresso.closeSoftKeyboard();

            onView(withId(R.id.rangeLow)).perform(typeText("18"));
            onView(withId(R.id.rangeHigh)).perform(typeText("50"));

            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButton)).perform(click());
            Espresso.closeSoftKeyboard();
            TestUtils.rotateScreen(activityTestRule.getActivity());

            onView(withText("Profile")).perform(click());
            TestUtils.rotateScreen(activityTestRule.getActivity());
        } finally {
            Intents.release();
        }
    }
}