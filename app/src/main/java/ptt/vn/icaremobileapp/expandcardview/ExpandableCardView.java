package ptt.vn.icaremobileapp.expandcardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;

public class ExpandableCardView extends LinearLayout {
    private View childrenView;
    private ViewGroup containerView;
    private int innerViewRes;

    private boolean firstTime = false;
    private final static int COLLAPSING = 0;
    private final static int EXPANDING = 1;
    private int previousHeight = 0;
    private boolean isExpanded = false;
    private boolean isExpanding = false;
    private boolean isCollapsing = false;

    private ImageView arrowBtn;
    private TextView textViewTitle;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private String title;


    public ExpandableCardView(Context context) {
        super(context);
    }

    public ExpandableCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);
        initView(context);
    }

    public ExpandableCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttributes(context, attrs);
        initView(context);
    }

    public void setChildrenView(String title) {

        textViewTitle.setText(title);

        textView1.setText("1");
        textView2.setText("2");
        textView3.setText("3");
        textView4.setText("4");
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.expandablecardview, this);
        }
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableCardView);
        title = typedArray.getString(R.styleable.ExpandableCardView_title);
        innerViewRes = typedArray.getResourceId(R.styleable.ExpandableCardView_children_view, View.NO_ID);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        arrowBtn = findViewById(R.id.arrow);
        textViewTitle = findViewById(R.id.tvTitle);
        //Set Text Title
        if(!TextUtils.isEmpty(title)) textViewTitle.setText(title);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null){
            childrenView = inflater.inflate(innerViewRes, null);

            containerView = findViewById(R.id.viewContainer);

            //Init Children View
//            textView1 = childrenView.findViewById(R.id.text1);
//            textView2 = childrenView.findViewById(R.id.text2);
//            textView3 = childrenView.findViewById(R.id.text3);
//            textView4 = childrenView.findViewById(R.id.text4);
        }
    }

    public void expand() {

        final int initialHeight = getHeight();

        if (!isMoving()) {
            previousHeight = initialHeight;
            containerView.addView(childrenView);
        }

        measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        int targetHeight = childrenView.getMeasuredHeight() + initialHeight;

        if (targetHeight - initialHeight != 0) {
            if (firstTime) {
                containerView.getLayoutParams().height = initialHeight;
                firstTime = false;
            }
            animateViews(initialHeight,
                    targetHeight - initialHeight,
                    EXPANDING);
        }
    }

    public void collapse() {
        int initialHeight = getMeasuredHeight();

        if (initialHeight - previousHeight != 0) {
            animateViews(initialHeight,
                    initialHeight - previousHeight,
                    COLLAPSING);
        }

    }

    public boolean isExpanded() {
        return isExpanded;
    }

    private void animateViews(final int initialHeight, final int distance, final int animationType) {

        Animation expandAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    isExpanding = false;
                    isCollapsing = false;

                    if (animationType == COLLAPSING) {
                        containerView.removeView(childrenView);
                    }
                }

                getLayoutParams().height = animationType == EXPANDING ? (int) (initialHeight + (distance * interpolatedTime)) :
                        (int) (initialHeight - (distance * interpolatedTime));
                requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        RotateAnimation arrowAnimation = animationType == EXPANDING ?
                new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f) :
                new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);

        arrowAnimation.setFillAfter(true);

        arrowAnimation.setDuration((long) distance);
        expandAnimation.setDuration((long) distance);

        isExpanding = animationType == EXPANDING;
        isCollapsing = animationType == COLLAPSING;

        startAnimation(expandAnimation);
        arrowBtn.startAnimation(arrowAnimation);
        isExpanded = animationType == EXPANDING;

    }

    private boolean isExpanding() {
        return isExpanding;
    }

    private boolean isCollapsing() {
        return isCollapsing;
    }

    private boolean isMoving() {
        return isExpanding() || isCollapsing();
    }
}
