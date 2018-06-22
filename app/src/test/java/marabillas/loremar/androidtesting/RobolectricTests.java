package marabillas.loremar.androidtesting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        activity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
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

    @Test
    public void testAFragment() {
        AFragment fragment = Robolectric.buildFragment(AFragment.class).create().get();
        assertThat(fragment.getView(), is(notNullValue()));
        TextView textView = fragment.getView().findViewById(R.id.a_fragment_text1);
        assertThat(textView.getText().toString(), is("Hello World 2018"));
        //ShadowView view = shadowOf(fragment.getView());
    }

    private void testRecyclerView(RecyclerView recyclerView) {
        assertThat(recyclerView.getAdapter(), is(notNullValue()));
        assertThat(recyclerView.getLayoutManager(), is(notNullValue()));
        assertThat(recyclerView.getAdapter().getItemCount(), is(4));
        assertThat(recyclerView.findViewHolderForAdapterPosition(0), is(notNullValue()));
        assertThat(((ARecyclerViewAdapter.AViewHolder) recyclerView
                .findViewHolderForAdapterPosition(0)).gettextView().getText().toString(), is
                ("dog"));
    }

    @Test
    public void testRecyclerViewFromActivity() {
        ShadowActivity shadowActivity = shadowOf(activity);
        RecyclerView recyclerView = (RecyclerView) shadowActivity.findViewById(R.id.activity_recycler_view);
        testRecyclerView(recyclerView);
    }

    @Test
    public void testRecyclerviewFromFragment() {
        AFragment fragment = Robolectric.buildFragment(AFragment.class).create().start().visible
                ().get();
        RecyclerView recyclerView = fragment.getView().findViewById(R.id.a_recycler_view);

        //finally got it to work with this fix for RecyclerView issue with Robolectric
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 1000);

        testRecyclerView(recyclerView);
    }

    @Test
    public void testAnyRecyclerViewWithActivity() {
        RecyclerView recyclerView = new RecyclerView(activity);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        activity.setContentView(recyclerView);
        String[] items = new String[]{"dog", "cat", "rat", "horse"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, items);
        recyclerView.setAdapter(new ARecyclerViewAdapter(activity, list));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        testRecyclerView(recyclerView);
    }
}
