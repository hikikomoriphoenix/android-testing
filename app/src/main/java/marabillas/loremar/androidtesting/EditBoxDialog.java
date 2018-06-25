package marabillas.loremar.androidtesting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import static marabillas.loremar.androidtesting.Utils.hideSoftKeyboard;

public abstract class EditBoxDialog {
    private EditText box;
    private AlertDialog alertDialog;

    EditBoxDialog(final Activity activity) {
        box = new EditText(activity);
        box.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        box.setImeOptions(EditorInfo.IME_ACTION_DONE);
        alertDialog = new AlertDialog.Builder(activity)
                .setView(box)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = box.getText().toString();
                        hideSoftKeyboard(activity, box.getWindowToken());
                        ok(text);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideSoftKeyboard(activity, box.getWindowToken());
                    }
                })
                .create();
        alertDialog.show();
        box.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = box.getText().toString();
                ok(text);
                alertDialog.dismiss();
                return false;
            }
        });
    }

    abstract void ok(String text);

    EditText getBox() {
        return box;
    }

    AlertDialog getAlertDialog() {
        return alertDialog;
    }
}
