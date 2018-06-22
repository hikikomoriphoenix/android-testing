package marabillas.loremar.androidtesting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ARecyclerViewAdapter extends RecyclerView.Adapter<ARecyclerViewAdapter.AViewHolder> {
    private Context context;
    private List<String> list;

    public ARecyclerViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new AViewHolder(inflater.inflate(R.layout.a_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        AViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.a_list_item_textview);
        }

        void bind(String item) {
            textView.setText(item);
        }

        TextView gettextView() {
            return textView;
        }
    }
}
