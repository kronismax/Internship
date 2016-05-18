package lituchiy.max.internship.ui.main;


import java.util.List;

import lituchiy.max.internship.data.model.AppealNew;
import lituchiy.max.internship.service.RestClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {


    private RecyclerFragmentView mView;

    public MainPresenter(RecyclerFragmentView mView) {
        this.mView = mView;
    }

    public void refreshData() {
        RestClient.getInstance().getmApiService().getAppeal("").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AppealNew>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<AppealNew> appealNew) {
                        mView.refreshAppealList(appealNew);
                    }

                });
    }

}
