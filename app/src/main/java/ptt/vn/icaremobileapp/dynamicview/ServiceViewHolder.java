package ptt.vn.icaremobileapp.dynamicview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.HistoryClinicServiceAdapter;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutServiceOrderHeaderDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView rcv;

    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        rcv = itemView.findViewById(R.id.rcvParent);
    }

    public void onBind(Context context, HistoryClinicDomain historyClinicDomain) {
        List<OutServiceOrderHeaderDomain> lstParent = new ArrayList<>();

        OutServiceOrderHeaderDomain outServiceOrderHeaderDomain = new OutServiceOrderHeaderDomain();
        outServiceOrderHeaderDomain.setTitle(context.getString(R.string.txt_dialog_serviceitem));
        outServiceOrderHeaderDomain.setLstChild(historyClinicDomain.getLstserviceorder());

        lstParent.add(outServiceOrderHeaderDomain);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicServiceAdapter adapter = new HistoryClinicServiceAdapter(lstParent);
        rcv.setAdapter(adapter);
    }

    public void onBind(Context context, List<HistoryClinicDomain> lstHistoryClinicDomain) {
        List<OutServiceOrderHeaderDomain> lstParent = new ArrayList<>();

        for (HistoryClinicDomain item : lstHistoryClinicDomain) {
            OutServiceOrderHeaderDomain outServiceOrderHeaderDomain = new OutServiceOrderHeaderDomain();
            outServiceOrderHeaderDomain.setTitle(Utils.dateConvert(item.getDateexa(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            outServiceOrderHeaderDomain.setLstChild(item.getLstserviceorder());

            lstParent.add(outServiceOrderHeaderDomain);
        }

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicServiceAdapter adapter = new HistoryClinicServiceAdapter(lstParent);
        rcv.setAdapter(adapter);
    }
}
