package ptt.vn.icaremobileapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Method;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;


public class MyInputTextOutline extends LinearLayout {
    private Context context;
    private TextInputLayout textInputLayout;
    private MyAutoCompleteTextView myAutoCompleteTextView;
    private CharSequence hint, text;
    private int inputType;

    public MyInputTextOutline(@NonNull Context context) {
        super(context);
        this.context = context;
        create(this.context);
    }

    public MyInputTextOutline(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        this.context = context;
        create(this.context);
    }

    public MyInputTextOutline(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        this.context = context;
        create(this.context);
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
            inflater.inflate(R.layout.my_inputtext_outline, this);

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

    public void setText(@NonNull CharSequence value, boolean selected) {
        myAutoCompleteTextView.setText(value);
        myAutoCompleteTextView.setEnabled(selected);
    }

    public void setError(@NonNull CharSequence value) {
        myAutoCompleteTextView.setError(value);
    }

    public void setText(@NonNull CharSequence charSequence) {
        myAutoCompleteTextView.setText(charSequence);
    }

    public void registerNumPadKeyboard() {
        myAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //((InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //NumPad.getInstance(context).show(myAutoCompleteTextView);
                }
                //else NumPad.getInstance(context).hide();
            }
        });
        myAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumPad.getInstance(context).show(myAutoCompleteTextView);
            }
        });

        // Disable standard keyboard hard way
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myAutoCompleteTextView.setShowSoftInputOnFocus(false);
        } else {
            //For sdk versions [14-20]
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , boolean.class);
                method.setAccessible(true);
                method.invoke(myAutoCompleteTextView, false);
            } catch (Exception e) {
                // ignore
            }
        }
        myAutoCompleteTextView.setInputType(myAutoCompleteTextView.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

    public CharSequence getText() {
        return myAutoCompleteTextView.getText();
    }

}
