package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import ptt.vn.icaremobileapp.R;


public class MyInputTextOutlineDisable extends LinearLayout {
    private TextInputLayout textInputLayout;
    private EditText myAutoCompleteTextView;
    private CharSequence hint, text;

    public MyInputTextOutlineDisable(Context context) {
        super(context);
        create(context);
    }

    public MyInputTextOutlineDisable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        create(context);
    }

    public MyInputTextOutlineDisable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        create(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyInputText);
        hint = typedArray.getString(R.styleable.MyInputText_hint);
        text = typedArray.getString(R.styleable.MyInputText_txt);
        typedArray.recycle();
    }

    private void create(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.my_inputtext_outline_disable, this);

            textInputLayout = findViewById(R.id.textInputLayout);
            myAutoCompleteTextView = findViewById(R.id.myAutoCompleteTextView);

            try {
                Field field = TextInputLayout.class.getDeclaredField("defaultStrokeColor");
                field.setAccessible(true);
                field.set(textInputLayout, getResources().getColor(R.color.disable));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Log.w("TAG", "Failed to change box color, item might look wrong");
            }

            if (!TextUtils.isEmpty(hint)) textInputLayout.setHint(hint);
            if (!TextUtils.isEmpty(text)) myAutoCompleteTextView.setText(text);
        }
    }

    public void setContentView(@NonNull CharSequence hint, @NonNull CharSequence value) {
        textInputLayout.setHint(hint);
        myAutoCompleteTextView.setText(value);
    }

    public void setValues(@NonNull CharSequence value) {
        myAutoCompleteTextView.setText(value);
    }

    public void setHint(@NonNull CharSequence hint) {
        textInputLayout.setHint(hint);
    }

    public CharSequence getText() {
        return myAutoCompleteTextView.getText();
    }

}
