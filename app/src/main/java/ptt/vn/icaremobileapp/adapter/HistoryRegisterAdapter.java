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

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.history.HistoryRegisterDomain;
import ptt.vn.icaremobileapp.model.history.HistoryRegisterServiceOrderDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class HistoryRegisterAdapter extends RecyclerView.Adapter<HistoryRegisterAdapter.MyViewHolder> {
    private List<HistoryRegisterDomain> lists;
    private Context context;

    public HistoryRegisterAdapter(List<HistoryRegisterDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_register_item, parent, false);
        this.context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HistoryRegisterDomain parent = lists.get(holder.getAdapterPosition());
        if (parent != null) {

            if (parent.hasChild()) holder.icArrow.setVisibility(View.VISIBLE);
            else holder.icArrow.setVisibility(View.INVISIBLE);

            holder.tvRegisterDate.setValues(Utils.dateConvert(parent.getRegdate(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            holder.tvRegisterCode.setValues(parent.getHosnum());
            holder.tvRegisterObject.setValues(parent.getName());
            holder.tvRegisterDiscount.setValues(parent.getNamediscount());

            final List<HistoryRegisterServiceOrderDomain> lstChild = new ArrayList<>();
            holder.rcvChild.setHasFixedSize(true);
            holder.rcvChild.setLayoutManager(new LinearLayoutManager(context));
            final HistoryRegisterChildAdapter adapter = new HistoryRegisterChildAdapter(lstChild);
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
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        final HistoryRegisterDomain parent = lists.get(holder.getAdapterPosition());
        parent.setExpandable(false);
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private RecyclerView rcvChild;
        private ImageView icArrow;

        private MyTextView tvRegisterDate, tvRegisterCode, tvRegisterObject, tvRegisterDiscount;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            rcvChild = itemView.findViewById(R.id.rcvChild);
            icArrow = itemView.findViewById(R.id.ic_expand);
            tvRegisterDate = itemView.findViewById(R.id.tvRegisterDate);
            tvRegisterCode = itemView.findViewById(R.id.tvRegisterCode);
            tvRegisterObject = itemView.findViewById(R.id.tvRegisterObject);
            tvRegisterDiscount = itemView.findViewById(R.id.tvRegisterDiscount);
        }
    }

    public void setItems(List<HistoryRegisterDomain> lists) {
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
