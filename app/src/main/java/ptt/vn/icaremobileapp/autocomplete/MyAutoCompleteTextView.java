package ptt.vn.icaremobileapp.autocomplete;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import ptt.vn.icaremobileapp.R;

/**
 * Created by kingpes on 9/11/18.
 * How to use
 * <p>
 * xml:    <ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView
 * android:id="@+id/autocomplete"
 * android:layout_width="match_parent"
 * android:layout_height="wrap_content"
 * android:background="@color/transparent"
 * android:gravity="center_vertical"
 * android:inputType="text"
 * android:hint="Hello Auto Complete"
 * android:completionThreshold="1"
 * android:maxLines="1"/>
 * <p>
 * activity: MyAutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.edtNationality);
 * List<CompleteObject> lst = new ArrayList<>();
 * lst.addMainFrame(new CompleteObject("Android"));
 * lst.addMainFrame(new CompleteObject("Ios"));
 * AutoCompleteTextViewServiceItemAdapter adapter = new AutoCompleteTextViewServiceItemAdapter(this, lst);
 * myAutoCompleteTextView.setAdapter(adapter);
 * myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 *
 * @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 * CompleteObject additional = (CompleteObject) parent.getItemAtPosition(position);
 * String a = additional.getName();
 * }
 * });
 */

public class  MyAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {
    private Bitmap mClearButton;
    private Paint mPaint;

    private int mInitPaddingRight;

    private int mButtonPadding = dp2px(3);

    public MyAutoCompleteTextView(Context context) {
        super(context);
        init(context);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        mClearButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_clear);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mInitPaddingRight = getPaddingRight();
    }


    private void setPadding(boolean isShow) {
        int paddingRight = mInitPaddingRight + (isShow ? mClearButton.getWidth() + mButtonPadding + mButtonPadding : 0);

        setPadding(getPaddingLeft(), getPaddingTop(), paddingRight, getPaddingBottom());
    }

    private Rect getRect(boolean isShow) {
        int left, top, right, bottom;

        right = isShow ? getMeasuredWidth() + getScrollX() - mButtonPadding - mButtonPadding : 0;
        left = isShow ? right - mClearButton.getWidth() : 0;
        top = isShow ? (getMeasuredHeight() - mClearButton.getHeight()) / 2 : 0;
        bottom = isShow ? top + mClearButton.getHeight() : 0;

        setPadding(isShow);

        return new Rect(left, top, right, bottom);
    }


    private void drawBitmap(Canvas canvas, Rect rect) {
        if (rect != null) {
            canvas.drawBitmap(mClearButton, null, rect, mPaint);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        if (getText() != null)
            drawBitmap(canvas, getRect(hasFocus() && getText().length() > 0));

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (event.getX() - (getMeasuredWidth() - getPaddingRight()) >= 0) {
                    setError(null);
                    this.setText("");
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
