package ptt.vn.icaremobileapp.circle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import static android.widget.ImageView.ScaleType.CENTER_CROP;
import static android.widget.ImageView.ScaleType.CENTER_INSIDE;

/**
 * Created by kingpes on 9/11/18.
 */

public class MyCircle extends android.support.v7.widget.AppCompatImageView {
    // Properties
    private float borderWidth;
    private int canvasSize;
    private ColorFilter colorFilter;

    // Object used to draw
    private Bitmap image;
    private Drawable drawable;
    private Paint paint;
    private Paint paintBorder;
    private Paint paintBackground;

    //region Constructor & Init Method
    public MyCircle(final Context context) {
        this(context, null);
    }

    public MyCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Init paint
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setAntiAlias(true);

        paintBackground = new Paint();
        paintBackground.setAntiAlias(true);

        //Init Border
        setBorderWidth(3);
        setBorderColor(Color.WHITE);
        setBackgroundColor(Color.WHITE);

    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        requestLayout();
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);
        invalidate();
    }

    public void setBackgroundColor(int backgroundColor) {
        if (paintBackground != null)
            paintBackground.setColor(backgroundColor);
        invalidate();
    }


    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.colorFilter == colorFilter)
            return;
        this.colorFilter = colorFilter;
        drawable = null;
        invalidate();
    }

    @Override
    public ScaleType getScaleType() {
        ScaleType currentScaleType = super.getScaleType();
        return currentScaleType == null || currentScaleType != CENTER_INSIDE ? CENTER_CROP : currentScaleType;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != CENTER_CROP && scaleType != CENTER_INSIDE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported. " +
                    "Just ScaleType.CENTER_CROP & ScaleType.CENTER_INSIDE are available for this library.", scaleType));
        } else {
            super.setScaleType(scaleType);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        loadBitmap();

        if (image == null)
            return;

        if (!isInEditMode()) {
            canvasSize = Math.min(canvas.getWidth(), canvas.getHeight());
        }

        int circleCenter = (int) (canvasSize - (borderWidth * 2)) / 2;
        float shadowRadius = 1;
        float margeWithShadowRadius = shadowRadius * 2;

        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - margeWithShadowRadius, paintBorder);
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - margeWithShadowRadius, paintBackground);
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - margeWithShadowRadius, paint);
    }

    private void loadBitmap() {
        if (drawable == getDrawable())
            return;

        drawable = getDrawable();
        image = drawableToBitmap(drawable);
        updateShader();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasSize = Math.min(w, h);
        if (image != null)
            updateShader();
    }

    private void updateShader() {
        if (image == null)
            return;

        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        float scale = 0;
        float dx = 0;
        float dy = 0;

        switch (getScaleType()) {
            case CENTER_CROP:
                if (image.getWidth() * getHeight() > getWidth() * image.getHeight()) {
                    scale = getHeight() / (float) image.getHeight();
                    dx = (getWidth() - image.getWidth() * scale) * 0.5f;
                } else {
                    scale = getWidth() / (float) image.getWidth();
                    dy = (getHeight() - image.getHeight() * scale) * 0.5f;
                }
                break;
            case CENTER_INSIDE:
                if (image.getWidth() * getHeight() < getWidth() * image.getHeight()) {
                    scale = getHeight() / (float) image.getHeight();
                    dx = (getWidth() - image.getWidth() * scale) * 0.5f;
                } else {
                    scale = getWidth() / (float) image.getWidth();
                    dy = (getHeight() - image.getHeight() * scale) * 0.5f;
                }
                break;
        }

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postTranslate(dx, dy);
        shader.setLocalMatrix(matrix);

        paint.setShader(shader);

        paint.setColorFilter(colorFilter);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else {
            result = canvasSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else {
            result = canvasSize;
        }

        return result + 2;
    }
}
