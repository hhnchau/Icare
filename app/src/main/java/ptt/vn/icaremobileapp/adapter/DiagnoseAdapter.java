package ptt.vn.icaremobileapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;

public class DiagnoseAdapter extends RecyclerView.Adapter<DiagnoseAdapter.MyViewHolder> {
    private List<Integer> expand = new ArrayList<>();
    private List<String> lists;

    public DiagnoseAdapter(List<String> lists) {
        this.lists = lists;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diagnose_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //Set Expand
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation arrowAnimation;
                if (expand.get(holder.getAdapterPosition()) % 2 == 0) {
                    holder.detailView.setVisibility(View.VISIBLE);
                    arrowAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                } else {
                    holder.detailView.setVisibility(View.GONE);
                    arrowAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                }
                expand.set(holder.getAdapterPosition(), expand.get(holder.getAdapterPosition()) + 1);
                arrowAnimation.setFillAfter(true);
                arrowAnimation.setDuration((long) 100);
                holder.icArrow.startAnimation(arrowAnimation);
            }
        });


//        holder.name.setText(lists.get(position));
//        holder.age.setText(lists.get(position) + position);
//
//        holder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClick != null)
//                    onItemClick.onClick(holder.getAdapterPosition());
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private LinearLayout detailView;
        private ImageView icArrow;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            detailView = itemView.findViewById(R.id.detailView);
            icArrow = itemView.findViewById(R.id.ic_expand);

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
