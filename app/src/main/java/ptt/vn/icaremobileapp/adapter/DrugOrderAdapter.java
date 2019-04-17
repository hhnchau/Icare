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
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;

public class DrugOrderAdapter extends RecyclerView.Adapter<DrugOrderAdapter.MyViewHolder> {
    private List<Integer> expand = new ArrayList<>();
    private List<InpatientDrugOrder> lists;

    public DrugOrderAdapter(List<InpatientDrugOrder> lists) {
        this.lists = lists;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drugorder_item, parent, false);
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

        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.tvName.setText("Tên Thuốc");
        holder.tvNumber.setText(10+"");
        holder.tvDrugCode.setText("AC1243123");
        holder.tvDrugActiveingre.setValues("Tên Hoạt Chất");
        holder.tvDrugUse.setValues("Sáng - Trưa - Chiều - Tối");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private LinearLayout detailView;
        private ImageView icArrow;

        private TextView tvName, tvNumber, tvDrugCode;
        private MyTextView  tvDrugActiveingre, tvDrugUse;
        private ImageView icEdit, icDelete;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            detailView = itemView.findViewById(R.id.detailView);
            icArrow = itemView.findViewById(R.id.ic_expand);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvDrugCode = itemView.findViewById(R.id.tvDrugCode);
            tvDrugActiveingre = itemView.findViewById(R.id.tvDrugActiveingre);
            tvDrugUse = itemView.findViewById(R.id.tvDrugUse);
            icEdit = itemView.findViewById(R.id.icEdit);
            icDelete = itemView.findViewById(R.id.icDelete);
        }
    }

    public void setItems(List<InpatientDrugOrder> lists) {
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
        void onClick(InpatientDrugOrder inpatientDrugOrder);
        void onEdit(InpatientDrugOrder inpatientDrugOrder);
        void onDelete(InpatientDrugOrder inpatientDrugOrder);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
