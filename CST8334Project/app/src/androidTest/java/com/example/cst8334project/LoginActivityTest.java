package com.example.cst8334project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> m = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity loginActivity = null;

    @Before
    public void setUp() throws Exception {
        loginActivity = m.getActivity();
    }

    @Test
    public void testLaunch() {
        EditText editText = loginActivity.findViewById(R.id.edit_pass);
        assertNotNull(editText);
    }

    @After
    public void testDown() throws Exception{
        loginActivity = null;
    }
}