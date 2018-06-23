package marabillas.loremar.androidtesting;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest02 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void espressoTest02() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.activity_text), withText("Hello World!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Hello World!")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.a_list_item_textview), withText("dog"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("dog")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.a_list_item_textview), withText("horse"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_recycler_view),
                                        3),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("horse")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.activity_text), withText("Hello World!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView4.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.a_fragment_text1), withText("Hello World 2018"),
                        childAtPosition(
                                allOf(withId(R.id.NextPage),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                1)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Hello World 2018")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.a_list_item_textview), withText("dog"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("dog")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.a_list_item_textview), withText("horse"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_recycler_view),
                                        3),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("horse")));

        pressBack();

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.activity_text), withText("Hello World!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("Hello World!")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.a_list_item_textview), withText("dog"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("dog")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.a_list_item_textview), withText("horse"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_recycler_view),
                                        3),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("horse")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
