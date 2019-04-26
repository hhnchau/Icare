package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;


import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.model.filter.FieldName;

public class MyTabButton extends FrameLayout implements View.OnClickListener {
    public static final int TAB1 = 1;
    public static final int TAB2 = 2;
    public static final int TAB3 = 3;
    public static final int TAB4 = 4;
    public static final int TAB5 = 5;
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

    public void setContent(List<String> lst) {
        for (int i = 0; i < lst.size(); i++)
            if (i == 0 && lst.get(i) != null) {
                radio1.setText(lst.get(i));
                radio1.setVisibility(VISIBLE);
            } else if (i == 1 && lst.get(i) != null) {
                radio2.setText(lst.get(i));
                radio2.setVisibility(VISIBLE);
            } else if (i == 2 && lst.get(i) != null) {
                radio3.setText(lst.get(i));
                radio3.setVisibility(VISIBLE);
            } else if (i == 3 && lst.get(i) != null) {
                radio4.setText(lst.get(i));
                radio4.setVisibility(VISIBLE);
            } else if (i == 4 && lst.get(i) != null) {
                radio5.setText(lst.get(i));
                radio5.setVisibility(VISIBLE);
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
                if (listener != null) {
                    listener.onTab(TAB1);
                }
                break;
            case R.id.btn2:
                if (listener != null) {
                    listener.onTab(TAB2);
                }
                break;
            case R.id.btn3:
                if (listener != null) {
                    listener.onTab(TAB3);
                }
                break;
            case R.id.btn4:
                if (listener != null) {
                    listener.onTab(TAB4);
                }
                break;
            case R.id.btn5:
                if (listener != null) {
                    listener.onTab(TAB5);
                }
                break;
        }
    }

    private OnToggledListener listener;

    public void setOnToggleSelectedListener(OnToggledListener listener) {
        this.listener = listener;
    }

    public interface OnToggledListener {
        void onTab(int tab);
    }
}
