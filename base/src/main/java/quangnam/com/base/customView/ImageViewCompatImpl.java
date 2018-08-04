package quangnam.com.base.customView;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;

class ImageViewCompatImpl implements IImageViewCompat {

    private final IImageViewCompat mHost;

    ImageViewCompatImpl(IImageViewCompat host) {
        mHost = host;
    }

    @Override
    public boolean getAdjustViewBounds() {
        return mHost.getAdjustViewBounds();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable mDrawable = getDrawable();
        if (mDrawable == null) {
            originMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        if (mHost.getAdjustViewBounds()) {
            int mDrawableWidth = mDrawable.getIntrinsicWidth();
            int mDrawableHeight = mDrawable.getIntrinsicHeight();
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);

            if (heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY) {
                // Fixed Height & Adjustable Width
                int height = heightSize;
                int width = height * mDrawableWidth / mDrawableHeight;
                if (isInScrollingContainer())
                    setImageMeasuredSize(width, height);
                else
                    setImageMeasuredSize(Math.min(width, widthSize), Math.min(height, heightSize));
            } else if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                // Fixed Width & Adjustable Height
                int width = widthSize;
                int height = width * mDrawableHeight / mDrawableWidth;
                if (isInScrollingContainer())
                    setImageMeasuredSize(width, height);
                else
                    setImageMeasuredSize(Math.min(width, widthSize), Math.min(height, heightSize));
            } else {
                originMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            originMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void originMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHost.originMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public Drawable getDrawable() {
        return mHost.getDrawable();
    }

    @Override
    public void setImageMeasuredSize(int width, int height) {
        mHost.setImageMeasuredSize(width, height);
    }

    private boolean isInScrollingContainer() {
        ViewParent p = ((View) mHost).getParent();
        while (p != null && p instanceof ViewGroup) {
            if (((ViewGroup) p).shouldDelayChildPressedState()) {
                return true;
            }
            p = p.getParent();
        }
        return false;
    }

}
