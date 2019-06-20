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

import ptt.vn.icaremobileapp.dynamicview.DynamicViewAdapter;
import ptt.vn.icaremobileapp.dynamicview.DynamicViewObject;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class HistoryClinicDateAdapter extends RecyclerView.Adapter<HistoryClinicDateAdapter.MyViewHolder> {
    private List<HistoryClinicDomain> lists;
    private Context context;

    public HistoryClinicDateAdapter(List<HistoryClinicDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_clinic_item, parent, false);
        this.context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HistoryClinicDomain parent = lists.get(holder.getAdapterPosition());
        if (parent != null) {

            holder.tvName.setText(parent.getReason());
            holder.tvDate.setText(Utils.dateConvert(parent.getDateexa(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));

            final List<DynamicViewObject> lstChild = new ArrayList<>();
            holder.rcvChild.setHasFixedSize(true);
            holder.rcvChild.setLayoutManager(new LinearLayoutManager(context));
            final DynamicViewAdapter dynamicViewAdapter = new DynamicViewAdapter(context, lstChild);
            holder.rcvChild.setAdapter(dynamicViewAdapter);


            //Set Expand
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    RotateAnimation arrowAnimation;
                    if (!parent.isExpandable()) {
                        lstChild.addAll(getChildData(parent));
                        dynamicViewAdapter.notifyDataSetChanged();
                        arrowAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    } else {
                        lstChild.clear();
                        dynamicViewAdapter.notifyDataSetChanged();
                        arrowAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    }
                    parent.setExpandable(!parent.isExpandable());
                    arrowAnimation.setFillAfter(true);
                    arrowAnimation.setDuration((long) 300);
                    holder.icArrow.startAnimation(arrowAnimation);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        HistoryClinicDomain parent = lists.get(holder.getAdapterPosition());
        parent.setExpandable(false);
        super.onViewDetachedFromWindow(holder);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private RecyclerView rcvChild;
        private ImageView icArrow;

        private TextView tvName, tvDate;


        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            rcvChild = itemView.findViewById(R.id.rcvChild);
            icArrow = itemView.findViewById(R.id.ic_expand);


            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    public void setItems(List<HistoryClinicDomain> lists) {
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

    private List<DynamicViewObject> getChildData(HistoryClinicDomain parent) {
        DynamicViewObject<HistoryClinicDomain> biological = new DynamicViewObject<>();
        biological.setType(DynamicViewObject.BIOLOGICAL_DATE_TYPE);
        biological.setData(parent);

        DynamicViewObject<HistoryClinicDomain> outDiagnose = new DynamicViewObject<>();
        outDiagnose.setType(DynamicViewObject.DIAGNOSE_DATE_TYPE);
        outDiagnose.setData(parent);

        DynamicViewObject<HistoryClinicDomain> outDrugOrder = new DynamicViewObject<>();
        outDrugOrder.setType(DynamicViewObject.DRUG_DATE_TYPE);
        outDrugOrder.setData(parent);

        DynamicViewObject<HistoryClinicDomain> outServiceOrder = new DynamicViewObject<>();
        outServiceOrder.setType(DynamicViewObject.SERVICE_DATE_TYPE);
        outServiceOrder.setData(parent);

        List<DynamicViewObject> lstChild = new ArrayList<>();

        lstChild.add(biological);
        lstChild.add(outDiagnose);
        lstChild.add(outDrugOrder);
        lstChild.add(outServiceOrder);

        return lstChild;
    }

}
