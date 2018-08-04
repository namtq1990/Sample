package quangnam.com.base.customView;

import android.graphics.drawable.Drawable;

public interface IImageViewCompat {
    boolean getAdjustViewBounds();
    void onMeasure(int widthMeasureSpec, int heightMeasureSpec);
    void originMeasure(int widthMeasureSpec, int heightMeasureSpec);

    Drawable getDrawable();

    void setImageMeasuredSize(int width, int height);
}
