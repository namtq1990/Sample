package quangnam.com.sample.ui.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View

/**
 * Created by quangnam on 9/9/17.
 * Project sosokan-android
 */

class ViewPager : android.support.v4.view.ViewPager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = heightMeasureSpec
        var height = Math.max(View.MeasureSpec.getSize(heightMeasureSpec), measuredHeight)         // Shouldn't change height

        val childMeasureHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as ViewPager.LayoutParams

            if (!lp.isDecor) {
                child.measure(widthMeasureSpec, childMeasureHeight)
                if (child.measuredHeight > height) {
                    height = child.measuredHeight
                }
            }
        }
        newHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)

    }
}
