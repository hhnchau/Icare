package ptt.vn.icaremobileapp.dynamicview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.HistoryClinicReasonAdapter;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;

public class ReasonViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView rcv;

    public ReasonViewHolder(@NonNull View itemView) {
        super(itemView);
        rcv = itemView.findViewById(R.id.rcvParent);
    }

    public void onBind(Context context, List<HistoryClinicDomain> lstHistoryClinicDomain) {
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicReasonAdapter adapter = new HistoryClinicReasonAdapter(lstHistoryClinicDomain);
        rcv.setAdapter(adapter);
    }
}
