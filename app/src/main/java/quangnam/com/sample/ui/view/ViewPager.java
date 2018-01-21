package quangnam.com.sample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by quangnam on 9/9/17.
 * Project sosokan-android
 */

public class ViewPager extends android.support.v4.view.ViewPager {

    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = Math.max(MeasureSpec.getSize(heightMeasureSpec), getMeasuredHeight());         // Shouldn't change height

        int childMeasureHeight = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        for (int i = 0;i < getChildCount();i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (!lp.isDecor) {
                child.measure(widthMeasureSpec, childMeasureHeight);
                if (child.getMeasuredHeight() > height) {
                    height = child.getMeasuredHeight();
                }
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
