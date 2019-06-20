package ptt.vn.icaremobileapp.dynamicview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.model.history.HistoryClinicHeaderDomain;


public class DynamicViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DynamicViewObject> lst;

    public DynamicViewAdapter(Context context, List<DynamicViewObject> lst) {
        this.context = context;
        this.lst = lst;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case DynamicViewObject.DIAGNOSE_DATE_TYPE:
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new DiagnoseViewHolder(view);
            case DynamicViewObject.DRUG_DATE_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new DrugViewHolder(view);
            case DynamicViewObject.SERVICE_DATE_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new ServiceViewHolder(view);
            case DynamicViewObject.BIOLOGICAL_DATE_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic_biological_item, viewGroup, false);
                return new BiologicalDateViewHolder(view);
            case DynamicViewObject.DRUG_GROUP_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new DrugViewHolder(view);
            case DynamicViewObject.SERVICE_GROUP_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new ServiceViewHolder(view);
            case DynamicViewObject.DIAGNOSE_GROUP_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new DiagnoseViewHolder(view);
            case DynamicViewObject.REASON_GROUP_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new ReasonViewHolder(view);
            case DynamicViewObject.BIOLOGICAL_GROUP_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_clinic, viewGroup, false);
                return new BiologicalGroupViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        final DynamicViewObject object = lst.get(i);
        if (object != null) {
            switch (object.getType()) {
                case DynamicViewObject.DIAGNOSE_DATE_TYPE:
                    ((DiagnoseViewHolder) holder).onBind(context, (HistoryClinicDomain) object.getData());
                    break;
                case DynamicViewObject.DRUG_DATE_TYPE:
                    ((DrugViewHolder) holder).onBind(context, ((HistoryClinicDomain) object.getData()));
                    break;
                case DynamicViewObject.SERVICE_DATE_TYPE:
                    ((ServiceViewHolder) holder).onBind(context, (HistoryClinicDomain) object.getData());
                    break;
                case DynamicViewObject.BIOLOGICAL_DATE_TYPE:
                    ((BiologicalDateViewHolder) holder).onBind((HistoryClinicDomain) object.getData());
                    break;
                case DynamicViewObject.DRUG_GROUP_TYPE:
                    ((DrugViewHolder) holder).onBind(context, ((HistoryClinicHeaderDomain) object.getData()).getLstChild());
                    break;
                case DynamicViewObject.SERVICE_GROUP_TYPE:
                    ((ServiceViewHolder) holder).onBind(context, ((HistoryClinicHeaderDomain) object.getData()).getLstChild());
                    break;
                case DynamicViewObject.DIAGNOSE_GROUP_TYPE:
                    ((DiagnoseViewHolder) holder).onBind(context, ((HistoryClinicHeaderDomain) object.getData()).getLstChild());
                    break;
                case DynamicViewObject.REASON_GROUP_TYPE:
                    ((ReasonViewHolder) holder).onBind(context, ((HistoryClinicHeaderDomain) object.getData()).getLstChild());
                    break;
                case DynamicViewObject.BIOLOGICAL_GROUP_TYPE:
                    ((BiologicalGroupViewHolder) holder).onBind(context, ((HistoryClinicHeaderDomain) object.getData()).getLstChild());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (lst.get(position).getType()) {
            case DynamicViewObject.DIAGNOSE_DATE_TYPE:
                return DynamicViewObject.DIAGNOSE_DATE_TYPE;
            case DynamicViewObject.DRUG_DATE_TYPE:
                return DynamicViewObject.DRUG_DATE_TYPE;
            case DynamicViewObject.SERVICE_DATE_TYPE:
                return DynamicViewObject.SERVICE_DATE_TYPE;
            case DynamicViewObject.BIOLOGICAL_DATE_TYPE:
                return DynamicViewObject.BIOLOGICAL_DATE_TYPE;
            case DynamicViewObject.DRUG_GROUP_TYPE:
                return DynamicViewObject.DRUG_GROUP_TYPE;
            case DynamicViewObject.SERVICE_GROUP_TYPE:
                return DynamicViewObject.SERVICE_GROUP_TYPE;
            case DynamicViewObject.DIAGNOSE_GROUP_TYPE:
                return DynamicViewObject.DIAGNOSE_GROUP_TYPE;
            case DynamicViewObject.REASON_GROUP_TYPE:
                return DynamicViewObject.REASON_GROUP_TYPE;
            case DynamicViewObject.BIOLOGICAL_GROUP_TYPE:
                return DynamicViewObject.BIOLOGICAL_GROUP_TYPE;
            default:
                return -1;
        }
    }
}
