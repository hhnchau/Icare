package ptt.vn.icaremobileapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.dialog.DialogPatientDetails;
import ptt.vn.icaremobileapp.model.patient.PatientAdrr;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {
    private Context context;
    private List<PatientDomain> lists;

    public PatientListAdapter(List<PatientDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_item, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final PatientDomain patientDomain = lists.get(position);
        if (patientDomain != null) {

            holder.tvName.setText(patientDomain.getPATIENTNAME());

            holder.tvCode.setValues(patientDomain.getHospcode());
            holder.tvBirthday.setValues(Utils.dateConvert(patientDomain.getBirthday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            if (patientDomain.getLstPatientIde() != null && patientDomain.getLstPatientIde().size() > 0)
                holder.tvIde.setValues(patientDomain.getLstPatientIde().get(0).getCardid());
            if (patientDomain.getLstPatientHi() != null && patientDomain.getLstPatientHi().size() > 0)
                holder.tvHi.setValues(patientDomain.getLstPatientHi().get(0).getNohi());

            holder.tvPhone.setValues(patientDomain.getPhone());
            holder.tvGender.setValues(patientDomain.getGender());
            holder.tvJob.setValues(patientDomain.getNamejob());
            if (patientDomain.getLstPatientBhi() != null && patientDomain.getLstPatientBhi().size() > 0)
                holder.tvBhi.setValues(patientDomain.getLstPatientBhi().get(0).getNobhi());

            if (patientDomain.getLstPatientAddr() != null && patientDomain.getLstPatientAddr().size() > 0)
                holder.tvAddr.setValues(patientDomain.getLstPatientAddr().get(0).getAddresfull());


            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null)
                        onItemClick.onClick(patientDomain);
                }
            });

            holder.tvDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogPatientDetails.getInstance().show(context, patientDomain, null);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView tvName;
        private MyTextView tvCode;
        private MyTextView tvBirthday;
        private MyTextView tvIde;
        private MyTextView tvHi;
        private MyTextView tvPhone;
        private MyTextView tvGender;
        private MyTextView tvJob;
        private MyTextView tvBhi;
        private MyTextView tvAddr;
        private TextView tvDetail;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tvName = itemView.findViewById(R.id.edtHappening);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvBirthday = itemView.findViewById(R.id.tvBirthday);
            tvIde = itemView.findViewById(R.id.tvIde);
            tvHi = itemView.findViewById(R.id.tvHi);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvJob = itemView.findViewById(R.id.tvJob);
            tvBhi = itemView.findViewById(R.id.tvBhi);
            tvAddr = itemView.findViewById(R.id.tvAddr);
            tvDetail = itemView.findViewById(R.id.tvDetail);

        }
    }

    public void setItems(List<PatientDomain> lists) {
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
