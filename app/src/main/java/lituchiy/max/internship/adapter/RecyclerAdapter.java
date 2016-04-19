package lituchiy.max.internship.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Appeal> mAppeals;

    public RecyclerAdapter(Context context) {
        mContext = context;
        mAppeals = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, int position) {
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
        holder.mAppealCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Appeal.APPEALITEM, appeal);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppeals.size();
    }

    public void setAppeals(List<Appeal> appeals) {
        mAppeals = appeals;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
