package marabillas.loremar.androidtesting;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class AndroidJUnit4Tests {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity
            .class);

    @Test
    public void trueTest() {
        assertThat(activityTestRule.getActivity().assert_true, is(true));
    }

    @Test
    public void testContext() {
        assertThat(activityTestRule.getActivity().getApplicationContext().getPackageName(), is
                ("marabillas.loremar.androidtesting"));
    }
}
