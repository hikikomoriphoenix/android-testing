package marabillas.loremar.androidtesting;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_fragment, container, false);

        recyclerView = view.findViewById(R.id.a_recycler_view);
        String[] items = new String[]{"dog", "cat", "rat", "horse"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, items);
        recyclerView.setAdapter(new ARecyclerViewAdapter(getActivity(), list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
