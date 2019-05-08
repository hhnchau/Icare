package ptt.vn.icaremobileapp.adapter;

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
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.dialog.DialogEditDrugOrder;
import ptt.vn.icaremobileapp.model.filter.Objectez;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;
import ptt.vn.icaremobileapp.tooltip.MyTooltip;
import ptt.vn.icaremobileapp.utils.Constant;

public class DrugOrderAdapter extends RecyclerView.Adapter<DrugOrderAdapter.MyViewHolder> {
    private List<Integer> expand = new ArrayList<>();
    private List<InpatientDrugOrder> lists;
    private Context context;

    public DrugOrderAdapter(List<InpatientDrugOrder> lists) {
        this.lists = lists;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drugorder_item, parent, false);

        this.context = parent.getContext();

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
                arrowAnimation.setDuration((long) 300);
                holder.icArrow.startAnimation(arrowAnimation);
            }
        });


        final InpatientDrugOrder inpatientDrugOrder = lists.get(position);


        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditDrugOrder.getInstance().show(context, null, new DialogEditDrugOrder.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        inpatientDrugOrder.setTotal(10);
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) onItemClick.onDelete(holder.getAdapterPosition());
            }
        });

        holder.icInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onIsHi(holder.getAdapterPosition());
                    showTooltip(inpatientDrugOrder.getInsurance(), holder.icInsurance);
                }
            }
        });

        holder.tvName.setText(inpatientDrugOrder.getCodedrug() + " - " + inpatientDrugOrder.getNamedrug());
        holder.tvNumber.setText(inpatientDrugOrder.getQty() + "");
        holder.tvDrugActiveingre.setValues(inpatientDrugOrder.getActivename());
        holder.tvUnit.setValues(inpatientDrugOrder.getUnitusename());
        holder.tvPrice.setValues(inpatientDrugOrder.getPrice() + "");
        holder.tvTotal.setValues(inpatientDrugOrder.getTotal() + "");
        holder.tvDrugUse.setValues(inpatientDrugOrder.getDesc());
        updateInsurance(inpatientDrugOrder.getInsurance(), holder.icInsurance);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private LinearLayout detailView;
        private ImageView icArrow;

        private TextView tvName, tvNumber;
        private MyTextView tvUnit, tvPrice, tvTotal, tvDrugActiveingre, tvDrugUse;
        private ImageView icEdit, icDelete, icInsurance;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            detailView = itemView.findViewById(R.id.detailView);
            icArrow = itemView.findViewById(R.id.ic_expand);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            icInsurance = itemView.findViewById(R.id.icInsurance);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotal = itemView.findViewById(R.id.tvTotal);
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

    private void updateInsurance(int isHi, ImageView img) {
        if (isHi == Objectez.BHYT.ordinal())
            img.setImageResource(R.mipmap.ic_insurance_red);
        else img.setImageResource(R.mipmap.ic_insurance_gray);
    }

    private void showTooltip(int primary, ImageView img) {
        if (primary == Constant.DEACTIVE)
            MyTooltip.on(img).autoHide(true, 1000).position(MyTooltip.Position.LEFT).text(context.getString(R.string.txt_drugorder_service)).show();
        else
            MyTooltip.on(img).autoHide(true, 1000).position(MyTooltip.Position.LEFT).text(context.getString(R.string.txt_drugorder_insurance)).show();
    }

    public interface OnItemClick {
        void onIsHi(int p);

        void onEdit(InpatientDrugOrder inpatientDrugOrder);

        void onDelete(int p);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
