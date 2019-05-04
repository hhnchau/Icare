package ptt.vn.icaremobileapp.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.lang.reflect.Method;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;


public class MyInputTextOutlineWithKeyboard extends LinearLayout {
    private TextInputLayout textInputLayout;
    private MyAutoCompleteTextView myAutoCompleteTextView;
    private CharSequence hint, text;
    private int inputType;
    private TableLayout keyboard;

    public MyInputTextOutlineWithKeyboard(@NonNull Context context) {
        super(context);
        create(context);
    }

    public MyInputTextOutlineWithKeyboard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        create(context);
    }

    public MyInputTextOutlineWithKeyboard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.my_inputtext_outline_with_keyboard, this);

            textInputLayout = findViewById(R.id.textInputLayout);
            myAutoCompleteTextView = findViewById(R.id.myAutoCompleteTextView);
            keyboard = findViewById(R.id.keyboard);

            if (!TextUtils.isEmpty(hint)) textInputLayout.setHint(hint);
            if (!TextUtils.isEmpty(text)) myAutoCompleteTextView.setText(text);
            if (inputType != EditorInfo.TYPE_NULL) myAutoCompleteTextView.setInputType(inputType);


            myAutoCompleteTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        TranslateAnimation animate = new TranslateAnimation(0,0,-keyboard.getHeight(),0);
                        animate.setDuration(500);

                        keyboard.startAnimation(animate);
                        keyboard.setVisibility(VISIBLE);
                    } else {
                        TranslateAnimation animate = new TranslateAnimation(0,0,0,-keyboard.getHeight());
                        animate.setDuration(500);

                        keyboard.startAnimation(animate);
                        keyboard.setVisibility(GONE);
                    }
                }
            });

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

    public CharSequence getText() {
        return myAutoCompleteTextView.getText();
    }

}
