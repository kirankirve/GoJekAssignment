package com.kiran2kirve.gojekassignment;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity=mActivityTestRule.getActivity();

    }


    @Test
    public void testLaunch()
    {
        //if view present activity also present
        View view =mainActivity.findViewById(R.id.recyclerView);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        mainActivity=null;

    }




}