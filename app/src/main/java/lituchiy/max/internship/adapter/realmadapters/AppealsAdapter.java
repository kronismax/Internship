package lituchiy.max.internship.adapter.realmadapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.data.AppealRealm;
import lituchiy.max.internship.ui.detail.DetailActivity;
import lituchiy.max.internship.utils.Utils;

public class AppealsAdapter extends RealmRecyclerViewAdapter<AppealRealm> {

    final Context mContext;

    public AppealsAdapter(Context context) {

        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final AppealRealm appeal = getItem(position);
        final ViewHolder holder = (ViewHolder) viewHolder;

        holder.mAppealTypeImageView.setImageResource(R.drawable.ic_doc);
        holder.mAppealTypeTextView.setText(appeal.getTitle());
        holder.mAppealAddressTextView.setText(appeal.getAddress());
        holder.mAppealLikesTextView.setText(String.valueOf(appeal.getLikes()));
        holder.mAppealBeginDateDiffTextView.setText(Utils.millisecondsToString(
                mContext, appeal.getCreatedDate()));
        holder.mAppealEndDateTextView.setText((Utils.millisecondsToString(
                mContext, appeal.getCreatedDate())));
        holder.mAppealCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(AppealRealm.APPEALITEM, appeal.getId());
                mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
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
        TextView mAppealLikesTextView;
        @Bind(R.id.appeal_item_card_view)
        CardView mAppealCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}