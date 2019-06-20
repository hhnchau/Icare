package ptt.vn.icaremobileapp.dynamicview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.HistoryClinicDrugAdapter;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutDrugOrderHeaderDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class DrugViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView rcv;

    public DrugViewHolder(@NonNull View itemView) {
        super(itemView);
        rcv = itemView.findViewById(R.id.rcvParent);
    }

    public void onBind(Context context, HistoryClinicDomain historyClinicDomain) {
        List<OutDrugOrderHeaderDomain> lstParent = new ArrayList<>();

        OutDrugOrderHeaderDomain outDrugOrderHeaderDomain = new OutDrugOrderHeaderDomain();
        outDrugOrderHeaderDomain.setTitle(context.getString(R.string.txt_dialog_drugorder));
        outDrugOrderHeaderDomain.setLstChild(historyClinicDomain.getLstdrugorder());

        lstParent.add(outDrugOrderHeaderDomain);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicDrugAdapter adapter = new HistoryClinicDrugAdapter(lstParent);
        rcv.setAdapter(adapter);
    }

    public void onBind(Context context, List<HistoryClinicDomain> lstHistoryClinicDomain) {
        List<OutDrugOrderHeaderDomain> lstParent = new ArrayList<>();

        for (HistoryClinicDomain item: lstHistoryClinicDomain){
            OutDrugOrderHeaderDomain outDrugOrderHeaderDomain = new OutDrugOrderHeaderDomain();
            outDrugOrderHeaderDomain.setTitle(Utils.dateConvert(item.getDateexa(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            outDrugOrderHeaderDomain.setLstChild(item.getLstdrugorder());

            lstParent.add(outDrugOrderHeaderDomain);
        }

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicDrugAdapter adapter = new HistoryClinicDrugAdapter(lstParent);
        rcv.setAdapter(adapter);
    }

}
