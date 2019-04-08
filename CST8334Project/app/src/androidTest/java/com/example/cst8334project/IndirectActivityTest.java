package com.example.cst8334project;

import android.support.test.rule.ActivityTestRule;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndirectActivityTest {
    @Rule
    public ActivityTestRule<IndirectActivity> m = new ActivityTestRule<>(IndirectActivity.class);

    private IndirectActivity indirectActivity = null;

    @Before
    public void setUp() throws Exception {
        indirectActivity = m.getActivity();
    }

    @Test
    public void testLaunch() {
        Button button = indirectActivity.findViewById(R.id.btn_directSubmit);
        assertNotNull(button);
    }

    @After
    public void testDown() throws Exception{
        indirectActivity = null;
    }
}