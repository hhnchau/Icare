package ptt.vn.icaremobileapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingpes on 8/31/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listFragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }


    public void addFragment(Fragment fragment){
        listFragment.add(fragment);
    }

}
