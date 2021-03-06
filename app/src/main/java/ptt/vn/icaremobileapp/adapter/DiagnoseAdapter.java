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
import ptt.vn.icaremobileapp.dialog.DialogEditDiagnose;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDiagnose;
import ptt.vn.icaremobileapp.tooltip.MyTooltip;
import ptt.vn.icaremobileapp.utils.Constant;

public class DiagnoseAdapter extends RecyclerView.Adapter<DiagnoseAdapter.MyViewHolder> {
    private List<Integer> expand = new ArrayList<>();
    private List<InpatientDiagnose> lists;
    private Context context;

    public DiagnoseAdapter(List<InpatientDiagnose> lists) {
        this.lists = lists;

        for (int i = 0; i < lists.size(); i++) {
            expand.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diagnose_item, parent, false);

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

        final InpatientDiagnose diagnose = lists.get(position);


        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditDiagnose.getInstance().show(context, diagnose, new DialogEditDiagnose.OnClickListener() {
                    @Override
                    public void onClickListener() {
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onClick(holder.getAdapterPosition());
            }
        });

        holder.icSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagnose.setPrimary(diagnose.getPrimary() == Constant.ACTIVE ? Constant.DEACTIVE : Constant.ACTIVE);
                updateSelected(diagnose, holder.icSelect);
                showTooltip(diagnose.getPrimary(), holder.icSelect);
            }
        });


        holder.tvIcdVn.setText((diagnose.getCode() + " - " + diagnose.getNameicdvn()));
        holder.tvIcdCode.setText(diagnose.getCode());
        holder.tvIcdEn.setValues(diagnose.getNameicdeng());
        updateSelected(diagnose, holder.icSelect);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private LinearLayout detailView;
        private ImageView icArrow;

        private TextView tvIcdVn, tvIcdCode;
        private ImageView icEdit, icDelete, icSelect;
        private MyTextView tvIcdEn;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            detailView = itemView.findViewById(R.id.detailView);
            icArrow = itemView.findViewById(R.id.ic_expand);

            tvIcdVn = itemView.findViewById(R.id.tvName);
            icEdit = itemView.findViewById(R.id.icEdit);
            icDelete = itemView.findViewById(R.id.icDelete);
            icSelect = itemView.findViewById(R.id.icSelect);
            tvIcdCode = itemView.findViewById(R.id.tvIcdCode);
            tvIcdEn = itemView.findViewById(R.id.tvIcdEn);
        }
    }

    public void setItems(List<InpatientDiagnose> lists) {
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
        void onClick(int p);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private void updateSelected(InpatientDiagnose diagnose, ImageView img) {
        if (diagnose.getPrimary() == Constant.DEACTIVE) img.setImageResource(R.mipmap.ic_uncheck);
        else img.setImageResource(R.mipmap.ic_checked);
    }

    private void showTooltip(int primary, ImageView img) {
        if (primary == Constant.DEACTIVE)
            MyTooltip.on(img).autoHide(true, 1000).position(MyTooltip.Position.LEFT).text(context.getString(R.string.txt_diagnose_unprimary)).show();
        else
            MyTooltip.on(img).autoHide(true, 1000).position(MyTooltip.Position.LEFT).text(context.getString(R.string.txt_diagnose_primary)).show();
    }

}
