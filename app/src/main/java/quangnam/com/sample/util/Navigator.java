package quangnam.com.sample.util;

import android.content.Context;
import android.content.Intent;

import quangnam.com.sample.ui.activity.test.TestActivity;
import quangnam.com.sample.ui.activity.test.ViewPagerTestActivity;

/**
 * Created by quangnam on 12/12/17.
 * Application Navigator to navigate between activities.
 * Project Sample
 */

public final class Navigator {

    private Navigator() {}

    /**
     * Test case navigate to {@link TestActivity}
     */
    public static void navigateTestActivity(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    /**
     * Test case navigate to {@link ViewPagerTestActivity}.
     * Show activity with fragment in viewpager
     */
    public static void navigateViewPagerActivity(Context context) {
        Intent intent = new Intent(context, ViewPagerTestActivity.class);
        context.startActivity(intent);
    }
}
