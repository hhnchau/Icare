package ptt.vn.icaremobileapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ptt.vn.icaremobileapp.R;

public class InpatientListAdapter extends RecyclerView.Adapter<InpatientListAdapter.MyViewHolder> {

    private List<String> lists;

    public InpatientListAdapter(List<String> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inpatient_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.name.setText(lists.get(position));
//        holder.age.setText(lists.get(position) + position);
//
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        MyViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv);

        }
    }

    public interface OnItemClick {
        void onClick(int p);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
