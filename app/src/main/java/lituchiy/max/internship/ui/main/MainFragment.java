package lituchiy.max.internship.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import lituchiy.max.internship.R;
import lituchiy.max.internship.adapter.realmadapters.AppealsAdapter;
import lituchiy.max.internship.adapter.realmadapters.RealmAppealAdapter;
import lituchiy.max.internship.data.AppealRealm;
import lituchiy.max.internship.data.RealmController;
import lituchiy.max.internship.utils.Constants;
import lituchiy.max.internship.utils.Utils;

public class MainFragment extends Fragment implements MainContract.View {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.internet_connection_tv)
    TextView mInternetConnectionTv;
    private MainPresenter mPresenter;
    AppealsAdapter mAppealsAdapter;
    private String query;
    private int mCurrentPage = 0;
    private boolean isLoadingQuery;
    private LinearLayoutManager mLayoutManager;

    public static MainFragment newInstance(String query, int state) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_STATE, state);
        args.putString(Constants.ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            query = savedInstanceState.getString(Constants.ARG_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);

        ButterKnife.bind(this, view);
        mPresenter = new MainPresenter(this);

        query = getArguments().getString(Constants.ARG_QUERY);

        if (!Utils.isConnectingToInternet(getContext()))
            mInternetConnectionTv.setText(R.string.check_internet);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAppealsAdapter = new AppealsAdapter(getContext());
        mRecyclerView.setAdapter(mAppealsAdapter);

        if (RealmController.getInstance().getAppeals().size() == 0) {
            mPresenter.loadAppealList(query, Constants.QUERY_FIRST_PAGE, Constants.QUERY_FIRST_PAGE);
        } else {
            setRealmAdapter(RealmController.getInstance().queryedAppeals(query));
        }


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mPresenter.loadAppealList(query, Constants.QUERY_ALL, Constants.QUERY_ALL);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        isLoadingQuery = false;
                        mPresenter.loadAppealList(query, mCurrentPage, totalItemCount);
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

    public void setRealmAdapter(RealmResults<AppealRealm> appeals) {
        RealmAppealAdapter realmAdapter = new RealmAppealAdapter(getContext(), appeals);
        mAppealsAdapter.setRealmAdapter(realmAdapter);
        mAppealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar(int status) {
        switch (status) {
            case ProgressBar.VISIBLE:
                mProgressBar.setVisibility(ProgressBar.VISIBLE);
                break;
            case ProgressBar.GONE:
                if(mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
                mProgressBar.setVisibility(ProgressBar.GONE);
                break;
        }
    }

    @Override
    public void showAppeal() {
        isLoadingQuery = true;
        RealmAppealAdapter realmAdapter = new RealmAppealAdapter(getContext(),
                RealmController.getInstance().queryedAppeals(query));
        mAppealsAdapter.setRealmAdapter(realmAdapter);
        mAppealsAdapter.notifyDataSetChanged();
    }
}