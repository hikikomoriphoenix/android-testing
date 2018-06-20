package marabillas.loremar.androidtesting;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    boolean assert_true;
    String aValue;
    int bValue;

    MainActivity() {
        assert_true = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assert_true = true;
    }

    void processInvolvingSomeComplexClass(SomeComplexClass someComplexClass) {
        aValue = someComplexClass.methodA();
        bValue = someComplexClass.methodB();
    }
}
