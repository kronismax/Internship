package lituchiy.max.internship.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import lituchiy.max.internship.R;
import lituchiy.max.internship.view.ListFragment;
import lituchiy.max.internship.view.RecyclerFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    public static final int TAB_COUNT = 3;

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        { //[Comment] Wrong formatting
            case 0: //[Comment] Hardcode
            case 1:
                return RecyclerFragment.newInstance();
            case 2:
                return ListFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: //[Comment] Hardcode
                return mContext.getString(R.string.in_progress);
            case 1:
                return mContext.getString(R.string.completed);
            case 2:
                return mContext.getString(R.string.waiting);
        }
        return super.getPageTitle(position);
    }
}
