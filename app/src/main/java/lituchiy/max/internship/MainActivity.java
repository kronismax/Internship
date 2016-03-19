package lituchiy.max.internship;

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
import lituchiy.max.internship.adapter.ImageAdapter;

//[Comment] Wrong toolbar and status bar color
public class MainActivity extends AppCompatActivity {
    public final static String[] imageUrls = new String[]{
            "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg",
            "https://lh5.googleusercontent.com/-0BDXkYmckbo/URquhKFW84I/AAAAAAAAAbs/ogQtHCTk2JQ/s1024/Closed%252520Door.jpg",
            "https://lh3.googleusercontent.com/-PyggXXZRykM/URquh-kVvoI/AAAAAAAAAbs/hFtDwhtrHHQ/s1024/Colorado%252520River%252520Sunset.jpg",
            "https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s1024/Yosemite%252520Tree.jpg",
    }; //[Comment] Hardcode. Move this strings into <string-array /> DONE

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView; //[Comment] Wrong formatter DONE

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.toolbarTitle);
//        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow)); [Comment] Don't leave commented code in production version
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    //[Comment] ImageAdapter shouldn't be internal class DONE

}
