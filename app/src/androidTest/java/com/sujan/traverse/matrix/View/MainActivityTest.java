package com.sujan.traverse.matrix.View;

import android.content.Intent;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sujan.traverse.matrix.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.provider.Settings.System.getString;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by macbookpro on 2/7/18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule(MainActivity.class);

    @Test
    public void VerifyAllViews_Displayed(){
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.editText_Row)).check(matches(isDisplayed())).check(matches(withHint("Number of Rows")));
        onView(withId(R.id.editText_Col)).check(matches(isDisplayed())).check(matches(withHint("Number of Columns")));
        onView(withId(R.id.button_Submit)).check(matches(isDisplayed())).check(matches(withText("Submit")));
        onView(withId(R.id.textView_Row)).check(matches(isDisplayed())).check(matches(withText("Enter number of rows")));
        onView(withId(R.id.textView_col)).check(matches(isDisplayed())).check(matches(withText("Enter number of columns")));
        onView(withId(R.id.textView)).check(matches(isDisplayed())).check(matches(withText("Please enter values below:")));

    }


    @Test
    public void SubmitButton_DisplayedWithRightText(){
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.button_Submit)).check(matches(isDisplayed())).perform(click());
        onView(withText("Invalid Matrix.")).check(matches(isDisplayed()));
        onView(withText("Non numeric!")).check(matches(isDisplayed()));
    }

    @Test
    public void submitSuccess(){
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.editText_Row)).check(matches(isDisplayed())).perform(typeText("3"));
        onView(withId(R.id.editText_Col)).check(matches(isDisplayed())).perform(typeText("3"));
        onView(withId(R.id.button_Submit)).check(matches(isDisplayed())).perform(click());
        onView(withText("Calculate")).check(matches(isDisplayed()));

    }

}