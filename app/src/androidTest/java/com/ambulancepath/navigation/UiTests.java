package com.ambulancepath.navigation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private final Context appContext = getInstrumentation().getTargetContext();
    private final String key = "user_type";
    private final SharedPreferences preferences = appContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);

    public void resetPrefs() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    @Test
    public void asserUserTypeAsUser() {
        resetPrefs();
        onView(withId(R.id.button_user)).perform(click());
        String user_type = preferences.getString(key, "abc");
        assertEquals(user_type, "user");
        resetPrefs();
    }

    @Test
    public void asserUserTypeAsDriver() {
        resetPrefs();
        onView(withId(R.id.button_driver)).perform(click());
        String user_type = preferences.getString(key, "abc");
        assertEquals(user_type, "driver");
        resetPrefs();
    }
}
