package lituchiy.max.internship.ui.main;


import android.widget.ProgressBar;

import java.util.List;

import io.realm.Realm;
import lituchiy.max.internship.data.AppealRealm;
import lituchiy.max.internship.data.RealmController;
import lituchiy.max.internship.data.model.Appeal;
import lituchiy.max.internship.service.RestClient;
import lituchiy.max.internship.utils.Constants;
import lituchiy.max.internship.utils.Utils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainContract.UserActionListener {

    private MainContract.View mView;
    private Realm mRealm;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        mRealm = RealmController.getInstance().getRealm();
    }

    @Override
    public void loadAppealList(String query, int page, int offset) {
        mView.showProgressBar(ProgressBar.VISIBLE);
        Observable<List<Appeal>> listObservable;

        if (page == Constants.QUERY_ALL && offset == Constants.QUERY_ALL) {
            listObservable = RestClient.getInstance().getmApiService().getAppeal(query);
        } else if (offset == Constants.QUERY_FIRST_PAGE) {
            listObservable = RestClient.getInstance().getmApiService().getAppeal(query,
                    String.valueOf(Constants.QUERY_AMOUNT));
        } else {
            listObservable = RestClient.getInstance().getmApiService().getAppeal(query,
                    String.valueOf(Constants.QUERY_AMOUNT),
                    String.valueOf(Constants.QUERY_AMOUNT));
        }

        listObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Appeal>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Appeal> appealList) {
                        for (Appeal appeal : appealList) {
                            AppealRealm appealRealm = Utils.appealToRealmObject(appeal);
                            mRealm.beginTransaction();
                            mRealm.copyToRealmOrUpdate(appealRealm);
                            mRealm.commitTransaction();
                        }
                        mView.showAppeal();
                        mView.showProgressBar(ProgressBar.GONE);
                    }

                });
    }
}
