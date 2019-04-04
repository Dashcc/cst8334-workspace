package com.example.cst8334project;

import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.support.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class InHomeActivityTest {
    @Rule
    public ActivityTestRule<InHomeActivity> m = new ActivityTestRule<>(InHomeActivity.class);

    private InHomeActivity inHomeActivity = null;

    @Before
    public void setUp() throws Exception {
        inHomeActivity = m.getActivity();
    }

    @Test
    public void testLaunch() {
        EditText editText = inHomeActivity.findViewById(R.id.editTextName);
        assertNotNull(editText);
    }

    @After
    public void testDown() throws Exception{
        inHomeActivity = null;
    }
}