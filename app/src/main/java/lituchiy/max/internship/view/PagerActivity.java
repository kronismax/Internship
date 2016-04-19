package lituchiy.max.internship.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.PagerAdapter;

public class PagerActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Bind(R.id.layout_drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.drawer_view_navigation)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_footer_links)
    TextView mFooterLinks;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppBar;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.fab)
    FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(R.string.drawer_item_all));
        }

        PagerAdapter mPagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());

        mTabLayout.setupWithViewPager(mViewPager);

        mFooterLinks.setMovementMethod(LinkMovementMethod.getInstance());

        if (mAppBar != null) {
            ViewCompat.setElevation(mAppBar, 0f);
        }

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PagerActivity.this, "fab", Toast.LENGTH_SHORT).show();
            }
        });

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.drawer_map) {
                    item.setChecked(false);
                    mDrawerLayout.closeDrawers();
                } else {
                    item.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_action_sort:
                PopupMenu popupMenu = new PopupMenu(PagerActivity.this, findViewById(R.id.menu_action_sort));
                popupMenu.getMenuInflater().inflate(R.menu.menu_filter_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.show();
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.getMenu().getItem(0).setChecked(true);
    }
}
