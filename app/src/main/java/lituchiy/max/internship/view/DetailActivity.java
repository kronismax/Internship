package lituchiy.max.internship.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.ImageAdapter;
import lituchiy.max.internship.data.Appeal;
import lituchiy.max.internship.utils.Utils;

//[Comment] Wrong toolbar and status bar color
public class DetailActivity extends AppCompatActivity {

    //[Comment] Hardcode. Move this strings into <string-array /> DONE

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView; //[Comment] Wrong formatter DONE
    @Bind(R.id.titleTv)
    TextView titleTv;
    @Bind(R.id.createdDateTv)
    TextView createdTv;
    @Bind(R.id.registeredDateTv)
    TextView registeredTv;
    @Bind(R.id.assignedDateTv)
    TextView assignedTv;
    @Bind(R.id.responsibleNameTv)
    TextView responsibleTv;


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
            getSupportActionBar().setTitle(R.string.toolbarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Appeal appeal = getAppeal();
        setDataToView(appeal);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setFocusable(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        ImageAdapter imageAdapter = new ImageAdapter(Arrays.asList(getResources().getStringArray(R.array.image_urls)), this);
        mRecyclerView.setAdapter(imageAdapter);
    }

    private void setDataToView(Appeal appeal) {
        Appeal.AppealType type = appeal.getType();
        String typeString = getString(R.string.other);
        switch (type) {
            case BUILDING:
                typeString = getString(R.string.type_building);
                break;
            case UTILITY:
                typeString = getString(R.string.utilities);
                break;
        }
        titleTv.setText(typeString);
        createdTv.setText(Utils.millisecondsToString(this, appeal.getCreated()));
        registeredTv.setText(Utils.millisecondsToString(this, appeal.getRegistered()));
        assignedTv.setText(Utils.millisecondsToString(this, appeal.getAssigned()));
        responsibleTv.setText(appeal.getResponsible());

    }

    private Appeal getAppeal() {
        Appeal appeal = null;
        if (getIntent().hasExtra(Appeal.APPEALITEM)) {
            appeal = getIntent().getParcelableExtra(Appeal.APPEALITEM);
        }
        return appeal;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    //[Comment] ImageAdapter shouldn't be internal class DONE

}
