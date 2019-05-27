package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import ptt.vn.icaremobileapp.R;

public class MyNumPad extends LinearLayout implements View.OnClickListener {
    public MyNumPad(Context context) {
        super(context);
        create(context);
    }

    public MyNumPad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        create(context);
    }

    public MyNumPad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create(context);
    }

    private void create(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.keyboard_numpad, this);

            this.findViewById(R.id.key0).setOnClickListener(this);
            this.findViewById(R.id.key1).setOnClickListener(this);
            this.findViewById(R.id.key2).setOnClickListener(this);
            this.findViewById(R.id.key3).setOnClickListener(this);
            this.findViewById(R.id.key4).setOnClickListener(this);
            this.findViewById(R.id.key5).setOnClickListener(this);
            this.findViewById(R.id.key6).setOnClickListener(this);
            this.findViewById(R.id.key7).setOnClickListener(this);
            this.findViewById(R.id.key8).setOnClickListener(this);
            this.findViewById(R.id.key9).setOnClickListener(this);
            this.findViewById(R.id.keyclear).setOnClickListener(this);
            this.findViewById(R.id.keydivide).setOnClickListener(this);
            this.findViewById(R.id.keydone).setOnClickListener(this);
        }
    }

    public void show() {
        if (this.getVisibility() == View.GONE) this.setVisibility(View.VISIBLE);
    }

    public void hide() {
        if (this.getVisibility() == VISIBLE) this.setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        if (onListener != null) onListener.onKey(((TextView) v).getText());
    }

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }

    private OnListener onListener;

    public interface OnListener {
        void onKey(CharSequence chr);
    }
}
