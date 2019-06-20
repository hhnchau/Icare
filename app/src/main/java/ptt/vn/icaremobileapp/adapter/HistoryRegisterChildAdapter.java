package ptt.vn.icaremobileapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.history.HistoryRegisterServiceOrderDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class HistoryRegisterChildAdapter extends RecyclerView.Adapter<HistoryRegisterChildAdapter.MyViewHolder> {
    private Context context;
    private List<HistoryRegisterServiceOrderDomain> lists;

    public HistoryRegisterChildAdapter(List<HistoryRegisterServiceOrderDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_register_child_item, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HistoryRegisterServiceOrderDomain child = lists.get(position);
        if (child != null) {

            holder.tvServiceCode.setValues(String.valueOf(child.getIditem()));
            holder.tvServiceName.setValues(child.getNameitem());
            holder.tvUnit.setValues(child.getNameunit());
            holder.tvNumber.setValues(String.valueOf(child.getQty()));
            holder.tvTotal.setValues(Utils.formatCurrency(child.getAmount()));

        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private MyTextView tvServiceCode, tvServiceName, tvUnit, tvNumber, tvTotal;

        MyViewHolder(View itemView) {
            super(itemView);
            tvServiceCode = itemView.findViewById(R.id.tvServiceCode);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvTotal = itemView.findViewById(R.id.tvTotal);

        }
    }

    public void setItems(List<HistoryRegisterServiceOrderDomain> lists) {
        this.lists = lists;
    }

    public interface OnItemClick {
        void onClick(PatientDomain patientDomain);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
