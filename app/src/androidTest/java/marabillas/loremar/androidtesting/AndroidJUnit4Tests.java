package marabillas.loremar.androidtesting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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

    @Test
    public void testRecyclerView() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id
                .activity_recycler_view);
        assertThat(recyclerView.getLayoutManager(), is(notNullValue()));
        assertThat(recyclerView.findViewHolderForAdapterPosition(3), is(notNullValue()));
        assertThat(((ARecyclerViewAdapter.AViewHolder) recyclerView
                .findViewHolderForAdapterPosition(3)).gettextView().getText().toString(), is
                ("horse"));
    }

    @Test
    public void testFragment() {
        final Activity activity = activityTestRule.getActivity();
        final AFragment fragment = new AFragment();
        activity.getFragmentManager().beginTransaction().add(android.R.id.content, fragment,
                null).commit();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //activity.getFragmentManager().executePendingTransactions();
                View view = fragment.getView();
                assertThat(view, is(notNullValue()));
                TextView textView = view.findViewById(R.id.a_fragment_text1);
                assertThat(textView.getText().toString(), is("Hello World 2018"));
            }
        });
    }

    @Test
    public void testEditBoxDialog() {
        final Activity activity = activityTestRule.getActivity();
        final Object syncToken = new Object();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                EditBoxDialog dialog = new EditBoxDialog(activity) {
                    @Override
                    void ok(String text) {
                        assertThat(text, is("hello world"));
                    }
                };
                final AlertDialog alertDialog = dialog.getAlertDialog();
                assertThat(alertDialog.isShowing(), is(true));
                dialog.getBox().setText("hello world");
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                // assertThat(alertDialog.isShowing(), is(false)); <--test fails
                // My guess is dismiss() in performClick() is wrapped inside a post() command, so
                // it is called after the next block of code which runs in the same thread. See
                // testPostOrder() for example.
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        assertThat(alertDialog.isShowing(), is(false));
                    }
                });
            }
        });
    }

    @Test
    public void testPostOrder() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.v(AndroidJUnit4Tests.class.toString(), "hello");
            }
        });
        Log.v(AndroidJUnit4Tests.class.toString(), "world");
        Log.v(AndroidJUnit4Tests.class.toString(), "world1");
        Log.v(AndroidJUnit4Tests.class.toString(), "world2");
        Log.v(AndroidJUnit4Tests.class.toString(), "world3");
        Log.v(AndroidJUnit4Tests.class.toString(), "world4");
    }
}
