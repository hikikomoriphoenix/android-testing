package marabillas.loremar.androidtesting;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static marabillas.loremar.androidtesting.custom_view_matchers.ImageViewMatchers.withSrc;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoInteractiveTests {
    private ViewInteraction button1Interaction;
    private ViewInteraction button2Interaction;
    private ViewInteraction button3Interaction;
    private ViewInteraction imageInteraction;
    private ViewInteraction textBoxInteraction;

    @Rule
    public ActivityTestRule<InteractiveViewsActivity> mActivityTestRule = new ActivityTestRule<>
            (InteractiveViewsActivity.class);

    @Test
    public void testInteractions() {
        button1Interaction = onView(withId(R.id.i_button1));
        button2Interaction = onView(withId(R.id.i_button2));
        button3Interaction = onView(withId(R.id.i_button3));
        imageInteraction = onView(withId(R.id.i_image));
        textBoxInteraction = onView(withId(R.id.i_textBox));

        button1Interaction.check(matches(isCompletelyDisplayed()));
        button2Interaction.check(matches(isCompletelyDisplayed()));
        button3Interaction.check(matches(isCompletelyDisplayed()));
        imageInteraction.check(matches(isCompletelyDisplayed()));
        textBoxInteraction.check(matches(isCompletelyDisplayed()));

        button1Interaction.perform(click());
        imageInteraction.check(matches(withSrc(R.drawable.ic_sentiment_satisfied_black_24dp)));
        button1Interaction.perform(click());
        imageInteraction.check(matches(withSrc(R.drawable.ic_sentiment_neutral_black_24dp)));
    }
}
