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
    public static final int FRAGMENT_PROGRESS = 0;
    public static final int FRAGMENT_COMPLETED = 1;
    public static final int FRAGMENT_WAITING = 2;

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) { //[Comment] Wrong formatting
            case FRAGMENT_PROGRESS: //[Comment] Hardcode
            case FRAGMENT_COMPLETED:
                return RecyclerFragment.newInstance();
            case FRAGMENT_WAITING:
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
            case FRAGMENT_PROGRESS: //[Comment] Hardcode
                return mContext.getString(R.string.in_progress);
            case FRAGMENT_COMPLETED:
                return mContext.getString(R.string.completed);
            case FRAGMENT_WAITING:
                return mContext.getString(R.string.waiting);
        }
        return super.getPageTitle(position);
    }
}
