package ptt.vn.icaremobileapp.expandrecyclerview;


import android.content.Context;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.alert.Alert;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.MyViewHolder> {
    private List<HeaderModel> lists;
    private Context context;
    private List<Integer> expand = new ArrayList<>();

    public ExpandableAdapter(Context context, List<HeaderModel> lists) {
        this.lists = lists;
        this.context = context;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.heppening_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.title.setText(lists.get(position).getTitle());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert.getInstance().show(context, lists.get(holder.getAdapterPosition()).getTitle(), null, null, true, null);
            }
        });


        holder.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onHeaderClick(holder.getAdapterPosition());
            }
        });


        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RotateAnimation arrowAnimation;

                if (expand.get(holder.getAdapterPosition()) % 2 == 0) {
                    holder.detailView.setVisibility(View.VISIBLE);
                    arrowAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                } else {
                    holder.detailView.setVisibility(View.GONE);
                    arrowAnimation = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                }
                expand.set(holder.getAdapterPosition(), expand.get(holder.getAdapterPosition()) + 1);


                arrowAnimation.setFillAfter(true);
                arrowAnimation.setDuration((long) 100);
                holder.btnNext.startAnimation(arrowAnimation);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private ImageView btnNext;
        private TextView title;
        private LinearLayout detailView;


        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            btnNext = itemView.findViewById(R.id.btnNext);
            title = itemView.findViewById(R.id.tvTitle);
            detailView = itemView.findViewById(R.id.detailView);
        }
    }

    public interface OnItemClick {
        void onHeaderClick(int p);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
