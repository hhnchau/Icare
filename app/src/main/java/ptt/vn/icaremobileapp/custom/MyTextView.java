package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;

public class MyTextView extends LinearLayout {
    private TextView txtLabel, txtValue;
    private CharSequence label, value;

    public MyTextView(Context context) {
        super(context);
        create(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        create(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        create(context);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs);
        create(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        label = typedArray.getString(R.styleable.MyTextView_label);
        value = typedArray.getString(R.styleable.MyTextView_value);
        typedArray.recycle();
    }

    private void create(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.my_textview, this);

            txtLabel = findViewById(R.id.label);
            txtValue = findViewById(R.id.value);
            if (!TextUtils.isEmpty(label)) txtLabel.setText(label);
            if (!TextUtils.isEmpty(value)) txtValue.setText(value);
        }
    }

    public void setContentView(@NonNull CharSequence label, @NonNull CharSequence value) {
        txtLabel.setText(label);
        txtValue.setText(value);
    }

    public void setValues(@NonNull CharSequence value) {
        txtValue.setText(value);
    }

    public void setLabel(@NonNull CharSequence label) {
        txtLabel.setText(label);
    }
}
