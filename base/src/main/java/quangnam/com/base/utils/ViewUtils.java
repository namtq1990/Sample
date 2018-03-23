package quangnam.com.base.utils;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * Created by quangnam on 8/31/17.
 * Project sosokan-android
 */

public class ViewUtils {

    @SuppressWarnings("unused")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field" + e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode" + e);
        }
    }

    @SuppressWarnings("unused")
    public static int dpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

    @SuppressWarnings("unused")
    public static int pxToDp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density);
    }

    public static boolean isTouchInside(final MotionEvent motionEvent, View view) {
        final int[] location = new int[2];
        view.getLocationOnScreen(location);

        return motionEvent.getRawX() > location[0]
                && motionEvent.getRawY() > location[1]
                && motionEvent.getRawX() < location[0] + view.getWidth()
                && motionEvent.getRawY() < location[1] + view.getHeight();
    }

    public static String getViewPagerFragmentTag(int viewID, int id) {
        return String.format(Locale.getDefault(), "android:switcher:%d:%d", viewID, id);
    }

    public static String getViewPagerFragmentTag(ViewPager pager, int position) {
        return getViewPagerFragmentTag(pager.getId(), position);
    }
}
