package quangnam.com.base.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.TextView;

import quangnam.com.base.R;

/**
 * Created by quangnam on 3/24/18.
 * Project Sample
 */

public class TextViewCompatImpl implements ITextViewCompat {

    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;
    private Drawable mDrawableTop;
    private Drawable mDrawableBottom;

    private TextView mHost;

    TextViewCompatImpl(Context context, TextView tv, AttributeSet attrs) {
        mHost = tv;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppTextView);
        try {
            int drawableLeftId = typedArray.getResourceId(R.styleable.AppTextView_app_leftDrawable, 0);
            if (drawableLeftId > 0) {
                mDrawableLeft = AppCompatResources.getDrawable(context, drawableLeftId);
            }

            int drawableRightId = typedArray.getResourceId(R.styleable.AppTextView_app_rightDrawable, 0);
            if (drawableRightId > 0) {
                mDrawableRight = AppCompatResources.getDrawable(context, drawableRightId);
            }

            int drawableTopId = typedArray.getResourceId(R.styleable.AppButton_app_topDrawable, 0);
            if (drawableTopId > 0) {
                mDrawableTop = AppCompatResources.getDrawable(context, drawableTopId);
            }

            int drawableBottomId = typedArray.getResourceId(R.styleable.AppButton_app_bottomDrawable, 0);
            if (drawableBottomId > 0) {
                mDrawableBottom = AppCompatResources.getDrawable(context, drawableBottomId);;
            }
        } finally {
            typedArray.recycle();
        }

        mHost.setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
    }

    @Override
    public Drawable getLeftDrawable() {
        return mDrawableLeft;
    }

    @Override
    public Drawable getRightDrawable() {
        return mDrawableRight;
    }

    @Override
    public Drawable getTopDrawable() {
        return mDrawableTop;
    }

    @Override
    public Drawable getBottomDrawable() {
        return mDrawableBottom;
    }
}
