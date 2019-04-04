package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;

public class MyButton extends CardView {
    private TextView txt;
    private CharSequence text;
    private int textSize;
    private int textColor;
    private Drawable backgroundColor;
    private int padding, paddingStart, paddingEnd, paddingTop, paddingBottom;

    public MyButton(@NonNull Context context) {
        super(context);
        create(context);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        create(context);

    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        create(context);

    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyButton);
        text = typedArray.getString(R.styleable.MyButton_text);
        textSize = typedArray.getDimensionPixelSize(R.styleable.MyButton_textSize, 0);
        padding = typedArray.getDimensionPixelSize(R.styleable.MyButton_padding, 0);
        paddingStart = typedArray.getDimensionPixelSize(R.styleable.MyButton_padding_Start, 0);
        paddingEnd = typedArray.getDimensionPixelSize(R.styleable.MyButton_padding_End, 0);
        paddingBottom = typedArray.getDimensionPixelSize(R.styleable.MyButton_padding_Bottom, 0);
        paddingTop = typedArray.getDimensionPixelSize(R.styleable.MyButton_padding_Top, 0);
        textColor = typedArray.getColor(R.styleable.MyButton_textColor, 0);
        backgroundColor = typedArray.getDrawable(R.styleable.MyButton_backgroundColor);
        typedArray.recycle();
    }


    private void create(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.my_button, this);

            txt = findViewById(R.id.button);
            if (!TextUtils.isEmpty(text)) txt.setText(text);

            if (textSize > 0) txt.setTextSize(textSize);
            if (textColor != 0) txt.setTextColor(textColor);
            if (backgroundColor != null) txt.setBackground(backgroundColor);

            if (paddingTop > 0 || paddingBottom > 0 || paddingEnd > 0 || paddingStart > 0)
                txt.setPadding(dp2px(paddingStart), dp2px(paddingTop), dp2px(paddingEnd), dp2px(paddingBottom));
            if (padding > 0) {
                int pad = dp2px(padding);
                txt.setPadding(pad, pad, pad, pad);
            }

            txt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onClick();
                }
            });
        }
    }


    private OnListener listener;

    public void setOnSelectedListener(OnListener listener) {
        this.listener = listener;
    }

    public interface OnListener {
        void onClick();
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
