package quangnam.com.base.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by quangnam on 3/24/18.
 * Project Sample
 */

public class AppTextView extends AppCompatTextView implements ITextViewCompat {

    private ITextViewCompat mViewImpl;

    public AppTextView(Context context) {
        this(context, null);
    }

    public AppTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public AppTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewImpl = new TextViewCompatImpl(getContext(), this, attrs);
    }

    @Override
    public Drawable getLeftDrawable() {
        return mViewImpl.getLeftDrawable();
    }

    @Override
    public Drawable getRightDrawable() {
        return mViewImpl.getRightDrawable();
    }

    @Override
    public Drawable getTopDrawable() {
        return mViewImpl.getTopDrawable();
    }

    @Override
    public Drawable getBottomDrawable() {
        return mViewImpl.getBottomDrawable();
    }
}
