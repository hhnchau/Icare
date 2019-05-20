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
import ptt.vn.icaremobileapp.dialog.DialogEditServiceItem;
import ptt.vn.icaremobileapp.model.filter.Objectez;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;
import ptt.vn.icaremobileapp.tooltip.MyTooltip;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.utils.Utils;

public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.MyViewHolder> {
    private List<Integer> expand = new ArrayList<>();
    private List<InpatientServiceOrder> lists;
    private Context context;

    public ServiceItemAdapter(List<InpatientServiceOrder> lists) {
        this.lists = lists;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serviceitem_item, parent, false);

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


        final InpatientServiceOrder serviceItem = lists.get(holder.getAdapterPosition());

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onDelete(holder.getAdapterPosition());
                }
            }
        });

        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    DialogEditServiceItem.getInstance().show(context, serviceItem, new DialogEditServiceItem.OnClickListener() {
                        @Override
                        public void onClickListener() {
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        });

        holder.icInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onIsHi(holder.getAdapterPosition());
                    showTooltip(serviceItem.getIshi(), holder.icInsurance);
                }
            }
        });

        holder.tvName.setText((serviceItem.getCode() + " - " + serviceItem.getNamehosp()));
        holder.tvDoctor.setText(serviceItem.getDocoder());
        holder.tvUnit.setValues(serviceItem.getNameunit());
        holder.tvPrice.setValues(Utils.formatCurrency(serviceItem.getPrice()));
        holder.tvPriceInsurance.setValues(Utils.formatCurrency(serviceItem.getPricehi()));
        holder.tvSurcharge.setValues(serviceItem.getDocoder());
        holder.tvNumber.setValues(String.valueOf(serviceItem.getQty()));
        holder.tvTotal.setValues(Utils.formatCurrency(serviceItem.getQty() * serviceItem.getPrice()));
        holder.tvNote.setValues(serviceItem.getDescrp());

        updateInsurance(serviceItem.getIshi(), holder.icInsurance);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private LinearLayout detailView;
        private ImageView icArrow;

        private TextView tvName, tvDoctor;
        private ImageView icEdit, icDelete, icInsurance;
        private MyTextView tvUnit, tvPrice, tvPriceInsurance, tvSurcharge, tvNumber, tvTotal, tvNote;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            detailView = itemView.findViewById(R.id.detailView);
            icArrow = itemView.findViewById(R.id.ic_expand);

            icEdit = itemView.findViewById(R.id.icEdit);
            icDelete = itemView.findViewById(R.id.icDelete);

            tvName = itemView.findViewById(R.id.tvName);
            tvDoctor = itemView.findViewById(R.id.tvDoctor);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPriceInsurance = itemView.findViewById(R.id.tvPriceInsurance);
            icInsurance = itemView.findViewById(R.id.icInsurance);
            tvSurcharge = itemView.findViewById(R.id.tvSurcharge);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvNote = itemView.findViewById(R.id.tvNote);
        }
    }

    public void setItems(List<InpatientServiceOrder> lists) {
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
        void onDelete(int p);

        void onEdit(int p);

        void onIsHi(int p);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
