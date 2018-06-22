package marabillas.loremar.androidtesting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        RecyclerView recyclerView = findViewById(R.id.activity_recycler_view);
        String[] items = new String[]{"dog", "cat", "rat", "horse"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, items);
        recyclerView.setAdapter(new ARecyclerViewAdapter(this, list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void processInvolvingSomeComplexClass(SomeComplexClass someComplexClass) {
        aValue = someComplexClass.methodA();
        bValue = someComplexClass.methodB();
    }
}
