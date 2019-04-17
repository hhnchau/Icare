package ptt.vn.icaremobileapp.togglebutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;


import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.enums.Fragmentez;

public class MyTabButton extends CardView implements View.OnClickListener {
    public static final int TAB1 = 1;
    public static final int TAB2 = 2;
    public static final int TAB3 = 3;
    public static final int TAB4 = 4;
    public static final int TAB5 = 5;
    private Fragmentez currentTab;
    private RadioButton radio1, radio2, radio3, radio4, radio5;


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
            radio1 = findViewById(R.id.btn1);
            radio1.setOnClickListener(this);
            radio2 = findViewById(R.id.btn2);
            radio2.setOnClickListener(this);
            radio3 = findViewById(R.id.btn3);
            radio3.setOnClickListener(this);
            radio4 = findViewById(R.id.btn4);
            radio4.setOnClickListener(this);
            radio5 = findViewById(R.id.btn5);
            radio5.setOnClickListener(this);
        }
    }

    public void setActive(int p) {
        if (p == TAB1) radio1.setChecked(true);
        else if (p == TAB2) radio2.setChecked(true);
        else if (p == TAB3) radio3.setChecked(true);
        else if (p == TAB4) radio4.setChecked(true);
        else if (p == TAB5) radio5.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                if (listener != null && currentTab != Fragmentez.THAM_KHAM) {
                    currentTab = Fragmentez.THAM_KHAM;
                    listener.onToggled(Fragmentez.THAM_KHAM);
                }
                break;
            case R.id.btn2:
                if (listener != null && currentTab != Fragmentez.THAM_KHAM) {
                    currentTab = Fragmentez.SERVICE_ITEM;
                    listener.onToggled(Fragmentez.SERVICE_ITEM);
                }
                break;
            case R.id.btn3:
                if (listener != null && currentTab != Fragmentez.THAM_KHAM) {
                    currentTab = Fragmentez.DRUG_ORDER;
                    listener.onToggled(Fragmentez.DRUG_ORDER);
                }
                break;
            case R.id.btn4:
                if (listener != null && currentTab != Fragmentez.THAM_KHAM) {
                    currentTab = Fragmentez.DRUG_ORDER_OUTSIDE;
                    listener.onToggled(Fragmentez.DRUG_ORDER_OUTSIDE);
                }
                break;
            case R.id.btn5:
                if (listener != null && currentTab != Fragmentez.THAM_KHAM) {
                    currentTab = Fragmentez.DIAGNOSE;
                    listener.onToggled(Fragmentez.DIAGNOSE);
                }
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
