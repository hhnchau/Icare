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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class HappeningAdapter extends RecyclerView.Adapter<HappeningAdapter.MyViewHolder> {
    private List<HappeningDomain> lists;
    private List<Integer> expand = new ArrayList<>();

    public HappeningAdapter(List<HappeningDomain> lists) {
        this.lists = lists;

        for (int i = 0; i < this.lists.size(); i++) {
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
        holder.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) onItemClick.onItem(lists.get(holder.getAdapterPosition()));
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


        holder.icCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HappeningDomain happening = null;
                try {
                    happening = (HappeningDomain) lists.get(holder.getAdapterPosition()).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                if (onItemClick != null && happening != null) onItemClick.onCopy(happening);
            }
        });

        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) onItemClick.onEdit(lists.get(holder.getAdapterPosition()));
            }
        });

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onDelete(lists.get(holder.getAdapterPosition()));
            }
        });


        holder.tvName.setText(lists.get(position).getHappening());
        holder.tvTotal.setText(Utils.dateConvert(lists.get(position).getDatecreate(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyyHHmm));
        holder.tvDoctor.setValues("");
        holder.tvCircui.setValues(lists.get(position).getCircui() + "");
        holder.tvBlood.setValues(lists.get(position).getBlomax() + lists.get(position).getBlomin() + "");
        holder.tvTemper.setValues(lists.get(position).getTemper() + "");
        holder.tvHeartb.setValues(lists.get(position).getHeartb() + "");
        holder.tvWeight.setValues(lists.get(position).getWeight() + "");

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private ImageView btnNext;
        private TextView tvName, tvTotal;
        private LinearLayout detailView;
        private MyTextView tvDoctor, tvCircui, tvBlood, tvTemper, tvHeartb, tvWeight;
        private ImageView icCopy, icEdit, icDelete;


        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            btnNext = itemView.findViewById(R.id.btnNext);
            tvName = itemView.findViewById(R.id.tvName);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            detailView = itemView.findViewById(R.id.detailView);
            tvDoctor = itemView.findViewById(R.id.tvDoctor);
            tvCircui = itemView.findViewById(R.id.tvCircuit);
            tvBlood = itemView.findViewById(R.id.edtBlood);
            tvTemper = itemView.findViewById(R.id.tvTemper);
            tvHeartb = itemView.findViewById(R.id.tvHeartb);
            tvWeight = itemView.findViewById(R.id.edtWeight);
            icCopy = itemView.findViewById(R.id.icCopy);
            icEdit = itemView.findViewById(R.id.icEdit);
            icDelete = itemView.findViewById(R.id.icDelete);
        }
    }

    public void setItems(List<HappeningDomain> lists) {
        this.lists = lists;
        for (int i = 0; i < this.lists.size(); i++) {
            expand.add(0);
        }
    }

    public void removeItem(int position) {
        this.lists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.lists.size());
    }

    public interface OnItemClick {
        void onItem(HappeningDomain happening);

        void onCopy(HappeningDomain happening);

        void onEdit(HappeningDomain happening);

        void onDelete(HappeningDomain happening);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
