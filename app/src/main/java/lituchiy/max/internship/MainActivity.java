package lituchiy.max.internship;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    public final static String[] imageUrls = new String[]{
            "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg",
            "https://lh5.googleusercontent.com/-0BDXkYmckbo/URquhKFW84I/AAAAAAAAAbs/ogQtHCTk2JQ/s1024/Closed%252520Door.jpg",
            "https://lh3.googleusercontent.com/-PyggXXZRykM/URquh-kVvoI/AAAAAAAAAbs/hFtDwhtrHHQ/s1024/Colorado%252520River%252520Sunset.jpg",
            "https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s1024/Yosemite%252520Tree.jpg",
    };

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    @OnClick({ R.id.createdTv,
            R.id.createdDateTv,
            R.id.titleTv,
            R.id.statusTv,
            R.id.registeredTv ,
            R.id.registeredDateTv,
            R.id.assignedTv,
            R.id.assignedDateTv,
            R.id.responsibleTv,
            R.id.responsibleNameTv,
            R.id.descriptionTv })
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
//        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow));
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

        ImageAdapter imageAdapter = new ImageAdapter(Arrays.asList(imageUrls), this);
        mRecyclerView.setAdapter(imageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }



    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
        private List<String> mImageList;
        private Context mContext;

        public class ViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.imageView) public ImageView mImageView;

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        public ImageAdapter(List<String> mImageList, Context context) {
            mContext = context;
            this.mImageList = mImageList;
        }

        @Override
        public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            Picasso.with(mContext).load(mImageList.get(position)).error(R.drawable.marshmallow).into(holder.mImageView);

            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mImageList.size();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
