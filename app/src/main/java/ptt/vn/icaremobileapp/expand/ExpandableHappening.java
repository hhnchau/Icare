package ptt.vn.icaremobileapp.expand;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
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

import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.patient.PatientAdrr;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientHi;
import ptt.vn.icaremobileapp.utils.Utils;

public class ExpandableHappening extends LinearLayout {
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

    private ImageView icArrow;
    private TextView tvTitle;

    private MyTextView tvPatientAddress, tvPatientCode, tvPatientBirthday,
            tvPatientSex, tvPatientNation, tvpPatientPhone, tvPatientJob,
            tvPatientRelative, tvPatientObject, tvInsuranceCode, tvInsuranceStart,
            tvInsuranceEnd, tvInsuranceInit;


    private String title;


    public ExpandableHappening(Context context) {
        super(context);
    }

    public ExpandableHappening(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);
        initView(context);
    }

    public ExpandableHappening(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttributes(context, attrs);
        initView(context);
    }

    public void setChildrenView(@NonNull PatientDomain patient) {

        tvTitle.setText(patient.getPATIENTNAME());

        List<PatientAdrr> lstAdrr = patient.getLstPatientAddr();
        if (lstAdrr != null && lstAdrr.size() > 0)
            for (PatientAdrr add : lstAdrr)
                if (add.getActive() == 1) {
                    tvPatientAddress.setValues(add.getAddresfull());
                    break;
                }

        tvPatientCode.setValues(patient.getPatid());

        tvPatientBirthday.setValues(Utils.dateConvert(patient.getBirthday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));

        tvPatientSex.setValues(patient.getGender());

        tvPatientNation.setValues(patient.getNamenation());

        tvpPatientPhone.setValues(patient.getPhone());

        tvPatientJob.setValues(patient.getNamejob());

        tvPatientRelative.setValues(patient.getFaname());

        tvPatientObject.setValues("");

        List<PatientHi> lstHi = patient.getLstPatientHi();
        if (lstHi != null && lstHi.size() > 0)
            for (PatientHi hi : lstHi) {
                tvInsuranceCode.setValues(hi.getNohi());
                tvInsuranceStart.setValues(Utils.dateConvert(hi.getStrday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
                tvInsuranceEnd.setValues(Utils.dateConvert(hi.getEndday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
                tvInsuranceInit.setValues(hi.getHospitalcode());
                break;
            }

    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.expandablecardview, this);
        }
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableHappening);
        title = typedArray.getString(R.styleable.ExpandableHappening_title);
        innerViewRes = typedArray.getResourceId(R.styleable.ExpandableHappening_children_view, View.NO_ID);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        icArrow = findViewById(R.id.arrow);
        tvTitle = findViewById(R.id.tvTitle);
        //Set Text Title
        if (!TextUtils.isEmpty(title)) tvTitle.setText(title);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            childrenView = inflater.inflate(innerViewRes, null);

            containerView = findViewById(R.id.viewContainer);

            //Init Children View
            tvPatientAddress = childrenView.findViewById(R.id.tvPatientAddress);
            tvPatientCode = childrenView.findViewById(R.id.tvPatientCode);
            tvPatientBirthday = childrenView.findViewById(R.id.tvPatientBirthday);
            tvPatientSex = childrenView.findViewById(R.id.tvPatientSex);
            tvPatientNation = childrenView.findViewById(R.id.tvPatientNation);
            tvpPatientPhone = childrenView.findViewById(R.id.tvpPatientPhone);
            tvPatientJob = childrenView.findViewById(R.id.tvPatientJob);
            tvPatientRelative = childrenView.findViewById(R.id.tvPatientRelative);
            tvPatientObject = childrenView.findViewById(R.id.tvPatientObject);
            tvInsuranceCode = childrenView.findViewById(R.id.tvInsuranceCode);
            tvInsuranceStart = childrenView.findViewById(R.id.tvInsuranceStart);
            tvInsuranceEnd = childrenView.findViewById(R.id.tvInsuranceEnd);
            tvInsuranceInit = childrenView.findViewById(R.id.tvInsuranceInit);

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
        expandAnimation.setDuration((long) distance);
        startAnimation(expandAnimation);

        RotateAnimation arrowAnimation = animationType == EXPANDING ?
                new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f) :
                new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);

        arrowAnimation.setFillAfter(true);
        arrowAnimation.setDuration((long) distance);
        icArrow.startAnimation(arrowAnimation);

        isExpanding = animationType == EXPANDING;
        isCollapsing = animationType == COLLAPSING;
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
