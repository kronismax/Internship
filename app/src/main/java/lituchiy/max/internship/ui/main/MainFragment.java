package lituchiy.max.internship.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.RecyclerAdapter;
import lituchiy.max.internship.data.model.Appeal;
import lituchiy.max.internship.data.model.AppealNew;
import lituchiy.max.internship.utils.Utils;

public class MainFragment extends Fragment  implements RecyclerFragmentView {

    private static final String TAG = "Debug";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerFragmentPresenter mPresenter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new RecyclerFragmentPresenter(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        List<Appeal> mAppeals = Utils.getAppealsList(getContext());

        RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(getContext());
        mRecyclerAdapter.setAppeals(mAppeals);

        mRecyclerView.setAdapter(mRecyclerAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
//                        retrofit();
                        mPresenter.refreshData();

                    }
                }
        );


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void refreshAppealList(List<AppealNew> mAppealList) {
        mSwipeRefreshLayout.setRefreshing(false);
        for (AppealNew appeal : mAppealList) {
            Log.d(TAG, "onResponse: " + appeal.getTitle());
        }
    }

//    private void retrofit() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://dev-contact.yalantis.com/rest/v1")
//                .build();
//
//        ApiService weatherService = retrofit.create(ApiService.class);
//        Observable<List<AppealNew>> call = weatherService.getAppeal("0,9,5,7,8");
////        Call<List<AppealNew>> call = weatherService.getAppeal("");
////        call.enqueue(new Callback<List<AppealNew>>() {
////            @Override
////            public void onResponse(Call<List<AppealNew>> call, Response<List<AppealNew>> response) {
////                for (AppealNew appeal : response.body()) {
////                    Log.d(TAG, "onResponse: "+appeal.getTitle());
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<AppealNew>> call, Throwable t) {
////
////            }
////        });
//
//        call.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<AppealNew>>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
//                    }
//
//                    @Override
//                    public void onNext(List<AppealNew> appealNew) {
//                        mSwipeRefreshLayout.setRefreshing(false);
//                        for (AppealNew appeal : appealNew) {
//                            Log.d(TAG, "onResponse: " + appeal.getTitle());
//                        }
//                    }
//
//                });
//    }
}