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

public class HistoryClinicBiologicalAdapter extends RecyclerView.Adapter<HistoryClinicBiologicalAdapter.MyViewHolder> {
    private List<HistoryClinicDomain> lists;

    public HistoryClinicBiologicalAdapter(List<HistoryClinicDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_clinic_biological_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HistoryClinicDomain parent = lists.get(holder.getAdapterPosition());
        if (parent != null) {


            holder.tvDate.setValues((Utils.dateConvert(parent.getDateexa(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy)));
            holder.tvCircuits.setValues(parent.getCircui());
            holder.tvBloodMax.setValues(String.valueOf(parent.getBlomax()));
            holder.tvBloodMin.setValues(String.valueOf(parent.getBlomin()));
            holder.tvTemperature.setValues(String.valueOf(parent.getTemper()));
            holder.tvHeartbeat.setValues(String.valueOf(parent.getHeartb()));
            holder.tvWeights.setValues(String.valueOf(parent.getWeight()));

        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private MyTextView tvDate, tvCircuits, tvBloodMax, tvBloodMin, tvTemperature, tvHeartbeat, tvWeights;


        MyViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDate.setVisibility(View.VISIBLE);
            tvCircuits = itemView.findViewById(R.id.tvCircuits);
            tvBloodMax = itemView.findViewById(R.id.tvBloodMax);
            tvBloodMin = itemView.findViewById(R.id.tvBloodMin);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            tvHeartbeat = itemView.findViewById(R.id.tvHeartbeat);
            tvWeights = itemView.findViewById(R.id.tvWeights);
        }
    }
}
