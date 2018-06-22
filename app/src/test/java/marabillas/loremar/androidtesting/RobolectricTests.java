package marabillas.loremar.androidtesting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class RobolectricTests {
    private MainActivity activity;

    @Before
    public void init() {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testMockitoWithRobolectricRunner() {
        SomeComplexClass someComplexClass = mock(SomeComplexClass.class);
        activity.processInvolvingSomeComplexClass(someComplexClass);
        when(someComplexClass.methodA()).then(Answers.CALLS_REAL_METHODS);
        assertThat(someComplexClass.methodA(), is("hi"));
    }

    @Test
    public void testEditBoxDialog() {
        EditBoxDialog dialog = new EditBoxDialog(activity) {
            @Override
            void ok(String text) {
                assertThat(text, is("hello world"));
            }
        };
        AlertDialog alertDialog = dialog.getAlertDialog();
        EditText box = dialog.getBox();
        assertThat(alertDialog.isShowing(), is(true));
        assertThat(alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).getVisibility(), is(View
                .VISIBLE));
        assertThat(alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled(), is(true));
        assertThat(box.getVisibility(), is(View.VISIBLE));
        assertThat(box.isEnabled(), is(true));
        box.setText("hello world");
        ShadowView.clickOn(alertDialog.getButton(DialogInterface.BUTTON_POSITIVE));
        //alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
        assertThat(alertDialog.isShowing(), is(false));
        ShadowAlertDialog shadowAlertDialog = shadowOf(alertDialog);
        assertThat(shadowAlertDialog, is(notNullValue()));
        assertThat(shadowAlertDialog.getView().getVisibility(), is(View.VISIBLE));
        assertThat(shadowAlertDialog.hasBeenDismissed(), is(true));
    }
}
