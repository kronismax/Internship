package lituchiy.max.internship.adapter.realmadapters;

import android.content.Context;

import io.realm.RealmResults;
import lituchiy.max.internship.data.AppealRealm;

public class RealmAppealAdapter extends RealmModelAdapter<AppealRealm> {

    public RealmAppealAdapter(Context context, RealmResults<AppealRealm> realmResults) {

        super(context, realmResults);
    }
}
