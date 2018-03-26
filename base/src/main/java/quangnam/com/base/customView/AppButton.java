package quangnam.com.base.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by quangnam on 3/24/18.
 * Project Sample
 */

public class AppButton extends AppCompatButton implements ITextViewCompat {

    private ITextViewCompat mTextViewCompatImpl;

    public AppButton(Context context) {
        this(context, null);
    }

    public AppButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public AppButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextViewCompatImpl = new TextViewCompatImpl(context, this, attrs);
    }

    @Override
    public Drawable getLeftDrawable() {
        return mTextViewCompatImpl.getLeftDrawable();
    }

    @Override
    public Drawable getRightDrawable() {
        return mTextViewCompatImpl.getRightDrawable();
    }

    @Override
    public Drawable getTopDrawable() {
        return mTextViewCompatImpl.getTopDrawable();
    }

    @Override
    public Drawable getBottomDrawable() {
        return mTextViewCompatImpl.getBottomDrawable();
    }
}
