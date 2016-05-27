package lituchiy.max.internship.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.ImageAdapter;
import lituchiy.max.internship.data.AppealPhotoRealm;
import lituchiy.max.internship.data.AppealRealm;
import lituchiy.max.internship.data.RealmController;
import lituchiy.max.internship.utils.Utils;

//[Comment] Wrong toolbar and status bar color
public class DetailActivity extends AppCompatActivity {

    //[Comment] Hardcode. Move this strings into <string-array /> DONE

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView; //[Comment] Wrong formatter DONE
    @Bind(R.id.titleTv)
    TextView mAppealTitle;
    @Bind(R.id.createdDateTv)
    TextView mAppealCreatedDate;
    @Bind(R.id.registeredDateTv)
    TextView mAppealRegisteredDate;
    @Bind(R.id.assignedDateTv)
    TextView mAppealAssignedDate;
    @Bind(R.id.responsibleNameTv)
    TextView mAppealResponsible;
    @Bind(R.id.descriptionTv)
    TextView mAppealDescription;
    @Bind(R.id.statusTv)
    TextView mAppealStatus;


    @OnClick({R.id.createdTv,
            R.id.createdDateTv,
            R.id.titleTv,
            R.id.statusTv,
            R.id.registeredTv,
            R.id.registeredDateTv,
            R.id.assignedTv,
            R.id.assignedDateTv,
            R.id.responsibleTv,
            R.id.responsibleNameTv,
            R.id.descriptionTv})
    public void showToast(View view) {
        Toast.makeText(this,
                ((TextView) view).getText(), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setDataToView(getAppeal());

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setFocusable(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setDataToView(AppealRealm appeal) {
        mAppealTitle.setText(appeal.getTitle());
        mAppealCreatedDate.setText(Utils.millisecondsToString(this, appeal.getCreatedDate()));
        mAppealRegisteredDate.setText(Utils.millisecondsToString(this, appeal.getCreatedDate()));
        mAppealAssignedDate.setText(Utils.millisecondsToString(this, appeal.getAssignedDate()));
        mAppealResponsible.setText(appeal.getResponsible());
        mAppealDescription.setText(appeal.getDescription());
        mAppealStatus.setText(appeal.getStateName());
        if (getSupportActionBar() != null) {
            String toolbarTitle = getString(R.string.appeal_id) + " " + appeal.getId();
            getSupportActionBar().setTitle(toolbarTitle);
        }

        ArrayList<String> list = new ArrayList<>();
        for (AppealPhotoRealm appealPhotoRealm : appeal.getPhoto()) {
            list.add(appealPhotoRealm.getImageUrl());
        }
        ImageAdapter imageAdapter = new ImageAdapter(list, this);
        mRecyclerView.setAdapter(imageAdapter);
    }

    private AppealRealm getAppeal() {
        AppealRealm appeal = null;
        if (getIntent().hasExtra(AppealRealm.APPEALITEM)) {
            appeal = RealmController.with(this).getAppeal(
                    getIntent().getExtras().getInt(AppealRealm.APPEALITEM));
        }
        return appeal;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

}
