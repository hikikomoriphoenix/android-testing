package marabillas.loremar.androidtesting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.EditText;

public abstract class EditBoxDialog {
    private EditText box;
    private AlertDialog alertDialog;

    EditBoxDialog(Context context) {
        box = new EditText(context);
        box.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        alertDialog = new AlertDialog.Builder(context)
                .setView(box)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = box.getText().toString();
                        ok(text);
                    }
                })
                .create();
        alertDialog.show();
    }

    abstract void ok(String text);

    EditText getBox() {
        return box;
    }

    AlertDialog getAlertDialog() {
        return alertDialog;
    }
}
