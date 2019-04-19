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
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;

public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.MyViewHolder> {
    private List<Integer> expand = new ArrayList<>();
    private List<ServiceItemDomain> lists;

    public ServiceItemAdapter(List<ServiceItemDomain> lists) {
        this.lists = lists;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.serviceitem_item, parent, false);
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

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onClick(holder.getAdapterPosition(), lists.get(holder.getAdapterPosition()).getId());
                }
            }
        });

        holder.tvName.setText(lists.get(position).getNamehosp());
        holder.tvDate.setText(lists.get(position).getDateapp());
        holder.tvItemCode.setValues(lists.get(position).getCode());
        holder.tvUnit.setValues(lists.get(position).getNameunit());
        holder.tvNumber.setValues(lists.get(position).getQty() + "");
        holder.tvDoctor.setText(lists.get(position).getDocoder());
        holder.tvPrice.setValues(lists.get(position).getPrice() + "");
        holder.tvPriceInsurance.setValues(lists.get(position).getPricehi() + "");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private LinearLayout detailView;
        private ImageView icArrow;

        private TextView tvName, tvDate, tvDoctor;
        private ImageView icDelete;
        private MyTextView tvItemCode, tvUnit, tvNumber, tvPrice, tvPriceInsurance;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            detailView = itemView.findViewById(R.id.detailView);
            icArrow = itemView.findViewById(R.id.ic_expand);

            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            icDelete = itemView.findViewById(R.id.icDelete);
            tvItemCode = itemView.findViewById(R.id.tvItemCode);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvDoctor = itemView.findViewById(R.id.tvDoctor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPriceInsurance = itemView.findViewById(R.id.tvPriceInsurance);
        }
    }

    public void setItems(List<ServiceItemDomain> lists) {
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
        void onClick(int p, int idService);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
