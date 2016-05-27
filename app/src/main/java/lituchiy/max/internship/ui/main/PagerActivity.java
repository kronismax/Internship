package lituchiy.max.internship.ui.main;

import android.content.Intent;
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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.PagerAdapter;
import lituchiy.max.internship.data.RealmController;
import lituchiy.max.internship.data.AccessTokenRealm;
import lituchiy.max.internship.data.ProfileRealm;
import lituchiy.max.internship.ui.profile.ProfileActivity;
import lituchiy.max.internship.utils.Constants;

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

    CallbackManager callbackManager;
    LoginManager loginManager;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        mRealm = RealmController.with(this).getRealm();

        setToolbar();

        setPager();

        setOnClick();

    }

    private void setOnClick() {
        mFooterLinks.setMovementMethod(LinkMovementMethod.getInstance());

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
                } else if (item.getItemId() == R.id.drawer_login) {
                    loginFacebook();
                    item.setChecked(true);
                } else if (item.getItemId() == R.id.drawer_profile) {
                    if (Profile.getCurrentProfile() != null) {
                        startActivity(new Intent(PagerActivity.this, ProfileActivity.class));
                    } else {
                        Toast.makeText(PagerActivity.this,
                                R.string.facebook_login,
                                Toast.LENGTH_SHORT).show();
                    }
                    item.setChecked(true);
                } else {
                    item.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
                return true;
            }
        });
    }

    private void loginFacebook() {
        callbackManager = CallbackManager.Factory.create();

        loginManager = LoginManager.getInstance();

        loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getFacebookProfile();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });
        Collection<String> permissions = Collections.singletonList(
                Constants.FACEBOOK_PERMISSIONS_LIST);
        loginManager.logInWithReadPermissions(this, permissions);
    }

    private void getFacebookProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Profile.fetchProfileForCurrentAccessToken();
                        Profile profile = Profile.getCurrentProfile();

                        AccessTokenRealm accessTokenRealm = new AccessTokenRealm();

                        accessTokenRealm.setToken(AccessToken.getCurrentAccessToken().getToken());
                        ProfileRealm profileRealm = new ProfileRealm();
                        profileRealm.setName(profile.getName());
                        profileRealm.setLink(profile.getLinkUri().toString());
                        profileRealm.setProfilePicture(String.valueOf(profile.getProfilePictureUri(
                                Constants.PROFILE_IMAGE_SIZE, Constants.PROFILE_IMAGE_SIZE)));

                        mRealm.beginTransaction();
                        mRealm.copyToRealmOrUpdate(accessTokenRealm);
                        mRealm.copyToRealmOrUpdate(profileRealm);
                        mRealm.commitTransaction();
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString(Constants.FACEBOOK_QUERY, Constants.FACEBOOK_PERMISSIONS);
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void setPager() {
        PagerAdapter mPagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(R.string.app_name));
        }

        if (mAppBar != null) {
            ViewCompat.setElevation(mAppBar, 0f);
        }
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
                PopupMenu popupMenu = new PopupMenu(PagerActivity.this,
                        findViewById(R.id.menu_action_sort));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
