package com.sujan.traverse.matrix.View;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Toast;

import com.sujan.traverse.matrix.HelperClass.Position;
import com.sujan.traverse.matrix.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by macbookpro on 2/7/18.
 */
@RunWith(AndroidJUnit4.class)
public class TableActivityTest {
    ActivityTestRule<TableActivity> activityTestRule=new ActivityTestRule(TableActivity.class);
    Intent intent;


    @Before
    public void init_intent(){
        intent=new Intent();
        intent.putExtra("row",3);
        intent.putExtra("col",3);

    }
    @Test
    public void verifyAllViews_displayed(){
        int count=0;
        activityTestRule.launchActivity(intent);
        onView(withText("Calculate")).check(matches(isDisplayed()));
        for (int j=0;j<intent.getIntExtra("row",0);j++){
            for (int i=0;i<intent.getIntExtra("col",0);i++){
                onView(withId(count)).check(matches(isDisplayed()));
                count=count+1;
            }
        }

    }

    @Test
    public void verify_AllIntInput_CalculationIsSuccess(){
        int count=0;
        activityTestRule.launchActivity(intent);
        for (int j=0;j<intent.getIntExtra("row",0);j++){
            for (int i=0;i<intent.getIntExtra("col",0);i++){
                onView(withId(count)).check(matches(isDisplayed())).perform(typeText("1"));
                count=count+1;
            }
        }
        onView(withText("Calculate")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.textView_Result)).check(matches(isDisplayed()));

    }

    @Test
    public void verify_AllNegativeInput_CalculationIsSuccess(){
        int count=0;
        activityTestRule.launchActivity(intent);
        onView(withText("Calculate")).check(matches(isDisplayed()));
        for (int j=0;j<intent.getIntExtra("row",0);j++){
            for (int i=0;i<intent.getIntExtra("col",0);i++){
                onView(withId(count)).check(matches(isDisplayed())).perform(typeText("-1"));
                count=count+1;
            }
        }
        onView(withText("Calculate")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.textView_Result)).check(matches(isDisplayed()));

    }
    @Test
    public void verify_InvalidInput_ShowDialogBox(){
        int count=0;
        activityTestRule.launchActivity(intent);
        onView(withText("Calculate")).check(matches(isDisplayed()));
        for (int j=0;j<intent.getIntExtra("row",0);j++){
            for (int i=0;i<intent.getIntExtra("col",0);i++){
                onView(withId(count)).check(matches(isDisplayed())).perform(typeText("a"));
                count=count+1;
            }
        }
        onView(withText("Calculate")).check(matches(isDisplayed())).perform(click());
        onView(withText("Invalid Matrix.")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

}