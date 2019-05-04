package ptt.vn.icaremobileapp.custom;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import ptt.vn.icaremobileapp.R;

public class Key {

    private static Key instance = null;

    public static Key getInstance(Context context) {
        if (instance == null) {
            instance = new Key(context);
        }
        return instance;
    }

    private final FloatingKeyboard floatingKeyboard;

    private Key(Context context) {
        floatingKeyboard = new FloatingKeyboard(context);
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
                    ((EditText) view).append(chr);
            }
        });

    }

    public void hide() {
        floatingKeyboard.remove();
    }

    public static class FloatingKeyboard extends FrameLayout {

        private static final int MARGIN_SCREEN_BORDER_TOOLTIP = 30;
        private int arrowHeight = 15;
        private int arrowWidth = 15;
        private int arrowSourceMargin = 0;
        private int arrowTargetMargin = 0;
        protected View childView;
        private int color = Color.parseColor("#aaeceff2");
        private Path bubblePath;
        private Paint bubblePaint;
        private Paint borderPaint;
        private MyFloatingKeyboard.Position position = MyFloatingKeyboard.Position.BOTTOM;
        private MyFloatingKeyboard.ALIGN align = MyFloatingKeyboard.ALIGN.CENTER;
        private boolean clickToHide;
        private boolean autoHide = true;
        private long duration = 4000;

        private MyFloatingKeyboard.ListenerDisplay listenerDisplay;

        private MyFloatingKeyboard.ListenerHide listenerHide;

        private MyFloatingKeyboard.TooltipAnimation tooltipAnimation = new MyFloatingKeyboard.FadeTooltipAnimation();

        private OnListener onListener;

        public void setOnListener(OnListener onListener) {
            this.onListener = onListener;
        }

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

            MyButton m = this.childView.findViewById(R.id.btnSave);
            m.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {
                    if (onListener != null) onListener.onKey("A");
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

        public void setPosition(MyFloatingKeyboard.Position position) {
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

        public void setAlign(MyFloatingKeyboard.ALIGN align) {
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
                if (borderPaint != null) {
                    canvas.drawPath(bubblePath, borderPaint);
                }
            }
        }

        public void setListenerDisplay(MyFloatingKeyboard.ListenerDisplay listener) {
            this.listenerDisplay = listener;
        }

        public void setListenerHide(MyFloatingKeyboard.ListenerHide listener) {
            this.listenerHide = listener;
        }

        public void setTooltipAnimation(MyFloatingKeyboard.TooltipAnimation tooltipAnimation) {
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

            if (position == MyFloatingKeyboard.Position.LEFT || position == MyFloatingKeyboard.Position.RIGHT) {
                if (position == MyFloatingKeyboard.Position.LEFT) {
                    x = rect.left - getWidth() - distanceWithView;
                } else {
                    x = rect.right + distanceWithView;
                }
                y = rect.top + getAlignOffset(getHeight(), rect.height());
            } else {
                if (position == MyFloatingKeyboard.Position.BOTTOM) {
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

            final float spacingLeft = this.position == MyFloatingKeyboard.Position.RIGHT ? arrowHeight : 0;
            final float spacingTop = this.position == MyFloatingKeyboard.Position.BOTTOM ? arrowHeight : 0;
            final float spacingRight = this.position == MyFloatingKeyboard.Position.LEFT ? arrowHeight : 0;
            final float spacingBottom = this.position == MyFloatingKeyboard.Position.TOP ? arrowHeight : 0;

            final float left = spacingLeft + myRect.left;
            final float top = spacingTop + myRect.top;
            final float right = myRect.right - spacingRight;
            final float bottom = myRect.bottom - spacingBottom;
            final float centerX = viewRect.centerX() - getX();

            final float arrowSourceX = (Arrays.asList(MyFloatingKeyboard.Position.TOP, MyFloatingKeyboard.Position.BOTTOM).contains(this.position))
                    ? centerX + arrowSourceMargin
                    : centerX;
            final float arrowTargetX = (Arrays.asList(MyFloatingKeyboard.Position.TOP, MyFloatingKeyboard.Position.BOTTOM).contains(this.position))
                    ? centerX + arrowTargetMargin
                    : centerX;
            final float arrowSourceY = (Arrays.asList(MyFloatingKeyboard.Position.RIGHT, MyFloatingKeyboard.Position.LEFT).contains(this.position))
                    ? bottom / 2f - arrowSourceMargin
                    : bottom / 2f;
            final float arrowTargetY = (Arrays.asList(MyFloatingKeyboard.Position.RIGHT, MyFloatingKeyboard.Position.LEFT).contains(this.position))
                    ? bottom / 2f - arrowTargetMargin
                    : bottom / 2f;

            path.moveTo(left + topLeftDiameter / 2f, top);
            //LEFT, TOP

            if (position == MyFloatingKeyboard.Position.BOTTOM) {
                path.lineTo(arrowSourceX - arrowWidth, top);
                path.lineTo(arrowTargetX, myRect.top);
                path.lineTo(arrowSourceX + arrowWidth, top);
            }
            path.lineTo(right - topRightDiameter / 2f, top);

            path.quadTo(right, top, right, top + topRightDiameter / 2);
            //RIGHT, TOP

            if (position == MyFloatingKeyboard.Position.LEFT) {
                path.lineTo(right, arrowSourceY - arrowWidth);
                path.lineTo(myRect.right, arrowTargetY);
                path.lineTo(right, arrowSourceY + arrowWidth);
            }
            path.lineTo(right, bottom - bottomRightDiameter / 2);

            path.quadTo(right, bottom, right - bottomRightDiameter / 2, bottom);
            //RIGHT, BOTTOM

            if (position == MyFloatingKeyboard.Position.TOP) {
                path.lineTo(arrowSourceX + arrowWidth, bottom);
                path.lineTo(arrowTargetX, myRect.bottom);
                path.lineTo(arrowSourceX - arrowWidth, bottom);
            }
            path.lineTo(left + bottomLeftDiameter / 2, bottom);

            path.quadTo(left, bottom, left, bottom - bottomLeftDiameter / 2);
            //LEFT, BOTTOM

            if (position == MyFloatingKeyboard.Position.RIGHT) {
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
            if (position == MyFloatingKeyboard.Position.LEFT && getWidth() > rect.left) {
                layoutParams.width = rect.left - MARGIN_SCREEN_BORDER_TOOLTIP - distanceWithView;
                changed = true;
            } else if (position == MyFloatingKeyboard.Position.RIGHT && rect.right + getWidth() > screenWidth) {
                layoutParams.width = screenWidth - rect.right - MARGIN_SCREEN_BORDER_TOOLTIP - distanceWithView;
                changed = true;
            } else if (position == MyFloatingKeyboard.Position.TOP || position == MyFloatingKeyboard.Position.BOTTOM) {
                int adjustedLeft = rect.left;
                int adjustedRight = rect.right;

                if ((rect.centerX() + getWidth() / 2f) > screenWidth) {
                    float diff = (rect.centerX() + getWidth() / 2f) - screenWidth;

                    adjustedLeft -= diff;
                    adjustedRight -= diff;

                    setAlign(MyFloatingKeyboard.ALIGN.CENTER);
                    changed = true;
                } else if ((rect.centerX() - getWidth() / 2f) < 0) {
                    float diff = -(rect.centerX() - getWidth() / 2f);

                    adjustedLeft += diff;
                    adjustedRight += diff;

                    setAlign(MyFloatingKeyboard.ALIGN.CENTER);
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
            if (withShadow) {
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

    public interface OnListener {
        void onKey(CharSequence chr);
    }

}
