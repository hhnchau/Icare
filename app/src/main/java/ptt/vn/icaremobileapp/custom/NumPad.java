package ptt.vn.icaremobileapp.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Arrays;

import ptt.vn.icaremobileapp.R;


public class NumPad {

    private static NumPad instance = null;

    public static NumPad getInstance(Context context) {
        if (instance == null) {
            instance = new NumPad(context);
        }
        return instance;
    }

    private final FloatingKeyboard floatingKeyboard;

    private NumPad(Context context) {
        floatingKeyboard = new FloatingKeyboard(context);
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

    public void show(final View view) {
        final Context activityContext = floatingKeyboard.getContext();
        final ViewGroup viewGroup = (ViewGroup) ((Activity) activityContext).getWindow().getDecorView();

        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);

        final Rect rootGlobalRect = new Rect();
        final Point rootGlobalOffset = new Point();
        viewGroup.getGlobalVisibleRect(rootGlobalRect, rootGlobalOffset);

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        rect.top -= rootGlobalOffset.y;
        rect.bottom -= rootGlobalOffset.y;
        rect.left -= rootGlobalOffset.x;
        rect.right -= rootGlobalOffset.x;


        if (viewGroup.indexOfChild(floatingKeyboard) == -1)
            viewGroup.addView(floatingKeyboard, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        floatingKeyboard.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                floatingKeyboard.setup(rect, viewGroup.getWidth());

                floatingKeyboard.getViewTreeObserver().removeOnPreDrawListener(this);

                return false;
            }
        });

        floatingKeyboard.setOnListener(new OnListener() {
            @Override
            public void onKey(CharSequence chr) {
                if (view instanceof EditText)
                    if (chr.equals(activityContext.getString(R.string.btn_clear))) {
                        String text = ((EditText) view).getText().toString();
                        if (text.length() > 0)
                            ((EditText) view).setText(text.substring(0, text.length() - 1));
                    } else ((EditText) view).append(chr);
            }
        });

    }

    public void hide() {
        floatingKeyboard.remove();
    }

    public static class FloatingKeyboard extends FrameLayout implements View.OnClickListener {

        private static final int MARGIN_SCREEN_BORDER = 30;
        private int arrowHeight = 15;
        private int arrowWidth = 15;
        private int arrowSourceMargin = 0;
        private int arrowTargetMargin = 0;
        protected View childView;
        private int color = getResources().getColor(R.color.keyboard_bg);
        private Path bubblePath;
        private Paint bubblePaint;
        private Position position = Position.BOTTOM;
        private ALIGN align = ALIGN.CENTER;

        private int corner = 30;
        private int paddingTop = 20;
        private int paddingBottom = 30;
        private int paddingRight = 30;
        private int paddingLeft = 30;
        int shadowPadding = 4;

        private Rect viewRect;
        private int distanceWithView = 0;


        private FadeAnimation fadeAnimation = new FadeAnimation();

        private OnListener onListener;

        public void setOnListener(OnListener onListener) {
            this.onListener = onListener;
        }

        public FloatingKeyboard(Context context) {
            super(context);
            setWillNotDraw(false);
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.childView = vi.inflate(R.layout.keyboard_numpad, this, false);

            addView(childView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            bubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            bubblePaint.setColor(color);

            TextView key0 = this.childView.findViewById(R.id.key0);
            key0.setOnClickListener(this);
            TextView key1 = this.childView.findViewById(R.id.key1);
            key1.setOnClickListener(this);
            TextView key2 = this.childView.findViewById(R.id.key2);
            key2.setOnClickListener(this);
            TextView key3 = this.childView.findViewById(R.id.key3);
            key3.setOnClickListener(this);
            TextView key4 = this.childView.findViewById(R.id.key4);
            key4.setOnClickListener(this);
            TextView key5 = this.childView.findViewById(R.id.key5);
            key5.setOnClickListener(this);
            TextView key6 = this.childView.findViewById(R.id.key6);
            key6.setOnClickListener(this);
            TextView key7 = this.childView.findViewById(R.id.key7);
            key7.setOnClickListener(this);
            TextView key8 = this.childView.findViewById(R.id.key8);
            key8.setOnClickListener(this);
            TextView key9 = this.childView.findViewById(R.id.key9);
            key9.setOnClickListener(this);
            TextView keyClear = this.childView.findViewById(R.id.keyclear);
            keyClear.setOnClickListener(this);
            TextView keyDivide = this.childView.findViewById(R.id.keydivide);
            keyDivide.setOnClickListener(this);


        }

        public void setColor(int color) {
            this.color = color;
            bubblePaint.setColor(color);
            postInvalidate();
        }

        public void setPosition(Position position) {
            this.position = position;
            switch (position) {
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
            }
        }


        protected void startEnterAnimation() {
            fadeAnimation.animateEnter(this, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
        }

        protected void startExitAnimation(final Animator.AnimatorListener animatorListener) {
            fadeAnimation.animateExit(this, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    animatorListener.onAnimationEnd(animation);
                }
            });
        }

        protected void handleAutoRemove() {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove();
                }
            });
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
                case END:
                    return hisLength - myLength;
                case CENTER:
                    return (hisLength - myLength) / 2;
            }
            return 0;
        }

        private Path drawBubble(RectF myRect, float topLeftDiameter, float topRightDiameter, float bottomRightDiameter, float bottomLeftDiameter) {
            final Path path = new Path();

            if (viewRect == null)
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
                layoutParams.width = rect.left - MARGIN_SCREEN_BORDER - distanceWithView;
                changed = true;
            } else if (position == Position.RIGHT && rect.right + getWidth() > screenWidth) {
                layoutParams.width = screenWidth - rect.right - MARGIN_SCREEN_BORDER - distanceWithView;
                changed = true;
            } else if (position == Position.TOP || position == Position.BOTTOM) {
                int adjustedLeft = rect.left;
                int adjustedRight = rect.right;

                if ((rect.centerX() + getWidth() / 2f) > screenWidth) {
                    float diff = (rect.centerX() + getWidth() / 2f) - screenWidth;

                    adjustedLeft -= diff;
                    adjustedRight -= diff;

                    setAlign(ALIGN.CENTER);
                    changed = true;
                } else if ((rect.centerX() - getWidth() / 2f) < 0) {
                    float diff = -(rect.centerX() - getWidth() / 2f);

                    adjustedLeft += diff;
                    adjustedRight += diff;

                    setAlign(ALIGN.CENTER);
                    changed = true;
                }

                if (adjustedLeft < 0) {
                    adjustedLeft = 0;
                }

                if (adjustedRight > screenWidth) {
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

            handleAutoRemove();
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

        public void removeNow() {
            if (getParent() != null) {
                final ViewGroup parent = ((ViewGroup) getParent());
                parent.removeView(FloatingKeyboard.this);
            }
        }

        @Override
        public void onClick(View v) {
            if (onListener != null)
                onListener.onKey(((TextView) v).getText());
        }
    }

    public static class FadeAnimation implements FadeListenerAnimation {

        private long fadeDuration = 400;

        public FadeAnimation() {
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

    public interface FadeListenerAnimation {
        void animateEnter(View view, Animator.AnimatorListener animatorListener);

        void animateExit(View view, Animator.AnimatorListener animatorListener);
    }

    public interface OnListener {
        void onKey(CharSequence chr);
    }
}
