package lituchiy.max.internship.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.data.Appeal;
import lituchiy.max.internship.utils.Utils;
import lituchiy.max.internship.view.DetailActivity;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Appeal> mAppeals;

    public ListAdapter(Context context) {
        mContext = context; //[Comment] Why do use "this" ? You know what does it mean in java?
        mAppeals = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mAppeals.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppeals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
//        View view = view; //[Comment] You don't need view
        ViewHolder holder;
        if(view != null) {
            holder  = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        final Appeal appeal = mAppeals.get(position);

        holder.mAppealTypeImageView.setImageResource(R.drawable.ic_doc);

        switch (appeal.getType()) {
            case BUILDING:
                holder.mAppealTypeTextView.setText(R.string.type_building);
                break;
            case UTILITY:
                holder.mAppealTypeTextView.setText(R.string.utilities);
                break;
            case OTHER:
                holder.mAppealTypeTextView.setText(R.string.other);
                break;
            default:
                break;
        }
        holder.mAppealAddressTextView.setText(appeal.getAddress());
        holder.mAppealBeginDateDiffTextView.setText(Utils.getRelativeTimeSpanString(appeal.getRegistered()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        holder.mAppealEndDateTextView.setText(dateFormat.format(new Date(appeal.getRegistered())));
        holder.mLikesTextView.setText(String.valueOf(appeal.getLikes()));
        holder.mAppealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Appeal.APPEALITEM, appeal);
                mContext.startActivity(intent);
                Toast.makeText(mContext, "ListView OnClick", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    class ViewHolder {

        @Bind(R.id.appeal_type_icon_image_view)
        ImageView mAppealTypeImageView;
        @Bind(R.id.appeal_type_text_view)
        TextView mAppealTypeTextView;
        @Bind(R.id.appeal_address_text_view)
        TextView mAppealAddressTextView;
        @Bind(R.id.appeal_registered_date)
        TextView mAppealEndDateTextView;
        @Bind(R.id.appeal_days_left_text_view)
        TextView mAppealBeginDateDiffTextView;
        @Bind(R.id.likes_text_view)
        TextView mLikesTextView;
        @Bind(R.id.appeal_item_card_view)
        CardView mAppealCardView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    public void setAppeals(List<Appeal> appeals) {
        mAppeals = appeals;
        notifyDataSetChanged();
    }

}
