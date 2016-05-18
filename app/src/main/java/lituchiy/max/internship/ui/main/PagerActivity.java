package lituchiy.max.internship.view;

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
import android.util.Log;
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
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.PagerAdapter;
import lituchiy.max.internship.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PagerActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "Debug";
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
    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);


        setToolbar();

//        retrofit();

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
                        Log.d(TAG, "onSuccess: " + loginResult.getAccessToken().getToken());
                        accessToken = loginResult.getAccessToken();
                        getFacebookProfile();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });

        Collection<String> permissions = Collections.singletonList("public_profile");

        loginManager.logInWithReadPermissions(this, permissions);
    }

    private void getFacebookProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.d(TAG, "onCompleted() called with: " + "object = [" + object + "], response = [" + response + "]");
                        Profile.fetchProfileForCurrentAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        Log.d(TAG, "onCompleted() called with: " + "profile = [" + profile.getProfilePictureUri(100, 100));
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,picture");
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
            supportActionBar.setTitle(getString(R.string.drawer_item_all));
        }

        if (mAppBar != null) {
            ViewCompat.setElevation(mAppBar, 0f);
        }
    }

    private void retrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://dev-contact.yalantis.com/rest/v1/")
                .client(client)
                .build();

        ApiService weatherService = retrofit.create(ApiService.class);
//        Observable<List<AppealNew>> call = weatherService.getAppeal("state=0,9,5,7,8");

//        Call<List<AppealNew>> call = weatherService.getAppeal("");
//        call.enqueue(new Callback<List<AppealNew>>() {
//            @Override
//            public void onResponse(Call<List<AppealNew>> call, Response<List<AppealNew>> response) {
//                for (AppealNew appeal : response.body()) {
//                    Log.d(TAG, "onResponse: "+appeal.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<AppealNew>> call, Throwable t) {
//
//            }
//        });

//        call.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<AppealNew>>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
//                    }
//
//                    @Override
//                    public void onNext(List<AppealNew> appealNew) {
//                        for (AppealNew appeal : appealNew) {
//                            Log.d(TAG, "onResponse: " + appeal.getTitle());
//                        }
//                    }
//
//                });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
