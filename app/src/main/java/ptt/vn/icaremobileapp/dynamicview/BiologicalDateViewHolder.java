package ptt.vn.icaremobileapp.dynamicview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;

public class BiologicalDateViewHolder extends RecyclerView.ViewHolder {
    private MyTextView tvCircuit, tvBloodMax, tvBloodMin, tvTemperature, tvHeartbeat, tvWeight;

    public BiologicalDateViewHolder(@NonNull View itemView) {
        super(itemView);

        tvCircuit = itemView.findViewById(R.id.tvCircuits);
        tvBloodMax = itemView.findViewById(R.id.tvBloodMax);
        tvBloodMin = itemView.findViewById(R.id.tvBloodMin);
        tvTemperature = itemView.findViewById(R.id.tvTemperature);
        tvHeartbeat = itemView.findViewById(R.id.tvHeartbeat);
        tvWeight = itemView.findViewById(R.id.tvWeights);
    }

    public void onBind(HistoryClinicDomain historyClinicDomain) {

        tvCircuit.setValues(historyClinicDomain.getCircui());
        tvBloodMax.setValues(String.valueOf(historyClinicDomain.getBlomax()));
        tvBloodMin.setValues(String.valueOf(historyClinicDomain.getBlomin()));
        tvTemperature.setValues(String.valueOf(historyClinicDomain.getTemper()));
        tvHeartbeat.setValues(String.valueOf(historyClinicDomain.getHeartb()));
        tvWeight.setValues(String.valueOf(historyClinicDomain.getWeight()));

    }
}
