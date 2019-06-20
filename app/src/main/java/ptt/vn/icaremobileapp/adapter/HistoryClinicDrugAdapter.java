package ptt.vn.icaremobileapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.model.outclinic.OutDrugOrderDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutDrugOrderHeaderDomain;


public class HistoryClinicDrugAdapter extends RecyclerView.Adapter<HistoryClinicDrugAdapter.MyViewHolder> {
    private List<OutDrugOrderHeaderDomain> lists;
    private Context context;

    public HistoryClinicDrugAdapter(List<OutDrugOrderHeaderDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_clinic_dynamic_item, parent, false);
        this.context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final OutDrugOrderHeaderDomain parent = lists.get(holder.getAdapterPosition());
        if (parent != null) {

            if (parent.hasChild()) holder.icArrow.setVisibility(View.VISIBLE);
            else holder.icArrow.setVisibility(View.INVISIBLE);

            holder.tvName.setText((parent.getTitle()));
            holder.tvCount.setText(("(" + parent.countChild() + ")"));

            final List<OutDrugOrderDomain> lstChild = new ArrayList<>();
            holder.rcvChild.setHasFixedSize(true);
            holder.rcvChild.setLayoutManager(new LinearLayoutManager(context));
            final HistoryClinicDrugChildAdapter adapter = new HistoryClinicDrugChildAdapter(lstChild);
            holder.rcvChild.setAdapter(adapter);

            //Set Expand
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (parent.hasChild()) {
                        RotateAnimation arrowAnimation;
                        if (!parent.isExpandable()) {
                            lstChild.addAll(parent.getLstChild());
                            adapter.notifyDataSetChanged();
                            arrowAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        } else {
                            lstChild.clear();
                            adapter.notifyDataSetChanged();
                            arrowAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        }
                        parent.setExpandable(!parent.isExpandable());
                        arrowAnimation.setFillAfter(true);
                        arrowAnimation.setDuration((long) 300);
                        holder.icArrow.startAnimation(arrowAnimation);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private RecyclerView rcvChild;
        private ImageView icArrow;

        private TextView tvName, tvCount;


        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            rcvChild = itemView.findViewById(R.id.rcvChild);
            icArrow = itemView.findViewById(R.id.ic_expand);

            tvName = itemView.findViewById(R.id.tvName);
            tvCount = itemView.findViewById(R.id.tvCount);

        }
    }

    public void setItems(List<OutDrugOrderHeaderDomain> lists) {
        this.lists = lists;
    }

    public void removeItem(int position) {
        this.lists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.lists.size());
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
