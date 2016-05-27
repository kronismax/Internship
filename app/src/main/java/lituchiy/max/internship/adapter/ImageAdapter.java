package lituchiy.max.internship.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.utils.Constants;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<String> imageList;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageView)
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ImageAdapter(List<String> imageList, Context context) {
        mContext = context;
        this.imageList = imageList; // [Comment] mImageList = imageList
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Picasso.with(mContext)
                .load(Constants.QUERY_IMAGE_URL + imageList.get(position))
                .error(R.drawable.marshmallow)
                .resize(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT)
                .onlyScaleDown()
                .centerInside()
                .into(holder.mImageView);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}