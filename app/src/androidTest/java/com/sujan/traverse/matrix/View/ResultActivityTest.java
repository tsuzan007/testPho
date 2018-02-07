package com.sujan.traverse.matrix.View;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sujan.traverse.matrix.Model.TraverseService;
import com.sujan.traverse.matrix.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by macbookpro on 2/7/18.
 */
@RunWith(AndroidJUnit4.class)
public class ResultActivityTest {
    ActivityTestRule<ResultActivity> activityTestRule=new ActivityTestRule(ResultActivity.class);
    Intent intent;

    @Before
    public void init_intent(){
        intent=new Intent();
        intent.putExtra("matrix",new int[][]{{0,0},{0,0}});

    }
    @Test
    public void verifyAllViews_Displayed(){
        activityTestRule.launchActivity(intent);
        onView(withId(R.id.textView_Result)).check(matches(isDisplayed()));

    }
}