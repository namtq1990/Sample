package quangnam.com.base.customView;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

public class AppImageButton extends AppCompatImageButton implements IImageViewCompat {

    private ImageViewCompatImpl mImpl;

    public AppImageButton(Context context) {
        this(context, null);
    }

    public AppImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.imageButtonStyle);
    }

    public AppImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImpl = new ImageViewCompatImpl(this);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mImpl.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void originMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setImageMeasuredSize(int width, int height) {
        setMeasuredDimension(width, height);
    }
}
