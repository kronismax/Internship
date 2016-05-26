package lituchiy.max.internship.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.realmadapters.AppealsAdapter;
import lituchiy.max.internship.adapter.realmadapters.RealmAppealAdapter;
import lituchiy.max.internship.data.AppealRealm;
import lituchiy.max.internship.data.RealmController;
import lituchiy.max.internship.data.model.AppealNew;
import lituchiy.max.internship.utils.Constants;
import lituchiy.max.internship.utils.Utils;

public class MainFragment extends Fragment implements MainView {

    private static final String TAG = "Debug";
    private static final String ARG_STATE = "ARG_STATE";
    private static final String ARG_QUERY = "ARG_QUERY";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    private MainPresenter mPresenter;
    AppealsAdapter mAppealsAdapter;
    private Realm mRealm;
    private String query;
    private int mCurrentPosition;
    private int mCurrentPage = 0;
    private boolean isLoadingQuery;
    private LinearLayoutManager mLayoutManager;

    public static MainFragment newInstance(String query, int state) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putString(ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = RealmController.with(this).getRealm();
        if (savedInstanceState != null) {
            query = savedInstanceState.getString(ARG_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);

        ButterKnife.bind(this, view);
        this.mRealm = RealmController.with(this).getRealm();
        mPresenter = new MainPresenter(this);

        query = getArguments().getString(ARG_QUERY);

//        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAppealsAdapter = new AppealsAdapter(getContext());
        mRecyclerView.setAdapter(mAppealsAdapter);

        if (RealmController.getInstance().getAppeals().size() == 0) {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            mPresenter.refreshData(query, Constants.QUERY_FIRST_PAGE, Constants.QUERY_FIRST_PAGE);
        } else {
//            setRealmAdapter(RealmController.getInstance().queryedAppeals(query));
            setRealmAdapter(RealmController.with(this).queryedAppeals(query));

        }


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mPresenter.refreshData(query, Constants.QUERY_ALL, Constants.QUERY_ALL);
                    }
                }
        );
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int itemCount = mRecyclerView.getAdapter().getItemCount();
//                if(itemCount > 0 && dy > 0) {
//                    mCurrentPosition = ((LinearLayoutManager)
//                            mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//
//                    if (mCurrentPosition >= itemCount - Constants.QUERY_START && isLoadingQuery) {
//                        mCurrentPage = itemCount / Constants.QUERY_AMOUNT;
//                        int offset = mCurrentPage * Constants.QUERY_OFFSET;
//                        mCurrentPage++;
//                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
//                        mPresenter.refreshData(query, mCurrentPage, offset);
//                        isLoadingQuery = false;
//                    }
//                }
//            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (isLoadingQuery) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        mCurrentPage = totalItemCount / Constants.QUERY_AMOUNT;
                        mCurrentPage++;
                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
                        isLoadingQuery = false;
                        Log.d(TAG, "onScrolled: "+mCurrentPage+"   " + totalItemCount);
                        mPresenter.refreshData(query, mCurrentPage, totalItemCount);
                    }
                }
            }

        });

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
        // TODO: 21.05.16 Model <---> View <---> Presenter
        // TODO: 21.05.16 Move all this ligic to presenter layer
        // TODO: 21.05.16 get RealmResult and update view

        isLoadingQuery = true;
        if (mProgressBar.getVisibility() == ProgressBar.VISIBLE) {
            mProgressBar.setVisibility(ProgressBar.GONE);
        }

        for (AppealNew appeal : mAppealList) {
            AppealRealm appealRealm = Utils.appealToRealmObject(appeal);
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(appealRealm);
            mRealm.commitTransaction();
        }
        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).queryedAppeals(query));
    }

    public void setRealmAdapter(RealmResults<AppealRealm> appeals) {
        RealmAppealAdapter realmAdapter = new RealmAppealAdapter(getContext(), appeals, true);
        mAppealsAdapter.setRealmAdapter(realmAdapter);
        mAppealsAdapter.notifyDataSetChanged();
    }

}