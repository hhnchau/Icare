package ptt.vn.icaremobileapp.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;


import java.util.Arrays;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.dialog.DialogNewHappening;

public class MyFloatingKeyboard {
    private View rootView;
    private final View view;
    private final FloatingKeyboard floatingKeyboard;

    private MyFloatingKeyboard(MyContext myContext, View view) {
        this.view = view;
        this.floatingKeyboard = new FloatingKeyboard(myContext.getContext());
        final NestedScrollView scrollParent = findScrollParent(view);
        if (scrollParent != null) {
            scrollParent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    floatingKeyboard.setTranslationY(floatingKeyboard.getTranslationY() - (scrollY - oldScrollY));
                }
            });
        }
    }

    private MyFloatingKeyboard(MyContext myContext, View rootView, View view) {
        this.rootView = rootView;
        this.view = view;
        this.floatingKeyboard = new FloatingKeyboard(myContext.getContext());
        final NestedScrollView scrollParent = findScrollParent(view);
        if (scrollParent != null) {
            scrollParent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    floatingKeyboard.setTranslationY(floatingKeyboard.getTranslationY() - (scrollY - oldScrollY));
                }
            });
        }
    }

    private MyFloatingKeyboard(View view) {
        this(new MyContext(getActivityContext(view.getContext())), view);
    }

    public static MyFloatingKeyboard on(final View view) {
        return new MyFloatingKeyboard(new MyContext(getActivityContext(view.getContext())), view);
    }

    public static MyFloatingKeyboard on(Fragment fragment, final View view) {
        return new MyFloatingKeyboard(new MyContext(fragment), view);
    }

    public static MyFloatingKeyboard on(Activity activity, final View view) {
        return new MyFloatingKeyboard(new MyContext(getActivityContext(activity)), view);
    }

    public static MyFloatingKeyboard on(Activity activity, final View rootView, final View view) {
        return new MyFloatingKeyboard(new MyContext(getActivityContext(activity)), rootView, view);
    }

    private NestedScrollView findScrollParent(View view) {
        if (view.getParent() == null || !(view.getParent() instanceof View)) {
            return null;
        } else if (view.getParent() instanceof NestedScrollView) {
            return ((NestedScrollView) view.getParent());
        } else {
            return findScrollParent(((View) view.getParent()));
        }
    }

    private static Activity getActivityContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public MyFloatingKeyboard position(Position position) {
        this.floatingKeyboard.setPosition(position);
        return this;
    }

    public MyFloatingKeyboard withShadow(boolean withShadow) {
        this.floatingKeyboard.setWithShadow(withShadow);
        return this;
    }

    public MyFloatingKeyboard shadowColor(@ColorInt int shadowColor) {
        this.floatingKeyboard.setShadowColor(shadowColor);
        return this;
    }

    public MyFloatingKeyboard customView(View customView) {
        this.floatingKeyboard.setCustomView(customView);
        return this;
    }

    public MyFloatingKeyboard customView(int viewId) {
        this.floatingKeyboard.setCustomView(((Activity) view.getContext()).findViewById(viewId));
        return this;
    }

    public MyFloatingKeyboard arrowWidth(int arrowWidth) {
        this.floatingKeyboard.setArrowWidth(arrowWidth);
        return this;
    }

    public MyFloatingKeyboard arrowHeight(int arrowHeight) {
        this.floatingKeyboard.setArrowHeight(arrowHeight);
        return this;
    }

    public MyFloatingKeyboard arrowSourceMargin(int arrowSourceMargin) {
        this.floatingKeyboard.setArrowSourceMargin(arrowSourceMargin);
        return this;
    }

    public MyFloatingKeyboard arrowTargetMargin(int arrowTargetMargin) {
        this.floatingKeyboard.setArrowTargetMargin(arrowTargetMargin);
        return this;
    }

    public MyFloatingKeyboard align(ALIGN align) {
        this.floatingKeyboard.setAlign(align);
        return this;
    }

    public FloatingKeyboard show() {
        final Context activityContext = floatingKeyboard.getContext();
        if (activityContext != null && activityContext instanceof Activity) {
            final ViewGroup decorView = rootView != null ?
                    (ViewGroup) rootView :
                    (ViewGroup) ((Activity) activityContext).getWindow().getDecorView();

            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Rect rect = new Rect();
                    view.getGlobalVisibleRect(rect);

                    final Rect rootGlobalRect = new Rect();
                    final Point rootGlobalOffset = new Point();
                    decorView.getGlobalVisibleRect(rootGlobalRect, rootGlobalOffset);

                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    rect.left = location[0];
                    if (rootGlobalOffset != null) {
                        rect.top -= rootGlobalOffset.y;
                        rect.bottom -= rootGlobalOffset.y;
                        rect.left -= rootGlobalOffset.x;
                        rect.right -= rootGlobalOffset.x;
                    }

                    decorView.addView(floatingKeyboard, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    floatingKeyboard.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {

                            floatingKeyboard.setup(rect, decorView.getWidth());

                            floatingKeyboard.getViewTreeObserver().removeOnPreDrawListener(this);

                            return false;
                        }
                    });
                }
            }, 1);
        }
        return floatingKeyboard;
    }

    public void close(){
        floatingKeyboard.remove();
    }

    public MyFloatingKeyboard duration(long duration) {
        this.floatingKeyboard.setDuration(duration);
        return this;
    }

    public MyFloatingKeyboard color(int color) {
        this.floatingKeyboard.setColor(color);
        return this;
    }

    public MyFloatingKeyboard color(Paint paint) {
        this.floatingKeyboard.setPaint(paint);
        return this;
    }

    public MyFloatingKeyboard onDisplay(ListenerDisplay listener) {
        this.floatingKeyboard.setListenerDisplay(listener);
        return this;
    }

    public MyFloatingKeyboard onHide(ListenerHide listener) {
        this.floatingKeyboard.setListenerHide(listener);
        return this;
    }

    public MyFloatingKeyboard padding(int left, int top, int right, int bottom) {
        this.floatingKeyboard.paddingTop = top;
        this.floatingKeyboard.paddingBottom = bottom;
        this.floatingKeyboard.paddingLeft = left;
        this.floatingKeyboard.paddingRight = right;
        return this;
    }

    public MyFloatingKeyboard animation(TooltipAnimation tooltipAnimation) {
        this.floatingKeyboard.setTooltipAnimation(tooltipAnimation);
        return this;
    }

    public MyFloatingKeyboard text(String text) {
        this.floatingKeyboard.setText(text);
        return this;
    }

    public MyFloatingKeyboard text(@StringRes int text) {
        this.floatingKeyboard.setText(text);
        return this;
    }

    public MyFloatingKeyboard corner(int corner) {
        this.floatingKeyboard.setCorner(corner);
        return this;
    }

    public MyFloatingKeyboard textColor(int textColor) {
        this.floatingKeyboard.setTextColor(textColor);
        return this;
    }

    public MyFloatingKeyboard textTypeFace(Typeface typeface) {
        this.floatingKeyboard.setTextTypeFace(typeface);
        return this;
    }

    public MyFloatingKeyboard textSize(int unit, float textSize) {
        this.floatingKeyboard.setTextSize(unit, textSize);
        return this;
    }

    public MyFloatingKeyboard setTextGravity (int textGravity) {
        this.floatingKeyboard.setTextGravity(textGravity);
        return this;
    }

    public MyFloatingKeyboard clickToHide(boolean clickToHide) {
        this.floatingKeyboard.setClickToHide(clickToHide);
        return this;
    }

    public MyFloatingKeyboard autoHide(boolean autoHide, long duration) {
        this.floatingKeyboard.setAutoHide(autoHide);
        this.floatingKeyboard.setDuration(duration);
        return this;
    }

    public MyFloatingKeyboard distanceWithView(int distance) {
        this.floatingKeyboard.setDistanceWithView(distance);
        return this;
    }

    public MyFloatingKeyboard border(int color,float width){
        Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(color);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(width);
        this.floatingKeyboard.setBorderPaint(borderPaint);
        return this;
    }

    public enum Position {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
    }

    public enum ALIGN {
        START,
        CENTER,
        END
    }

    public interface TooltipAnimation {
        void animateEnter(View view, Animator.AnimatorListener animatorListener);

        void animateExit(View view, Animator.AnimatorListener animatorListener);
    }

    public interface ListenerDisplay {
        void onDisplay(View view);
    }

    public interface ListenerHide {
        void onHide(View view);
    }

    public static class FadeTooltipAnimation implements TooltipAnimation {

        private long fadeDuration = 400;

        public FadeTooltipAnimation() {
        }

        public FadeTooltipAnimation(long fadeDuration) {
            this.fadeDuration = fadeDuration;
        }

        @Override
        public void animateEnter(View view, Animator.AnimatorListener animatorListener) {
            view.setAlpha(0);
            view.animate().alpha(1).setDuration(fadeDuration).setListener(animatorListener);
        }

        @Override
        public void animateExit(View view, Animator.AnimatorListener animatorListener) {
            view.animate().alpha(0).setDuration(fadeDuration).setListener(animatorListener);
        }
    }

    public static class FloatingKeyboard extends FrameLayout {

        private static final int MARGIN_SCREEN_BORDER_TOOLTIP = 30;
        private int arrowHeight = 15;
        private int arrowWidth = 15;
        private int arrowSourceMargin = 0;
        private int arrowTargetMargin = 0;
        protected View childView;
        private int color = Color.parseColor("#1F7C82");
        private Path bubblePath;
        private Paint bubblePaint;
        private Paint borderPaint;
        private Position position = Position.BOTTOM;
        private ALIGN align = ALIGN.CENTER;
        private boolean clickToHide;
        private boolean autoHide = true;
        private long duration = 4000;

        private ListenerDisplay listenerDisplay;

        private ListenerHide listenerHide;

        private TooltipAnimation tooltipAnimation = new FadeTooltipAnimation();

        private int corner = 30;

        private int paddingTop = 20;
        private int paddingBottom = 30;
        private int paddingRight = 30;
        private int paddingLeft = 30;

        int shadowPadding = 4;
        int shadowWidth = 8;

        private Rect viewRect;
        private int distanceWithView = 0;
        private int shadowColor = Color.parseColor("#aaaaaa");

        public FloatingKeyboard(Context context) {
            super(context);
            setWillNotDraw(false);

//            this.childView = new TextView(context);
//            ((TextView) childView).setTextColor(Color.WHITE);
//            ((TextView) childView).setText("Chau");

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.childView = vi.inflate(R.layout.resolved, this, false);

            MyButton m =  this.childView.findViewById(R.id.btnSave);
            m.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {
                    remove();
                }
            });

            addView(childView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //childView.setPadding(0, 0, 0, 0);

            bubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            bubblePaint.setColor(color);
            //bubblePaint.setStyle(Paint.Style.FILL);

            //borderPaint = null;

            //setLayerType(LAYER_TYPE_SOFTWARE, bubblePaint);

            //setWithShadow(true);

        }

        public void setCustomView(View customView) {
            this.removeView(childView);
            this.childView = customView;
            addView(childView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        public void setColor(int color) {
            this.color = color;
            bubblePaint.setColor(color);
            postInvalidate();
        }

        public void setShadowColor(int color) {
            this.shadowColor = color;
            postInvalidate();
        }

        public void setPaint(Paint paint) {
            bubblePaint = paint;
            setLayerType(LAYER_TYPE_SOFTWARE, paint);
            postInvalidate();
        }

        public void setPosition(Position position) {
            this.position = position;
            switch (position){
                case TOP:
                    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom + arrowHeight);
                    break;
                case BOTTOM:
                    setPadding(paddingLeft, paddingTop + arrowHeight, paddingRight, paddingBottom);
                    break;
                case LEFT:
                    setPadding(paddingLeft, paddingTop, paddingRight + arrowHeight, paddingBottom);
                    break;
                case RIGHT:
                    setPadding(paddingLeft + arrowHeight, paddingTop, paddingRight, paddingBottom);
                    break;
            }
            postInvalidate();
        }

        public void setAlign(ALIGN align) {
            this.align = align;
            postInvalidate();
        }

        public void setText(String text) {
            if (childView instanceof TextView) {
                ((TextView) this.childView).setText(Html.fromHtml(text));
            }
            postInvalidate();
        }

        public void setText(int text) {
            if (childView instanceof TextView) {
                ((TextView) this.childView).setText(text);
            }
            postInvalidate();
        }

        public void setTextColor(int textColor) {
            if (childView instanceof TextView) {
                ((TextView) this.childView).setTextColor(textColor);
            }
            postInvalidate();
        }

        public int getArrowHeight() {
            return arrowHeight;
        }

        public void setArrowHeight(int arrowHeight) {
            this.arrowHeight = arrowHeight;
            postInvalidate();
        }

        public int getArrowWidth() {
            return arrowWidth;
        }

        public void setArrowWidth(int arrowWidth) {
            this.arrowWidth = arrowWidth;
            postInvalidate();
        }

        public int getArrowSourceMargin() {
            return arrowSourceMargin;
        }

        public void setArrowSourceMargin(int arrowSourceMargin) {
            this.arrowSourceMargin = arrowSourceMargin;
            postInvalidate();
        }

        public int getArrowTargetMargin() {
            return arrowTargetMargin;
        }

        public void setArrowTargetMargin(int arrowTargetMargin) {
            this.arrowTargetMargin = arrowTargetMargin;
            postInvalidate();
        }

        public void setTextTypeFace(Typeface textTypeFace) {
            if (childView instanceof TextView) {
                ((TextView) this.childView).setTypeface(textTypeFace);
            }
            postInvalidate();
        }

        public void setTextSize(int unit, float size) {
            if (childView instanceof TextView) {
                ((TextView) this.childView).setTextSize(unit, size);
            }
            postInvalidate();
        }

        public void setTextGravity(int textGravity) {
            if (childView instanceof TextView) {
                ((TextView) this.childView).setGravity(textGravity);
            }
            postInvalidate();
        }

        public void setClickToHide(boolean clickToHide) {
            this.clickToHide = clickToHide;
        }

        public void setCorner(int corner) {
            this.corner = corner;
        }

        @Override
        protected void onSizeChanged(int width, int height, int oldw, int oldh) {
            super.onSizeChanged(width, height, oldw, oldh);

            bubblePath = drawBubble(new RectF(shadowPadding, shadowPadding, width - shadowPadding * 2, height - shadowPadding * 2), corner, corner, corner, corner);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (bubblePath != null) {
                canvas.drawPath(bubblePath, bubblePaint);
                if(borderPaint != null){
                    canvas.drawPath(bubblePath,borderPaint);
                }
            }
        }

        public void setListenerDisplay(ListenerDisplay listener) {
            this.listenerDisplay = listener;
        }

        public void setListenerHide(ListenerHide listener) {
            this.listenerHide = listener;
        }

        public void setTooltipAnimation(TooltipAnimation tooltipAnimation) {
            this.tooltipAnimation = tooltipAnimation;
        }

        protected void startEnterAnimation() {
            tooltipAnimation.animateEnter(this, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (listenerDisplay != null) {
                        listenerDisplay.onDisplay(FloatingKeyboard.this);
                    }
                }
            });
        }

        protected void startExitAnimation(final Animator.AnimatorListener animatorListener) {
            tooltipAnimation.animateExit(this, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    animatorListener.onAnimationEnd(animation);
                    if (listenerHide != null) {
                        listenerHide.onHide(FloatingKeyboard.this);
                    }
                }
            });
        }

        protected void handleAutoRemove() {
            if (clickToHide) {
                setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickToHide) {
                            remove();
                        }
                    }
                });
            }

            if (autoHide) {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        remove();
                    }
                }, duration);
            }
        }

        public void remove() {
            startExitAnimation(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    removeNow();
                }
            });
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public void setAutoHide(boolean autoHide) {
            this.autoHide = autoHide;
        }

        public void setupPosition(Rect rect) {

            int x, y;

            if (position == Position.LEFT || position == Position.RIGHT) {
                if (position == Position.LEFT) {
                    x = rect.left - getWidth() - distanceWithView;
                } else {
                    x = rect.right + distanceWithView;
                }
                y = rect.top + getAlignOffset(getHeight(), rect.height());
            } else {
                if (position == Position.BOTTOM) {
                    y = rect.bottom + distanceWithView;
                } else { // top
                    y = rect.top - getHeight() - distanceWithView;
                }
                x = rect.left + getAlignOffset(getWidth(), rect.width());
            }

            setTranslationX(x);
            setTranslationY(y);
        }

        private int getAlignOffset(int myLength, int hisLength) {
            switch (align) {
                case END:    return hisLength - myLength;
                case CENTER: return (hisLength - myLength) / 2;
            }
            return 0;
        }

        private Path drawBubble(RectF myRect, float topLeftDiameter, float topRightDiameter, float bottomRightDiameter, float bottomLeftDiameter) {
            final Path path = new Path();

            if(viewRect == null)
                return path;

            topLeftDiameter = topLeftDiameter < 0 ? 0 : topLeftDiameter;
            topRightDiameter = topRightDiameter < 0 ? 0 : topRightDiameter;
            bottomLeftDiameter = bottomLeftDiameter < 0 ? 0 : bottomLeftDiameter;
            bottomRightDiameter = bottomRightDiameter < 0 ? 0 : bottomRightDiameter;

            final float spacingLeft = this.position == Position.RIGHT ? arrowHeight : 0;
            final float spacingTop = this.position == Position.BOTTOM ? arrowHeight : 0;
            final float spacingRight = this.position == Position.LEFT ? arrowHeight : 0;
            final float spacingBottom = this.position == Position.TOP ? arrowHeight : 0;

            final float left = spacingLeft + myRect.left;
            final float top = spacingTop + myRect.top;
            final float right = myRect.right - spacingRight;
            final float bottom = myRect.bottom - spacingBottom;
            final float centerX = viewRect.centerX() - getX();

            final float arrowSourceX = (Arrays.asList(Position.TOP, Position.BOTTOM).contains(this.position))
                    ? centerX + arrowSourceMargin
                    : centerX;
            final float arrowTargetX = (Arrays.asList(Position.TOP, Position.BOTTOM).contains(this.position))
                    ? centerX + arrowTargetMargin
                    : centerX;
            final float arrowSourceY = (Arrays.asList(Position.RIGHT, Position.LEFT).contains(this.position))
                    ? bottom / 2f - arrowSourceMargin
                    : bottom / 2f;
            final float arrowTargetY = (Arrays.asList(Position.RIGHT, Position.LEFT).contains(this.position))
                    ? bottom / 2f - arrowTargetMargin
                    : bottom / 2f;

            path.moveTo(left + topLeftDiameter / 2f, top);
            //LEFT, TOP

            if (position == Position.BOTTOM) {
                path.lineTo(arrowSourceX - arrowWidth, top);
                path.lineTo(arrowTargetX, myRect.top);
                path.lineTo(arrowSourceX + arrowWidth, top);
            }
            path.lineTo(right - topRightDiameter / 2f, top);

            path.quadTo(right, top, right, top + topRightDiameter / 2);
            //RIGHT, TOP

            if (position == Position.LEFT) {
                path.lineTo(right, arrowSourceY - arrowWidth);
                path.lineTo(myRect.right, arrowTargetY);
                path.lineTo(right, arrowSourceY + arrowWidth);
            }
            path.lineTo(right, bottom - bottomRightDiameter / 2);

            path.quadTo(right, bottom, right - bottomRightDiameter / 2, bottom);
            //RIGHT, BOTTOM

            if (position == Position.TOP) {
                path.lineTo(arrowSourceX + arrowWidth, bottom);
                path.lineTo(arrowTargetX, myRect.bottom);
                path.lineTo(arrowSourceX - arrowWidth, bottom);
            }
            path.lineTo(left + bottomLeftDiameter / 2, bottom);

            path.quadTo(left, bottom, left, bottom - bottomLeftDiameter / 2);
            //LEFT, BOTTOM

            if (position == Position.RIGHT) {
                path.lineTo(left, arrowSourceY + arrowWidth);
                path.lineTo(myRect.left, arrowTargetY);
                path.lineTo(left, arrowSourceY - arrowWidth);
            }
            path.lineTo(left, top + topLeftDiameter / 2);

            path.quadTo(left, top, left + topLeftDiameter / 2, top);

            path.close();

            return path;
        }

        public boolean adjustSize(Rect rect, int screenWidth) {

            final Rect r = new Rect();
            getGlobalVisibleRect(r);

            boolean changed = false;
            final ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (position == Position.LEFT && getWidth() > rect.left) {
                layoutParams.width = rect.left - MARGIN_SCREEN_BORDER_TOOLTIP - distanceWithView;
                changed = true;
            } else if (position == Position.RIGHT && rect.right + getWidth() > screenWidth) {
                layoutParams.width = screenWidth - rect.right - MARGIN_SCREEN_BORDER_TOOLTIP - distanceWithView;
                changed = true;
            } else if (position == Position.TOP || position == Position.BOTTOM) {
                int adjustedLeft = rect.left;
                int adjustedRight = rect.right;

                if((rect.centerX() + getWidth() / 2f) > screenWidth){
                    float diff = (rect.centerX() + getWidth() / 2f) - screenWidth;

                    adjustedLeft -=  diff;
                    adjustedRight -=  diff;

                    setAlign(ALIGN.CENTER);
                    changed = true;
                }else if((rect.centerX() - getWidth() / 2f) < 0){
                    float diff = -(rect.centerX() - getWidth() / 2f);

                    adjustedLeft +=  diff;
                    adjustedRight +=  diff;

                    setAlign(ALIGN.CENTER);
                    changed = true;
                }

                if(adjustedLeft < 0){
                    adjustedLeft = 0;
                }

                if(adjustedRight > screenWidth){
                    adjustedRight = screenWidth;
                }

                rect.left = adjustedLeft;
                rect.right = adjustedRight;
            }

            setLayoutParams(layoutParams);
            postInvalidate();
            return changed;
        }

        private void onSetup(Rect myRect) {
            setupPosition(myRect);

            bubblePath = drawBubble(new RectF(shadowPadding, shadowPadding, getWidth() - shadowPadding * 2f, getHeight() - shadowPadding * 2f), corner, corner, corner, corner);
            startEnterAnimation();

            //handleAutoRemove();
        }

        public void setup(final Rect viewRect, int screenWidth) {
            this.viewRect = new Rect(viewRect);
            final Rect myRect = new Rect(viewRect);

            final boolean changed = adjustSize(myRect, screenWidth);
            if (!changed) {
                onSetup(myRect);
            } else {
                getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        onSetup(myRect);
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
            }
        }

        public void close() {
            remove();
        }

        public void removeNow() {
            if (getParent() != null) {
                final ViewGroup parent = ((ViewGroup) getParent());
                parent.removeView(FloatingKeyboard.this);
            }
        }

        public void closeNow() {
            removeNow();
        }

        public void setWithShadow(boolean withShadow) {
            if(withShadow){
                bubblePaint.setShadowLayer(shadowWidth, 0, 0, shadowColor);
            } else {
                bubblePaint.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
            }
        }

        public void setDistanceWithView(int distanceWithView) {
            this.distanceWithView = distanceWithView;
        }

        public void setBorderPaint(Paint borderPaint) {
            this.borderPaint = borderPaint;
            postInvalidate();
        }
    }

    public static class MyContext {
        private Fragment fragment;
        private Context context;
        private Activity activity;

        public MyContext(Activity activity) {
            this.activity = activity;
        }

        public MyContext(Fragment fragment) {
            this.fragment = fragment;
        }

        public MyContext(Context context) {
            this.context = context;
        }

        public Context getContext() {
            if (activity != null) {
                return activity;
            } else {
                return fragment.getActivity();
            }
        }

        public Activity getActivity() {
            if (activity != null) {
                return activity;
            } else {
                return fragment.getActivity();
            }
        }


        public Window getWindow() {
            if (activity != null) {
                return activity.getWindow();
            } else {
                if (fragment instanceof DialogFragment) {
                    return ((DialogFragment) fragment).getDialog().getWindow();
                }
                return fragment.getActivity().getWindow();
            }
        }
    }
}