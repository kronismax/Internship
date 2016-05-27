package lituchiy.max.internship.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import lituchiy.max.internship.R;
import lituchiy.max.internship.ui.main.MainFragment;
import lituchiy.max.internship.utils.Constants;

public class PagerAdapter extends FragmentPagerAdapter {

    public static final int TAB_COUNT = 3;
    public static final int FRAGMENT_IN_PROGRESS = 0;
    public static final int FRAGMENT_DONE = 1;
    public static final int FRAGMENT_PENDING = 2;

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) { //[Comment] Wrong formatting
            case FRAGMENT_IN_PROGRESS: //[Comment] Hardcode
                return MainFragment.newInstance(Constants.QUERY_IN_PROGRESS, Constants.STATE_IN_PROGRESS);
            case FRAGMENT_DONE:
                return MainFragment.newInstance(Constants.QUERY_DONE, Constants.STATE_DONE);
            case FRAGMENT_PENDING:
                return MainFragment.newInstance(Constants.QUERY_PENDING, Constants.STATE_PENDING);
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
            case FRAGMENT_IN_PROGRESS: //[Comment] Hardcode
                return mContext.getString(R.string.in_progress);
            case FRAGMENT_DONE:
                return mContext.getString(R.string.completed);
            case FRAGMENT_PENDING:
                return mContext.getString(R.string.waiting);
        }
        return super.getPageTitle(position);
    }
}
