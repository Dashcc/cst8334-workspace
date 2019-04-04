package com.example.cst8334project;

import android.support.test.rule.ActivityTestRule;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> m = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = m.getActivity();
    }

    @Test
    public void testLaunch() {
        Button button = mainActivity.findViewById(R.id.button);
        assertNotNull(button);
    }

    @After
    public void testDown() throws Exception{
        mainActivity = null;
    }

}