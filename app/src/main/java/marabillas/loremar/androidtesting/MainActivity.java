package marabillas.loremar.androidtesting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    boolean assert_true;
    String aValue;
    int bValue;

    public MainActivity() {
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

        TextView textView = findViewById(R.id.activity_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(android.R.id.content, new
                        AFragment(), null).addToBackStack(null).commit();
            }
        });
    }

    void processInvolvingSomeComplexClass(SomeComplexClass someComplexClass) {
        aValue = someComplexClass.methodA();
        bValue = someComplexClass.methodB();
    }
}
