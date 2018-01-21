package quangnam.com.sample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnam on 8/31/17.
 * Project sosokan-android
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
  private final List<Fragment> mFragmentList = new ArrayList<>();
  private final List<String> mFragmentTitleList = new ArrayList<>();

  public ViewPagerFragmentAdapter(FragmentManager manager) {
    super(manager);
  }

  @Override
  public Fragment getItem(int position) {
    return mFragmentList.size() > 0 ? mFragmentList.get(position) : null;
  }

  @Override
  public int getCount() {
    return mFragmentList.size();
  }

  public void addFragment(Fragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

}