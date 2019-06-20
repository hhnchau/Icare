package ptt.vn.icaremobileapp.dynamicview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.HistoryClinicDiagnoseAdapter;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutDiagnoseHeaderDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class DiagnoseViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView rcv;

    public DiagnoseViewHolder(@NonNull View itemView) {
        super(itemView);
        rcv = itemView.findViewById(R.id.rcvParent);
    }

    public void onBind(Context context, HistoryClinicDomain historyClinicDomain) {
        List<OutDiagnoseHeaderDomain> lstParent = new ArrayList<>();

        OutDiagnoseHeaderDomain outDiagnoseHeaderDomain = new OutDiagnoseHeaderDomain();
        outDiagnoseHeaderDomain.setTitle(context.getString(R.string.txt_dialog_diagnose));
        outDiagnoseHeaderDomain.setLstChild(historyClinicDomain.getLstdiagnose());

        lstParent.add(outDiagnoseHeaderDomain);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicDiagnoseAdapter adapter = new HistoryClinicDiagnoseAdapter(lstParent);
        rcv.setAdapter(adapter);

    }

    public void onBind(Context context, List<HistoryClinicDomain> lstHistoryClinicDomain) {
        List<OutDiagnoseHeaderDomain> lstParent = new ArrayList<>();

        for (HistoryClinicDomain item : lstHistoryClinicDomain) {
            OutDiagnoseHeaderDomain outDiagnoseHeaderDomain = new OutDiagnoseHeaderDomain();
            outDiagnoseHeaderDomain.setTitle(Utils.dateConvert(item.getDateexa(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            outDiagnoseHeaderDomain.setLstChild(item.getLstdiagnose());

            lstParent.add(outDiagnoseHeaderDomain);
        }

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(context));
        HistoryClinicDiagnoseAdapter adapter = new HistoryClinicDiagnoseAdapter(lstParent);
        rcv.setAdapter(adapter);

    }
}
