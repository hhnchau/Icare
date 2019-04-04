package ptt.vn.icaremobileapp.togglebutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.enums.Fragmentez;

public class MyTabButton extends CardView implements View.OnClickListener {


    public MyTabButton(@NonNull Context context) {
        super(context);
        create(context);
    }

    public MyTabButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        create(context);
    }

    public MyTabButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create(context);
    }


    private void create(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.my_tabbutton, this);
            findViewById(R.id.btn1).setOnClickListener(this);
            findViewById(R.id.btn2).setOnClickListener(this);
            findViewById(R.id.btn3).setOnClickListener(this);
            findViewById(R.id.btn4).setOnClickListener(this);
            findViewById(R.id.btn5).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                if (listener != null) listener.onToggled(Fragmentez.THAM_KHAM);
                break;
            case R.id.btn2:
                if (listener != null) listener.onToggled(Fragmentez.SERVICE_ITEM);
                break;
            case R.id.btn3:
                if (listener != null) listener.onToggled(Fragmentez.DRUG_ORDER);
                break;
            case R.id.btn4:
                if (listener != null) listener.onToggled(Fragmentez.DRUG_ORDER_OUTSIDE);
                break;
            case R.id.btn5:
                if (listener != null) listener.onToggled(Fragmentez.DIAGNOSE);
                break;
        }
    }

    private OnToggledListener listener;

    public void setOnToggleSelectedListener(OnToggledListener listener) {
        this.listener = listener;
    }

    public interface OnToggledListener {
        void onToggled(Fragmentez fzg);
    }
}
