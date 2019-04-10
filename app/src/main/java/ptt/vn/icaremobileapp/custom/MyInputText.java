package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;


public class MyInputText extends CardView {
    private TextInputLayout textInputLayout;
    private MyAutoCompleteTextView myAutoCompleteTextView;
    private CharSequence hint, text;
    private int inputType;

    public MyInputText(@NonNull Context context) {
        super(context);
        create(context);
    }

    public MyInputText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        create(context);
    }

    public MyInputText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        create(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyInputText);
        hint = typedArray.getString(R.styleable.MyInputText_hint);
        text = typedArray.getString(R.styleable.MyInputText_txt);
        inputType = typedArray.getInt(R.styleable.MyInputText_android_inputType, EditorInfo.TYPE_NULL);
        typedArray.recycle();
    }

    private void create(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.my_inputtext, this);

            textInputLayout = findViewById(R.id.textInputLayout);
            myAutoCompleteTextView = findViewById(R.id.myAutoCompleteTextView);

            if (!TextUtils.isEmpty(hint)) textInputLayout.setHint(hint);
            if (!TextUtils.isEmpty(text)) myAutoCompleteTextView.setText(text);
            if (inputType != EditorInfo.TYPE_NULL) myAutoCompleteTextView.setInputType(inputType);

        }
    }

    public void setCustomView(@NonNull CharSequence hint, @NonNull CharSequence text, boolean selected) {
        textInputLayout.setHint(hint);
        myAutoCompleteTextView.setText(text);
        myAutoCompleteTextView.setEnabled(selected);
    }

    public void setText(@NonNull CharSequence charSequence) {
        myAutoCompleteTextView.setText(charSequence);
    }

    public CharSequence getText() {
        return myAutoCompleteTextView.getText();
    }

}
