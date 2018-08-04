package quangnam.com.base.customView;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class AppImageView extends AppCompatImageView implements IImageViewCompat {

    private ImageViewCompatImpl mImpl;

    public AppImageView(Context context) {
        this(context, null);
    }

    public AppImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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