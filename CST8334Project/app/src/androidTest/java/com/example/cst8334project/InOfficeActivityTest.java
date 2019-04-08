package com.example.cst8334project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;
import android.widget.CheckBox;
import android.widget.EditText;


import static org.junit.Assert.*;

public class InOfficeActivityTest {

    @Rule
    public ActivityTestRule<InOfficeActivity> m = new ActivityTestRule<>(InOfficeActivity.class);

    private InOfficeActivity inOfficeActivity = null;

    @Before
    public void setUp() throws Exception {
        inOfficeActivity = m.getActivity();
    }

    @Test
    public void testLaunch() {
         CheckBox cb = inOfficeActivity.findViewById(R.id.CB1);
        assertNotNull(cb);
    }

    @After
    public void testDown() throws Exception{
        inOfficeActivity = null;
    }
}