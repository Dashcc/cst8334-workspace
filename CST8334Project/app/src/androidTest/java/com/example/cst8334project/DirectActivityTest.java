package com.example.cst8334project;

import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectActivityTest {

    @Rule
    public ActivityTestRule<DirectActivity> m = new ActivityTestRule<>(DirectActivity.class);

    private DirectActivity directActivity = null;

    @Before
    public void setUp() throws Exception {
        directActivity = m.getActivity();
    }

    @Test
    public void testLaunch() {
        Button button = directActivity.findViewById(R.id.inHome_btn);
        assertNotNull(button);
    }

    @After
    public void testDown() throws Exception{
        directActivity = null;
    }

}