package ptt.vn.icaremobileapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class HistoryClinicReasonAdapter extends RecyclerView.Adapter<HistoryClinicReasonAdapter.MyViewHolder> {
    private List<HistoryClinicDomain> lists;

    public HistoryClinicReasonAdapter(List<HistoryClinicDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_clinic_reason_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HistoryClinicDomain parent = lists.get(holder.getAdapterPosition());
        if (parent != null) {


            holder.tvDate.setValues((Utils.dateConvert(parent.getDateexa(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy)));
            holder.tvReason.setValues(parent.getReason());

        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private MyTextView tvDate, tvReason;


        MyViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvReason = itemView.findViewById(R.id.tvReason);
        }
    }
}
