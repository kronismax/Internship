package lituchiy.max.internship.data;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;
import lituchiy.max.internship.utils.Constants;

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public RealmResults<AppealRealm> getAppeals() {
        return realm.where(AppealRealm.class).findAll();
    }

    public AppealRealm getAppeal(int id) {
        return realm.where(AppealRealm.class).equalTo("id", id).findFirst();
    }

    public RealmResults<AppealRealm> queryedAppeals(String query) {

        switch (query) {
            case Constants.QUERY_IN_PROGRESS:
                return realm.where(AppealRealm.class).
                        equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_0)
                        .or().equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_9)
                        .or().equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_5)
                        .or().equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_7)
                        .or().equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_8).findAll();
            case Constants.QUERY_DONE:
                return realm.where(AppealRealm.class).
                        equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_10).or().
                        equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_6).findAll();
            case Constants.QUERY_PENDING:
                return realm.where(AppealRealm.class).
                        equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_1).or().
                        equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_3).or().
                        equalTo(Constants.APPEAL_STATE, Constants.APPEAL_STATE_4).findAll();
        }
        return null;
    }
}
