package com.example.experimentapp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsRule;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {


    private ActivityScenario<MainActivity> mainActivityActivityScenario;


    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);


  /*  @Rule
    private ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);*/

    @Rule
    public IntentsRule mainActivityIntentsTestRule = new IntentsRule();


    @Before
    public void setUp() {
  /*      mainActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
        mainActivityActivityScenario.moveToState(Lifecycle.State.RESUMED);*/
    }

    @Test
    public void testButtonClick() {
       // onView(withId(R.id.button_one)).perform(click());
        mainActivityActivityScenarioRule.getScenario().onActivity(activity -> {
            AppCompatButton button = activity.findViewById(R.id.button_one);
            String btnText = button.getText().toString().trim();
            assertEquals(btnText,"Button One");
        });
       /* mainActivityActivityScenario.onActivity(activity -> {
            AppCompatButton button = activity.findViewById(R.id.button_one);
            String btnText = button.getText().toString().trim();
            assertEquals(btnText,"Button One");
        });*/
     /*   Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String expectedMessage = context.getString(R.string.toast_msg_tst);
         onView(withText(expectedMessage));*/
      //  intended(hasComponent(SecondActivity.class.getName()));
        /*mainActivityActivityScenario.onActivity(activity -> {
            activity.isEmpty = true;

        });*/
        /* onView(withId(R.id.button_one)).perform(click());
        ViewInteraction viewInteraction =  onView(withId(R.id.button_two));
        viewInteraction.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));*/
      /*  onView(withId(R.id.name)).perform(typeText("Farman Ali Khan"));
        onView(withId(R.id.btn_save)).perform(click());
        Context appContext =InstrumentationRegistry.getInstrumentation().getTargetContext();*/
       // String errorMessage = appContext.getString(R.string.name_error);
       /* assertEquals(errorMessage,)*/
       /* SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper(appContext);
        String savedName = sharedPrefrenceHelper.getStringValue("name");
        assertEquals(savedName,"Farman Ali Khan");
        SystemClock.sleep(500);
        intended(hasComponent(MainActivity.class.getName()));*/


    }


    @After
    public void close() {
      //  mainActivityActivityScenario.close();
    }


}