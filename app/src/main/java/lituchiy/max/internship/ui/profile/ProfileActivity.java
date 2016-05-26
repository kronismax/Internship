package lituchiy.max.internship.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import lituchiy.max.internship.R;
import lituchiy.max.internship.data.RealmController;
import lituchiy.max.internship.data.ProfileRealm;

public class ProfileActivity extends AppCompatActivity {


    @Bind(R.id.profile_image_view)
    ImageView mProfileImageView;
    @Bind(R.id.profile_name_text_view)
    TextView mProfileTextView;
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        ButterKnife.bind(this);

        mRealm = RealmController.with(this).getRealm();
        ProfileRealm profileRealm = mRealm.where(ProfileRealm.class).findFirst();
        mProfileTextView.setText(profileRealm.getName());
        Picasso.with(this).load(profileRealm.getProfilePicture())
                .error(R.drawable.marshmallow).into(mProfileImageView);

    }


}
