package quangnam.com.base.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class AppEditText extends AppCompatEditText implements ITextViewCompat {

    private ITextViewCompat mViewImpl;

    public AppEditText(Context context) {
        this(context, null);
    }

    public AppEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public AppEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
