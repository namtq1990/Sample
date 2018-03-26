package quangnam.com.base.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

/**
 * Created by quangnam on 3/24/18.
 * Project Sample
 */

public class AppAutoCompleteTextView extends AppCompatAutoCompleteTextView implements ITextViewCompat {

    private ITextViewCompat mTextViewImpl;

    public AppAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.autoCompleteTextViewStyle);
    }

    public AppAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
