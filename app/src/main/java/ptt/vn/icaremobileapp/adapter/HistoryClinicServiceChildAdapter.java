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
import ptt.vn.icaremobileapp.model.outclinic.OutDiagnoseDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutServiceOrderDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;

public class HistoryClinicServiceChildAdapter extends RecyclerView.Adapter<HistoryClinicServiceChildAdapter.MyViewHolder> {
    private List<OutServiceOrderDomain> lists;

    public HistoryClinicServiceChildAdapter(List<OutServiceOrderDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_clinic_dynamic_child_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final OutServiceOrderDomain child = lists.get(position);
        if (child != null) {

            holder.tvDetail.setText(child.getServiceName());

        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDetail;

        MyViewHolder(View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.child);

        }
    }

    public void setItems(List<OutServiceOrderDomain> lists) {
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
