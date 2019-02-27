package quangnam.com.sample.util

import android.content.Context
import android.content.Intent

import quangnam.com.sample.module.test.activity.TestActivity
import quangnam.com.sample.module.test.activity.ViewPagerTestActivity

/**
 * Created by quangnam on 12/12/17.
 * Application Navigator to navigate between activities.
 * Project Sample
 */

object Navigator {

    /**
     * Test case navigate to [TestActivity]
     */
    fun navigateTestActivity(context: Context) {
        val intent = Intent(context, TestActivity::class.java)
        context.startActivity(intent)
    }

    /**
     * Test case navigate to [ViewPagerTestActivity].
     * Show activity with fragment in viewpager
     */
    fun navigateViewPagerActivity(context: Context) {
        val intent = Intent(context, ViewPagerTestActivity::class.java)
        context.startActivity(intent)
    }
}
