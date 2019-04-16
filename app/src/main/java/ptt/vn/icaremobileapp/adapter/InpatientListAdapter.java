package ptt.vn.icaremobileapp.adapter;

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
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class InpatientListAdapter extends RecyclerView.Adapter<InpatientListAdapter.MyViewHolder> {

    private List<InpatientDomain> lists;

    public InpatientListAdapter(List<InpatientDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inpatient_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        InpatientDomain inpatientDomain = lists.get(position);
        if (inpatientDomain != null) {
            PatientDomain patientDomain = inpatientDomain.getPatient();

            if (patientDomain != null) {
                holder.tvName.setText(patientDomain.getPATIENTNAME());
                holder.tvBirthday.setValues(Utils.dateConvert(patientDomain.getBirthday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
                holder.tvGender.setValues(patientDomain.getGender());
            }

            holder.tvCode.setValues(inpatientDomain.getPatid());
            //holder.tvRoom.setValues(inpatientDomain.getPatid());
            holder.tvDate.setValues("");
            //holder.tvBed.setValues(inpatientDomain.getPatid());

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null)
                        onItemClick.onClick(holder.getAdapterPosition());
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
        private MyTextView tvRoom;
        private MyTextView tvDate;
        private MyTextView tvGender;
        private MyTextView tvBed;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tvName = itemView.findViewById(R.id.edtHappening);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvBirthday = itemView.findViewById(R.id.tvBirthday);
            tvRoom = itemView.findViewById(R.id.tvRoom);
            tvDate = itemView.findViewById(R.id.tvNumber);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvBed = itemView.findViewById(R.id.tvBed);

        }
    }

    public void setItems(List<InpatientDomain> lists) {
        this.lists = lists;
    }

    public interface OnItemClick {
        void onClick(int p);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
