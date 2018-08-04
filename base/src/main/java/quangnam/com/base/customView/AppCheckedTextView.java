package quangnam.com.base.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

public class AppCheckedTextView extends AppCompatCheckedTextView implements ITextViewCompat {

    private ITextViewCompat mTextViewImpl;

    public AppCheckedTextView(Context context) {
        this(context, null);
    }

    public AppCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.checkedTextViewStyle);
    }

    public AppCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextViewImpl = new TextViewCompatImpl(context, this, attrs);
    }

    @Override
    public Drawable getLeftDrawable() {
        return mTextViewImpl.getLeftDrawable();
    }

    @Override
    public Drawable getRightDrawable() {
        return mTextViewImpl.getRightDrawable();
    }

    @Override
    public Drawable getTopDrawable() {
        return mTextViewImpl.getTopDrawable();
    }

    @Override
    public Drawable getBottomDrawable() {
        return mTextViewImpl.getBottomDrawable();
    }
}
